package com.example.iainteracitvemovies.presentation.user_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.iainteracitvemovies.R
import com.example.iainteracitvemovies.databinding.ActivityHomeBinding
import com.example.iainteracitvemovies.domain.entities.UserUI
import com.example.iainteracitvemovies.presentation.login.LoginActivity.Companion.USER_KEY
import com.example.iainteracitvemovies.presentation.movie_list.MoviesFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userSelected: UserUI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getIntentData()

        title=resources.getString(R.string.menu_1)
        loadFragment(ProfileFragment(), userSelected)
        binding.navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.navigation_appoint-> {
                    title=resources.getString(R.string.menu_1)
                    loadFragment(ProfileFragment(), userSelected)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_new_appoint-> {
                    title=resources.getString(R.string.menu_2)
                    loadFragment(MoviesFragment(), userSelected)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun getIntentData() {
        intent.extras?.let {
            userSelected = it.getSerializable(USER_KEY) as UserUI
        }

    }

    private fun loadFragment(fragment: Fragment, userSelected: UserUI) {

        val bundle = Bundle()
        bundle.putSerializable(USER_KEY, userSelected)

        val transaction = supportFragmentManager.beginTransaction()
        fragment.arguments = bundle
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}