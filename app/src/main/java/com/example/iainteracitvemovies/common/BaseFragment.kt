package com.example.iainteracitvemovies.common

import androidx.fragment.app.Fragment
import com.example.iainteracitvemovies.R
import com.example.iainteracitvemovies.common.utils.DialogBuilder
import com.example.iainteracitvemovies.domain.entities.base.ErrorResponseUI

/**
 * Created by Joao Betancourth on 23,enero,2022
 */
open class BaseFragment : Fragment(){
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
            requireContext(),
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