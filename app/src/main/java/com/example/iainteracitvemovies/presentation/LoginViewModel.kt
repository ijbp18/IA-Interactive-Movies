package com.example.iainteracitvemovies.presentation

import android.content.Context
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iainteracitvemovies.R
import com.example.iainteracitvemovies.domain.common.Result
import com.example.iainteracitvemovies.domain.entities.UserUI
import com.example.iainteracitvemovies.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.collect

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userUseCase: UserUseCase,
    private val context: Context
) : ViewModel() {

    private val _isEmpty = MutableLiveData<Boolean>()
    val isEmpty: LiveData<Boolean> get() = _isEmpty

    private val _user = MutableLiveData<Result<UserUI>>()
    val user: LiveData<Result<UserUI>> get() = _user

    fun retrieveUsersDefault(username: String, pass: String) {
        viewModelScope.launch {
            userUseCase.invoke(context.getString(R.string.bodyRequest, username, pass))
                .collect {
                    _user.value = it
                }
        }
    }

    fun checkEmptyFields(edtTxtUser: EditText, edtTxtPass: EditText) {
        when {
            edtTxtUser.text.trim().isEmpty() -> edtTxtUser.error = context.getString(R.string.label_empty_field_error)
            edtTxtPass.text.trim().isEmpty() -> edtTxtPass.error = context.getString(R.string.label_empty_field_error)
            else -> _isEmpty.value = false
        }
    }
}