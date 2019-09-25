package com.imd.atividade.interfaces

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.imd.atividade.*

class SQLiteRepositoryNote(context: Context): NoteRepository  {

    private  val helper : NoteSqlHelper = NoteSqlHelper(context)

     fun insert(note: Note): Long{
        val db = helper.writableDatabase

        val contentValuesToDB = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_DESCRIPTION, note.text)
        }

        val id = db.insert(TABBLE_NAME, null, contentValuesToDB)

        if (id != -1L){
            note.id = id
        }

        db.close()

         return  id
    }


     fun update( note : Note){
        val db = helper.writableDatabase

        val contentValues = ContentValues().apply {
            put(COLUMN_TITLE, note.title)
            put(COLUMN_DESCRIPTION, note.text)
        }

        //@TODO não entendi direito esse código sql de update
        db.update(
            TABBLE_NAME,
            contentValues,
            "$COLIMN_ID= ?",
            arrayOf((note.id.toString()))
        )

        db.close()
    }



    override fun save(note: Note) {
      if(note.id == 0L){
        insert(note)
      }else{
          update(note)
      }
    }

    override fun remove(vararg notes: Note) {

        val db = helper.writableDatabase

        for (note in notes){
            db.delete(
                TABBLE_NAME,
                "$COLIMN_ID = ? ",
                arrayOf(note.id.toString())
            )
        }
        db.close()
    }

    override fun noteById(id: Long, callback: (Note?) -> Unit) {

        val sql = "SELECT * FROM $TABBLE_NAME WHERE $COLIMN_ID = ?"
        val db = helper.writableDatabase
        val cursor = db.rawQuery(sql, arrayOf(id.toString()))
        val note = if (cursor.moveToNext())noteFromCursor(cursor) else null

        callback(note)
    }

    override fun search(term: String, callback: (List<Note>) -> Unit) {
        var sql = "SELECT * FROM $TABBLE_NAME"
        var args : Array<String>? =null

        if(term.isNotEmpty()){
            sql += "WHERE $COLUMN_TITLE LIKE ?"
            args = arrayOf("$term")
        }

        sql += " ORDER BY $COLUMN_TITLE"
        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql, args)
        val notes = ArrayList<Note>()

        while (cursor.moveToNext()){
            val note = noteFromCursor(cursor)
            notes.add(note)
        }

        cursor.close()
        db.close()
        callback(notes)
    }

    private fun noteFromCursor(cursor: Cursor) : Note{
        val id = cursor.getLong(cursor.getColumnIndex(COLIMN_ID))
        val title  = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE))
        val description = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION))

        var note = Note(title,description)
        note.id = id
        return note
    }

    fun notesArray() : ArrayList<Note>{
        var sql = "SELECT * FROM $TABBLE_NAME"
        var args : Array<String>? =null

        sql += " ORDER BY $COLUMN_TITLE"
        val db = helper.readableDatabase
        val cursor = db.rawQuery(sql, args)
        val notes = ArrayList<Note>()

        while (cursor.moveToNext()){
            val note = noteFromCursor(cursor)
            notes.add(note)
        }

        cursor.close()
        db.close()
        return  notes
    }
}