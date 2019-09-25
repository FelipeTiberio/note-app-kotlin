package com.imd.atividade.interfaces

import com.imd.atividade.Note
import javax.security.auth.callback.Callback

interface NoteRepository {

    fun save(note: Note)
    fun remove (vararg note: Note)
    fun noteById(id:Long, callback: (Note?) -> Unit)
    fun search(term : String , callback: (List<Note>) -> Unit)
}