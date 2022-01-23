package com.example.iainteracitvemovies.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.iainteracitvemovies.common.BaseActivity
import com.example.iainteracitvemovies.databinding.ActivityMainBinding
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.UserUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

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
                    Toast.makeText(this@LoginActivity, "Pasar a Info Perfil :D", Toast.LENGTH_SHORT)
                        .show()
                    //NextActivity
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
}