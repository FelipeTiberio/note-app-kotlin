package com.imd.atividade

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    lateinit var newNote: Note

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        newNote = intent.getSerializableExtra("note") as Note
        var id : Int = intent.getSerializableExtra("id") as Int


        // action bar secondActivity
        val actionBar  = supportActionBar
        actionBar!!.title = "Nova nota"
        actionBar.setDisplayHomeAsUpEnabled(true)


        textNote_editText.setText( newNote.text)

        button_gravar.setOnClickListener {
            newNote.text = textNote_editText.text.toString()
            var intentToReturn: Intent = Intent()
            intentToReturn.putExtra("newNote", newNote)
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
                newNote.title = text
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
