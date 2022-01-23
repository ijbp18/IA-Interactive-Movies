package com.example.iainteracitvemovies.common

import androidx.appcompat.app.AppCompatActivity
import com.example.iainteracitvemovies.R
import com.example.iainteracitvemovies.common.utils.DialogBuilder
import com.example.iainteracitvemovies.domain.entities.base.ErrorResponseUI

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
open class BaseActivity : AppCompatActivity() {

    fun showMessageFromBackend(
        errorResponseUI: ErrorResponseUI?,
        httCode: Int,
        okClickedScope: () -> Unit?
    ) {
        errorResponseUI?.let { errorResponse ->
            val errorMessage = errorResponse.error_description ?: getString(R.string.error_message_by_default)
            showMessageDialog(errorMessage) {
                okClickedScope()
            }
        }

        if(errorResponseUI == null){
            val errorMessage = getString(R.string.error_message_by_default) + "$ERROR_TYPE $httCode"
            showMessageDialog(errorMessage) {
                okClickedScope()
            }
        }


    }

    fun showMessageDialog(message: String, onAcceptedClicked: () -> Unit) {
        DialogBuilder().createSimpleDialog(
            message,
            getString(R.string.label_accept_button),
            this@BaseActivity,
            object : DialogBuilder.InterfaceDialog {
                override fun onAccept() {
                    onAcceptedClicked()
                }

            })
    }

    companion object {
        const val ERROR_TYPE = "Code error"
    }
}