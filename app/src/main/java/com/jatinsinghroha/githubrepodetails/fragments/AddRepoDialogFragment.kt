package com.jatinsinghroha.githubrepodetails.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import com.jatinsinghroha.githubrepodetails.R
import com.jatinsinghroha.githubrepodetails.interfaces.ActOnValidInValidInterface
import com.jatinsinghroha.githubrepodetails.interfaces.ValidateRepoInterface
import com.jatinsinghroha.githubrepodetails.viewModels.SharedViewModel
import kotlinx.android.synthetic.main.fragment_add_repo.*

class AddRepoDialogFragment: DialogFragment(), ActOnValidInValidInterface {
    private lateinit var validateRepoInterface: ValidateRepoInterface

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        topAppBar.setNavigationOnClickListener{dismiss()}

        addRepoButton.setOnClickListener{
            if(basicValidation(ownerRepoET.text.toString(), repoNameET.text.toString()))
                validateRepoInterface.validateRepo(ownerRepoET.text.toString().trim(), repoNameET.text.toString().trim(), this)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(fragmentManager: FragmentManager, validateRepoInterface: ValidateRepoInterface) =
                AddRepoDialogFragment().apply {
                    this.validateRepoInterface = validateRepoInterface
                }.show(fragmentManager, "SubmitRecipe")

    }

    override fun actOnValidInValid(isValid: Boolean) {
        if(isValid)
            dismiss()
        else{
            ownerRepoET.error = "InValid Repo Owner"
            repoNameET.error = "InValid Repo Name"
        }
    }

    private fun basicValidation(repoOwner: String, repoName: String): Boolean{
        return repoOwner.trim().isNotBlank()&&repoName.trim().isNotBlank()
    }
}