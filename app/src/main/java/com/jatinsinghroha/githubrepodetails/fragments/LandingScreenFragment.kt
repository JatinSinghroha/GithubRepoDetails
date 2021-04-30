package com.jatinsinghroha.githubrepodetails.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.jatinsinghroha.githubrepodetails.R
import com.jatinsinghroha.githubrepodetails.interfaces.OnRepoClickInterface
import com.jatinsinghroha.githubrepodetails.adapters.LandingRepoAdapter
import com.jatinsinghroha.githubrepodetails.interfaces.ActOnValidInValidInterface
import com.jatinsinghroha.githubrepodetails.interfaces.DeleteRepoInterface
import com.jatinsinghroha.githubrepodetails.interfaces.ValidateRepoInterface
import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo
import com.jatinsinghroha.githubrepodetails.room.RepoDatabase
import com.jatinsinghroha.githubrepodetails.viewModels.SharedViewModel
import com.jatinsinghroha.githubrepodetails.viewModels.SharedViewModelFactory
import kotlinx.android.synthetic.main.fragment_landing_screen.*
import kotlinx.serialization.json.Json


class LandingScreenFragment : Fragment(), OnRepoClickInterface, ValidateRepoInterface, DeleteRepoInterface {

private lateinit var repoDatabase: RepoDatabase
    private lateinit var repos_adapter: LandingRepoAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var actOnValidInValidInterface: ActOnValidInValidInterface
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var sharedViewModelFactory: SharedViewModelFactory
    private lateinit var listOfRepos: MutableList<Repo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModelFactory = SharedViewModelFactory()
        sharedViewModel = ViewModelProvider(this, sharedViewModelFactory).get(SharedViewModel::class.java)
        listOfRepos = mutableListOf()
        linearLayoutManager = LinearLayoutManager(context)
        repos_adapter = LandingRepoAdapter(listOfRepos, this)
        listOfReposRV.layoutManager = linearLayoutManager
        listOfReposRV.adapter = repos_adapter
        repoDatabase = RepoDatabase.getInstance(context!!)
        topAppBarLandRepo.setOnMenuItemClickListener {
            goToAddRepo()
            true
        }
        addNowButton.setOnClickListener{goToAddRepo()}

        initViewModel()
        initRepos()
    }

    private fun goToAddRepo(){AddRepoDialogFragment.newInstance(childFragmentManager, this)}
    private fun initViewModel() {
        sharedViewModel.listOfRepos.observe(viewLifecycleOwner, { list ->
            if(list.isEmpty()){
                listOfReposRV.visibility = View.GONE
                ifEmptyLayout.visibility = View.VISIBLE
            }
            else {
                ifEmptyLayout.visibility = View.GONE
                listOfReposRV.visibility = View.VISIBLE
                listOfRepos.clear()
                listOfRepos.addAll(list)
                repos_adapter.notifyDataSetChanged()
            }
        })
    }

    private fun getRepo(isValidateRepo: Boolean, repoOwner: String, repoName: String) {
        val requestQueue = Volley.newRequestQueue(context)
            val apiURL = "https://api.github.com/repos/$repoOwner/$repoName"
            val apiRequest = JsonObjectRequest(
                    Request.Method.GET, apiURL, null,
                    { response ->
                        //|| (response.get("message") as String).trim() == "Not Found"
                        if(response.has("message") )
                            actOnValidInValidInterface.actOnValidInValid(false)
                        else{
                            val format = Json{ignoreUnknownKeys=true}
                            val repo = format.decodeFromString(Repo.serializer(), response.toString())
                            repoDatabase.repoDao().insertOne(repo)
                            Log.e("TAG", "${repo.name}\n")
                            initRepos()
                            actOnValidInValidInterface.actOnValidInValid(true)
                        }
                    }, { error ->
                    Log.e("TAG", error.toString() + error.message)
                    actOnValidInValidInterface.actOnValidInValid(false)

            })
            requestQueue.add(apiRequest)
    }

    private fun initRepos() {
        if(repoDatabase.repoDao().count()!=0){
            sharedViewModel.listOfRepos.value = repoDatabase.repoDao().getAll() as List<Repo>
            repos_adapter.notifyDataSetChanged()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            LandingScreenFragment().apply {
            }
    }

    override fun shareRepoDetails(details: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, details)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    override fun goToRepoDetails(repo: Repo) {
        RepoDetailsDialogFragment.newInstance(childFragmentManager, repo, this)
    }

    override fun validateRepo(repoOwner: String, repoName: String, actOnValidInValidInterface: ActOnValidInValidInterface) {
        this.actOnValidInValidInterface = actOnValidInValidInterface
        getRepo(true, repoOwner, repoName)
    }

    override fun deleteRepo(repo: Repo) {
        repoDatabase.repoDao().delete(repo)
        initRepos()
    }
}