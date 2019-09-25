package com.imd.atividade

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    lateinit var note: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        note = intent.getSerializableExtra("note") as Note
        var id : Int = intent.getSerializableExtra("id") as Int

        val actionBar  = supportActionBar

        /** Verifica se a intent é para uma nova nota ou um update de uma existente  */
        if ( intent.getStringExtra("tipoNote") == "nova") {
            actionBar!!.title = "Nova nota"
        }else{
            actionBar!!.title = note.title
            textNote_editText.setText( note.text)
        }

        actionBar!!.setDisplayHomeAsUpEnabled(true)

        button_gravar.setOnClickListener {

            note.text = textNote_editText.text.toString()
            var intentToReturn: Intent = Intent()
            intentToReturn.putExtra("newNote", note)
            intentToReturn.putExtra("id", id)

            setResult(Activity.RESULT_OK, intentToReturn)

            openEditDialog()
        }

        button_cancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }


    fun openEditDialog()  {

        MyEditDialog.show(supportFragmentManager, object : MyEditDialog.OnTextListener {
            override fun onSetText(text: String) {
                note.title = text
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
