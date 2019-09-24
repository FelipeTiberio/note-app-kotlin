package com.imd.atividade

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.startActivityForResult

import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item.view.*

class NoteAdapter(private val notes: List<Note>, private val callback: (Note)-> Unit)
    :RecyclerView.Adapter<NoteAdapter.LocalViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocalViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent, false)
        return LocalViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: LocalViewHolder, position: Int) {
        holder.bindView(notes[position])

        holder.itemView.setOnClickListener {
           //Toast.makeText(holder.itemView.context, "vc clicou em mim ", Toast.LENGTH_SHORT).show()
            val note = notes[holder.adapterPosition]
            callback(note)
        }
    }


    class LocalViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(note: Note) {
            this.itemView.titleItem_textview.text = note.title
            //this.itemView.textContent_textview.text = note.text
        }

    }
}