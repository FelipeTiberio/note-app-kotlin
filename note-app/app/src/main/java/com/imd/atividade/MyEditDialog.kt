package com.imd.atividade

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.imd.atividade.R

class MyEditDialog : DialogFragment() {

    private var editText: EditText? = null
    private var listener: OnTextListener? = null



    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        builder.setTitle("Título")

        builder.setPositiveButton("OK") { dialogInterface, i ->
            if (listener != null && this.context !=  null) {
                val text = editText!!.text.toString()
                listener!!.onSetText(text)

                (this.context as Activity).finish()
            }
        }

        builder.setNegativeButton("Cancelar") {
                dialogInterface, i -> dismiss()
        }

        val view = activity!!.layoutInflater.inflate(R.layout.dialog_my_edit, null)
        editText = view.findViewById(R.id.edt_texto)
        builder.setView(view)
        return builder.create()
    }

    interface OnTextListener  {
        fun onSetText(text: String)
    }

    companion object {

        fun show(fm: FragmentManager, listener: OnTextListener) {

            var dialog = MyEditDialog()
            dialog.listener = listener
            dialog.show(fm, "textDialog")
           // dialog.context

        }
    }
}