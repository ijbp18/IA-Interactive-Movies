package com.example.iainteracitvemovies.common.utils

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.iainteracitvemovies.databinding.AlertCommonBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Created by Joao Betancourth on 22,enero,2022
 */
class DialogBuilder {

    fun createSimpleDialog(
        content: String?,
        textPositive: String,
        context: Context,
        confirmCallBack: InterfaceDialog
    ): AlertDialog {
        val dialogBuilder = MaterialAlertDialogBuilder(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val dialogView = AlertCommonBinding.inflate(inflater)

        content?.let {
            dialogView.textMessage.text = it
        }

        dialogBuilder.apply {
            setView(dialogView.root)
            setCancelable(false)
            setPositiveButton(textPositive) { dialogInterface, _ ->
                confirmCallBack.onAccept()
                dialogInterface.cancel()
            }
        }

        val alertDialogB = dialogBuilder.create()
        alertDialogB.show()

        return alertDialogB
    }

    interface InterfaceDialog {
        fun onAccept()
    }
}