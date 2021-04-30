package com.jatinsinghroha.githubrepodetails.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.jatinsinghroha.githubrepodetails.R
import com.jatinsinghroha.githubrepodetails.adapters.IssuesAdapter
import com.jatinsinghroha.githubrepodetails.models.reposmodel.IssueElement
import com.jatinsinghroha.githubrepodetails.models.reposmodel.Repo
import kotlinx.android.synthetic.main.fragment_issues.*

class IssuesFragment : Fragment() {
    private lateinit var listOfIssues: List<IssueElement>
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
        return inflater.inflate(R.layout.fragment_issues, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        issues_rv.adapter = IssuesAdapter(context!!, listOfIssues)
        issues_rv.layoutManager = LinearLayoutManager(context)
    }

    companion object {
        @JvmStatic
        fun newInstance(listOfIssues: List<IssueElement>) =
            IssuesFragment().apply {
                this.listOfIssues = listOfIssues
                arguments = Bundle().apply {
                }
            }
    }
}