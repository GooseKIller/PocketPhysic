package com.example.physicsformulas

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class DatabaseHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            "CREATE TABLE IF NOT EXISTS wordle_words (_id INTEGER PRIMARY KEY AUTOINCREMENT, word TEXT, len INTEGER);"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS wordle_words;")
        onCreate(db)
    }

    fun getDataNames(sqlRequest:String = ""): ArrayList<TerminDataModel>{
        val db = this.readDatabaseFromAssets(this.context)
        val dataList = ArrayList<TerminDataModel>()

        val cursor: Cursor = db.rawQuery(sqlRequest, null)

        if(cursor.moveToFirst()){
            do {
                val nameDataModel = TerminDataModel(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                )
                dataList.add(nameDataModel)
            } while (cursor.moveToNext())
        }

        return dataList
    }

    fun getDataTermin(sqlRequest: String = ""): TerminDataModel {
        val db: SQLiteDatabase = this.readDatabaseFromAssets(this.context)
        val cursor: Cursor = db.rawQuery(sqlRequest, null)
        val terminDataModel = TerminDataModel()
        if(cursor.moveToFirst()){
            terminDataModel.id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            terminDataModel.name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            terminDataModel.unit = cursor.getString(cursor.getColumnIndexOrThrow("unit"))
            terminDataModel.description = cursor.getString(cursor.getColumnIndexOrThrow("description"))
            terminDataModel.formulas = cursor.getString(cursor.getColumnIndexOrThrow("formulas"))
        }
        return terminDataModel
    }

    fun getDataFormulas(sqlRequest: String = ""): ArrayList<FormulaDataModel> {
        val db:SQLiteDatabase = this.readDatabaseFromAssets(this.context)
        val dataList = ArrayList<FormulaDataModel>()
        db.rawQuery("PRAGMA case_sensitive_like=ON;", null)
        val cursor: Cursor = db.rawQuery(sqlRequest, null)

        if(cursor.moveToFirst()){
            do {
                val formulaDataModel = FormulaDataModel(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    name = cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    formula = cursor.getString(cursor.getColumnIndexOrThrow("formula")),
                    termins = cursor.getString(cursor.getColumnIndexOrThrow("termins"))
                )
                dataList.add(formulaDataModel)
            }while (cursor.moveToNext())
        }
        return dataList
    }

    fun readDatabaseFromAssets(context: Context):SQLiteDatabase {
        var stream: InputStream? = null


        val assetManager = context.assets
        stream = assetManager.open("formulas.db") //open("formulas.db")
        val file = File.createTempFile("prefix", "")
        val fileOutput = FileOutputStream(file)
        val buffer = ByteArray(1024)
        var size: Int = stream.read(buffer)
        while (size > 0) {
            fileOutput.write(buffer, 0, size)
            size = stream.read(buffer)
        }
        fileOutput.flush()
        fileOutput.close()
        stream?.close()
        var db: SQLiteDatabase = SQLiteDatabase.openDatabase(file.absolutePath, null, SQLiteDatabase.OPEN_READWRITE)
        return db
    }

    companion object {
        private const val DATABASE_VERSION:Int = 1
        private const val DATABASE_NAME:String = "formulas.db"
    }
    
    class FormulaDataModel(var id: Int = 0, var name:String = "", var formula: String = "", var termins:String = "")
    class TerminDataModel(var id: Int = 0, var name:String = "", var unit:String = "", var description:String = "", var formulas:String = "")
    //class NamesDataModel(var id:Int = 0, var names:String = "")
}