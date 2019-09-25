package com.imd.atividade

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class noteSqlHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSIN) {

    override fun onCreate( sqLiteDatabase:  SQLiteDatabase?) {

        sqLiteDatabase!!.execSQL(
            "CREATE TABLE $TABBLE_NAME ( " +
                    "$COLIMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_TITLE TEXT NOT NULL, " +
                    "$COLUMN_DESCRIPTION TEXT)"
        )

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}