package com.example.app_

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import java.sql.Types.ROWID

class MyDbHelper(context: Context): SQLiteOpenHelper(context,
    MyDbHelper.DATABASE_NAME,null,
    MyDbHelper.DATABASE_VERSION
) {
    object MyDBContract{

        object  MyEntry:BaseColumns{
            const val TABLE_NAME ="myDFf"
            const val c1 ="phoneNum"
            const val c2 = "date"
            const val c3 = "msg"
            const val c4 = "relationship"

        }
    }

   //class MyDbHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,DATABASE_VERSION){
        val SQL_CREATE_ENTRIES=
      /* "CREATE TABLE ${MyDBContract.MyEntry.TABLE_NAME} ("+
               "${MyDBContract.MyEntry.c1} TEXT PRIMARY KEY,"+
               "${MyDBContract.MyEntry.c2} TEXT,"+
               "${MyDBContract.MyEntry.c3} TEXT,")+
               "${MyDBContract.MyEntry.c4} TEXT,"+
               "${MyDBContract.MyEntry.c5} TEXT)"*/

            "CREATE TABLE ${MyDBContract.MyEntry.TABLE_NAME} ("+
                    "${BaseColumns._ID} INTEGER PRIMARY KEY,"+
                    "${MyDBContract.MyEntry.c1} TEXT,"+
                    "${MyDBContract.MyEntry.c2} TEXT,"+
                    "${MyDBContract.MyEntry.c3} TEXT,"+
                    "${MyDBContract.MyEntry.c4} TEXT)"

        val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${MyDBContract.MyEntry.TABLE_NAME}"

    fun selectAll(): MutableList<MyElement> {
        val readList = mutableListOf<MyElement>()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM ${MyDBContract.MyEntry.TABLE_NAME}" + ";",
            null
        )

        with(cursor) {
            while (moveToNext()) {
                readList.add(
                    MyElement(
                        cursor.getString(1),
                        cursor.getString(3),
                        cursor.getString(2),
                        cursor.getString(4)

                    )
                )
            }
        }
        cursor.close()
        db.close()
        return readList}


    fun selectNum(): MutableList<MyElement> {
        val readList = mutableListOf<MyElement>()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM   ${MyDBContract.MyEntry.TABLE_NAME} " +
                    " WHERE ${MyDBContract.MyEntry.c1} = " +
                    "(SELECT ${ MyDBContract.MyEntry.c1}  FROM ${MyDBContract.MyEntry.TABLE_NAME} ORDER BY ROWID DESC LIMIT 1)" +";",
            null
        )

        with(cursor) {
            while (moveToNext()) {
                readList.add(
                    MyElement(
                        cursor.getString(1),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(2)

                    )
                )
            }
        }
        cursor.close()
        db.close()
        return readList}

        override  fun onCreate(db: SQLiteDatabase){
            db.execSQL(SQL_CREATE_ENTRIES)
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
                db.execSQL(SQL_DELETE_ENTRIES)
            //add
                var db= readableDatabase
            onCreate(db)
        }

        override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
                onUpgrade(db,oldVersion,newVersion)
        }

        companion object{
            const val DATABASE_VERSION=1
            const val DATABASE_NAME="myDBf.db"
        }
    }


//}