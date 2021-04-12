package com.joerae.elizabethsstuffedanimals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class AddAnimal : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.animal_fragment, container, false)

        // database
        val db = DataManager(view.context)

        // widgets
        val saveButton = view.findViewById<Button>(R.id.btnAddAnimalToCollection)
        val animalName = view.findViewById<EditText>(R.id.etAnimalName)
        val animalColor = view.findViewById<EditText>(R.id.etAnimalColorName)
        val animalNotes = view.findViewById<EditText>(R.id.etAnimalNotes)


        // save button
        saveButton.setOnClickListener {
            // variable for flag
            var good = true

            // check to see if fields are valid
            if (animalName.text == null || animalName.text.toString() == "") {
                good = false
            }

            if (animalColor.text == null || animalColor.text.toString() == "") {
                good = false
            }

            if (animalNotes.text == null || animalNotes.text.toString() == "") {
                good = false
            }

            // if everything is good, get the values and write it to the database
            if (good) {
                val newAnimal = Animal()

                newAnimal.category = "Stuffed Animal"

                newAnimal.name = animalName.text.toString()

                newAnimal.color = animalColor.text.toString()

                newAnimal.notes = animalNotes.text.toString()

                db.insertData(newAnimal)
            } else {
                Toast.makeText(view.context, "Gotta fill in all of the fields kid", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}