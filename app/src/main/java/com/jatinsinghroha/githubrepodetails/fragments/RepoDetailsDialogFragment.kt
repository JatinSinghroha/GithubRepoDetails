package com.jatinsinghroha.githubrepodetails.fragments

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.tabs.TabLayoutMediator
import com.jatinsinghroha.githubrepodetails.R
import com.jatinsinghroha.githubrepodetails.adapters.TabLayoutAdapter
import com.jatinsinghroha.githubrepodetails.interfaces.DeleteRepoInterface
import com.jatinsinghroha.githubrepodetails.models.reposmodel.BranchItem
import com.jatinsinghroha.githubrepodetails.models.reposmodel.IssueElement
import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo
import com.jatinsinghroha.githubrepodetails.room.RepoDatabase
import kotlinx.android.synthetic.main.fragment_repo_details.*
import kotlinx.serialization.json.Json
import org.json.JSONArray


class RepoDetailsDialogFragment: DialogFragment() {

    private lateinit var deleteRepoInterface: DeleteRepoInterface
    private lateinit var repo: Repo
    private lateinit var repoDatabase: RepoDatabase
    private lateinit var tabLayoutAdapter: TabLayoutAdapter
    private lateinit var issuesList: MutableList<IssueElement>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.GithubRepoFullScreenDialog)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
            dialog.window!!.setWindowAnimations(R.style.GithubRepoTestSlide)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        issuesList = mutableListOf()
        getIssues()

        repo_detail_name.text = repo.name
        repo_detail_desc.text = repo.description

        repoDatabase = RepoDatabase.getInstance(context!!)

        getUpdateBranch()

        topAppBarDetails.setNavigationOnClickListener{dismiss()}
        topAppBarDetails.setOnMenuItemClickListener { item->
            when(item.itemId){
                R.id.delete_repo_menu -> {
                    deleteRepoInterface.deleteRepo(repo)
                    dismiss()
                }
                R.id.view_repo_in_browser_menu -> {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(repo.htmlURL)
                        )
                    )
                }
            }
            true
        }

        tabLayoutAdapter = TabLayoutAdapter(childFragmentManager, lifecycle, repo, issuesList)
        viewPagerTabs.adapter = tabLayoutAdapter
        TabLayoutMediator(repo_details_tabs, viewPagerTabs){ tab, position ->
            when (position) {
                0 -> {
                    tab.text = "BRANCHES"
                }
                1 -> {
                    tab.text = "ISSUES"
                }
            }
        }.attach()

    }

    private fun getIssues() {
        issuesList.clear()
        val requestQueue = Volley.newRequestQueue(context)
        val apiURL = "https://api.github.com/repos/${repo.fullName}/issues?state=open"
        val apiRequest = JsonArrayRequest(
            Request.Method.GET, apiURL, null,
            { response ->
                //|| (response.get("message") as String).trim() == "Not Found"
                val format = Json { ignoreUnknownKeys = true }
                if (response != null) {
                    val issuesArray: JSONArray? = response
                    if (issuesArray != null) {
                        for (i in 0 until issuesArray.length()) {
                            val issueElement : IssueElement = format.decodeFromString(
                                IssueElement.serializer(), issuesArray.getJSONObject(i).toString())
                            issueElement.userName = issueElement.user?.login.toString()
                            issueElement.userAvatarURL = issueElement.user?.avatarURL.toString()
                            issuesList.add(issueElement)
                        }

                    }
                    val badge = repo_details_tabs.getTabAt(1)?.orCreateBadge
                    badge?.number = issuesList.size
                    tabLayoutAdapter.notifyDataSetChanged()
                }


            }, { error ->
                Log.e("TAG", error.toString() + error.message)

            })
        requestQueue.add(apiRequest)
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
                        initRepos()
                    }
                }


            }, { error ->
                Log.e("TAG", error.toString() + error.message)

            })
        requestQueue.add(apiRequest)
    }

    companion object {
        @JvmStatic
        fun newInstance(
            fragmentManager: FragmentManager,
            repo: Repo,
            deleteRepoInterface: DeleteRepoInterface
        ) =
                RepoDetailsDialogFragment().apply {
                    this.deleteRepoInterface = deleteRepoInterface
                    this.repo = repo
                }.show(fragmentManager, "RepoDetailsFragment")

    }

    private fun initRepos() {
        if(repoDatabase.branchDao().count()!=0){
            for(i in 0 until repoDatabase.branchDao().count())
            Log.e(
                "TAG", "${repoDatabase.branchDao().count()} ${
                    repoDatabase.branchDao().getAll()
                        ?.get(i)?.id
                } + ${repoDatabase.branchDao().getAll()?.get(i)?.name}"
            )
        }
    }

}