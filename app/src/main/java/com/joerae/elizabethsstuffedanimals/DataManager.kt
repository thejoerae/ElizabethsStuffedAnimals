package com.joerae.elizabethsstuffedanimals

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASENAME = "ELIZABETHS ANIMALS"
val TABLENAME = "Inventory"
class DataManager(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLENAME + " (Id integer PRIMARY KEY autoincrement, AnimalCategory text not null, AnimalName text not null, AnimalColor text not null, AnimalNotes text)"
        db?.execSQL(createTable)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }

    fun insertData(animal: Animal) {
        val database = this.writableDatabase

        val contentValues = ContentValues()
        // don't need to add the Id value because it's auto assigned when we create the food object
        contentValues.put("AnimalCategory", animal.category)
        contentValues.put("AnimalName", animal.name)
        contentValues.put("AnimalColor", animal.color)
        contentValues.put("AnimalNotes", animal.notes)

        val result = database.insert(TABLENAME, null, contentValues)

        if (result == (0).toLong()) {
            Toast.makeText(context, "Something went wrong - please try again", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Your animal has been added", Toast.LENGTH_SHORT).show()
        }
    }

    // let's read the data from the database
    fun readData(): MutableList<Animal> {
        val list: MutableList<Animal> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"

        val result = db.rawQuery(query, null)

        if (result.moveToFirst()) {
            do {
                val animal = Animal()

                animal.id = result.getInt(result.getColumnIndex("Id"))
                animal.category = result.getString(result.getColumnIndex("AnimalCategory"))
                animal.name = result.getString(result.getColumnIndex("AnimalName"))
                animal.color = result.getString(result.getColumnIndex("AnimalColor"))
                animal.notes = result.getString(result.getColumnIndex("AnimalNotes"))

                list.add(animal)
            }
            while (result.moveToNext())
        }
        return list
    }
}