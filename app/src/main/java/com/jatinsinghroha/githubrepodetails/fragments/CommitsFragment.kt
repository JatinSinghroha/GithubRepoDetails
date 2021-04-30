package com.jatinsinghroha.githubrepodetails.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.jatinsinghroha.githubrepodetails.R
import com.jatinsinghroha.githubrepodetails.adapters.CommitsAdapter
import com.jatinsinghroha.githubrepodetails.models.reposmodel.CommitItem
import com.jatinsinghroha.githubrepodetails.models.reposmodel.IssueElement
import kotlinx.android.synthetic.main.fragment_commits.*
import kotlinx.android.synthetic.main.fragment_repo_details.*
import kotlinx.serialization.json.Json
import org.json.JSONArray

class CommitsFragment: DialogFragment() {
    private lateinit var repoFullName: String
    private lateinit var branchSha: String
    private lateinit var branchName: String
    private lateinit var commitsList: MutableList<CommitItem>
    private lateinit var commitsAdapter: CommitsAdapter

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
        return inflater.inflate(R.layout.fragment_commits, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topAppBarCommits.subtitle = branchName
        topAppBarCommits.setNavigationOnClickListener { dismiss() }
        commitsList = mutableListOf()
        commitsAdapter = CommitsAdapter(context!!, commitsList)
        commitsListRV.adapter = commitsAdapter
        commitsListRV.layoutManager = LinearLayoutManager(context)
        getCommits()
    }

    private fun getCommits() {
        commitsList.clear()
        val requestQueue = Volley.newRequestQueue(context)
        val apiURL = "https://api.github.com/repos/$repoFullName/commits?sha=$branchSha"
        val apiRequest = JsonArrayRequest(
            Request.Method.GET, apiURL, null,
            { response ->
                //|| (response.get("message") as String).trim() == "Not Found"
                val format = Json { ignoreUnknownKeys = true }
                if (response != null) {
                    val commitsArray: JSONArray? = response
                    if (commitsArray != null) {
                        for (i in 0 until commitsArray.length()) {
                            val commitItem : CommitItem = format.decodeFromString(
                                CommitItem.serializer(), commitsArray.getJSONObject(i).toString())
                            commitsList.add(commitItem)
                        }

                    }
                    commitsAdapter.notifyDataSetChanged()
                }
            }, { error ->
                Log.e("TAG", error.toString() + error.message)

            })
        requestQueue.add(apiRequest)
    }

    companion object {
        @JvmStatic
        fun newInstance(fragmentManager: FragmentManager, repoFullName: String, branchName: String, branchSha: String) =
            CommitsFragment().apply {
                this.repoFullName = repoFullName
                this.branchSha = branchSha
                this.branchName = branchName
            }.show(fragmentManager, "CommitsFragment")

    }

}