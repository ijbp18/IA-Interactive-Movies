package com.example.iainteracitvemovies.presentation.user_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.iainteracitvemovies.R
import com.example.iainteracitvemovies.databinding.FragmentProfileBinding
import com.example.iainteracitvemovies.domain.entities.UserUI
import com.example.iainteracitvemovies.presentation.LoginActivity

class ProfileFragment : Fragment() {

    private lateinit var userSelected: UserUI
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        getBundleExtra()
        setUserInfo()
        return binding.root
    }

    private fun setUserInfo() {
        binding.apply {
            userSelected.let { userInfo ->
                tvProfileUser.text = getString(R.string.label_full_name, userInfo.first_name, userInfo.last_name)
                tvEmailText.text =  userInfo.email
                tvPhoneText.text = userInfo.phone_number
                tvCardNumberText.text = userInfo.card_number
            }
        }
    }

    private fun getBundleExtra() {
        val args = arguments
        userSelected = args?.getSerializable(LoginActivity.USER_KEY) as UserUI
    }


}