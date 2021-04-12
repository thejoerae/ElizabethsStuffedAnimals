package com.joerae.elizabethsstuffedanimals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddDoll : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.doll_fragment, container, false)

        // database
        val db = DataManager(view.context)

        // widgets
        val saveButton = view.findViewById<Button>(R.id.btnAddDollToCollection)
        val dollName = view.findViewById<EditText>(R.id.etDollName)
        val dollColor = view.findViewById<EditText>(R.id.etDollColor)
        val dollNotes = view.findViewById<EditText>(R.id.etDollNotes)


        // save button
        saveButton.setOnClickListener {
            // variable for flag
            var good = true

            // check to see if fields are valid
            if (dollName.text == null || dollName.text.toString() == "") {
                good = false
            }

            if (dollColor.text == null || dollColor.text.toString() == "") {
                good = false
            }

            if (dollNotes.text == null || dollNotes.text.toString() == "") {
                good = false
            }

            // if everything is good, get the values and write it to the database
            if (good) {
                val newDoll = Animal()

                newDoll.category = "Doll"

                newDoll.name = dollName.text.toString()

                newDoll.color = dollColor.text.toString()

                newDoll.notes = dollNotes.text.toString()

                db.insertData(newDoll)
            } else {
                Toast.makeText(view.context, "Gotta fill in all of the fields kid", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}