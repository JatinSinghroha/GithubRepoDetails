package com.jatinsinghroha.githubrepodetails.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.jatinsinghroha.githubrepodetails.R
import com.jatinsinghroha.githubrepodetails.adapters.BranchesAdapter
import com.jatinsinghroha.githubrepodetails.interfaces.OnBranchClick
import com.jatinsinghroha.githubrepodetails.models.reposmodel.BranchItem
import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo
import com.jatinsinghroha.githubrepodetails.room.RepoDatabase
import com.jatinsinghroha.githubrepodetails.viewModels.SharedViewModel
import com.jatinsinghroha.githubrepodetails.viewModels.SharedViewModelFactory
import kotlinx.android.synthetic.main.fragment_branches.*
import kotlinx.serialization.json.Json
import org.json.JSONArray

class BranchesFragment : Fragment(), OnBranchClick {
    private lateinit var repoDatabase: RepoDatabase
    private lateinit var repo: Repo
    private lateinit var branchesAdapter: BranchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_branches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repoDatabase = RepoDatabase.getInstance(context!!)
        getUpdateBranch()
    }

    companion object {
        @JvmStatic
        fun newInstance(repo: Repo) =
            BranchesFragment().apply {
                this.repo = repo
                arguments = Bundle().apply {
                }
            }
    }

    private fun getUpdateBranch() {
        val requestQueue = Volley.newRequestQueue(context)
        val apiURL = "https://api.github.com/repos/${repo.fullName}/branches"
        val apiRequest = JsonArrayRequest(
                Request.Method.GET, apiURL, null,
                { response ->
                    //|| (response.get("message") as String).trim() == "Not Found"
                    val format = Json { ignoreUnknownKeys = true }
                    if (response != null) {
                        val branchArray: JSONArray? = response
                        if (branchArray != null) {
                            for (i in 0 until branchArray.length()) {
                                val branchItem = format.decodeFromString(
                                        BranchItem.serializer(), branchArray.getJSONObject(
                                        i
                                ).toString()
                                )
                                branchItem.id = repo.id!!
                                branchItem.commitSha = branchItem.commit?.sha
                                branchItem.commitURL = branchItem.commit?.url

                                repoDatabase.branchDao().insertOne(branchItem)
                            }
                            initBranches(repoDatabase.branchDao().getAllRecordWithID(repo.id) as List<BranchItem>)
                        }
                    }


                }, { error ->
            Log.e("TAG", error.toString() + error.message)

        })
        requestQueue.add(apiRequest)
    }

    private fun initBranches(listOfBranches: List<BranchItem>){
        branchesAdapter = BranchesAdapter(listOfBranches, this)
        branches_rv.adapter = branchesAdapter
        branches_rv.layoutManager = LinearLayoutManager(context)
    }

    override fun onClickBranch(branchName: String, branchSha: String) {
        CommitsFragment.newInstance(childFragmentManager, repo.fullName!!, branchName, branchSha)
    }
}