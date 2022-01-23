package com.example.iainteracitvemovies.presentation.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.iainteracitvemovies.common.BaseActivity
import com.example.iainteracitvemovies.databinding.ActivityMainBinding
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.UserUI
import com.example.iainteracitvemovies.presentation.user_info.HomeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private var usersResultContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    //nothing to do
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        suscribeUI()
        initListeners()
    }

    private fun initListeners() {

        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.checkEmptyFields(edtTxtUser, edtTxtPass)

            }
        }
    }

    private fun suscribeUI() {
        viewModel.user.observe(this, {
            when (it) {
                is Result.Success<UserUI> -> {
                    binding.homeLoader.visibility = View.GONE
                    showUserInfoScreen(it.data)
                }
                is Result.Loading -> {
                    binding.homeLoader.visibility = View.VISIBLE
                }
                is Result.Failure -> {
                    binding.homeLoader.visibility = View.GONE
                    showMessageFromBackend(it.error, it.httpCode) {
                    }
                }
            }
        })

        viewModel.isEmpty.observe(this, {
            if (!it) {
                binding.apply {
                    viewModel.retrieveUsersDefault(
                        edtTxtUser.text.toString().trim(),
                        edtTxtPass.text.toString().trim()
                    )
                }
            }
        })
    }

    private fun showUserInfoScreen(data: UserUI?) {

        data?.let {
            val userInfoIntent =
                Intent(this@LoginActivity, HomeActivity::class.java).apply {
                    putExtra(USER_KEY, it)
                }
            usersResultContent.launch(userInfoIntent)
        }
    }

    companion object {
        const val USER_KEY = "user_key"
    }
}