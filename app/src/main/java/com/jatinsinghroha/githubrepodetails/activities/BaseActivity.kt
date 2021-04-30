package com.jatinsinghroha.githubrepodetails.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jatinsinghroha.githubrepodetails.R
import com.jatinsinghroha.githubrepodetails.fragments.LandingScreenFragment
import com.jatinsinghroha.githubrepodetails.viewModels.SharedViewModel

class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_screen)

        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, LandingScreenFragment.newInstance()).commit()
    }
}