package com.joerae.elizabethsstuffedanimals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddActionFigure : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.action_figure_fragment, container, false)

        // database
        val db = DataManager(view.context)

        // widgets
        val saveButton = view.findViewById<Button>(R.id.btnAddActionFigureToCollection)
        val actionFigureName = view.findViewById<EditText>(R.id.etActionFigureName)
        val actionFigureColor = view.findViewById<EditText>(R.id.etActionFigureColor)
        val actionFigureNotes = view.findViewById<EditText>(R.id.etActionFigureNotes)


        // save button
        saveButton.setOnClickListener {
            // variable for flag
            var good = true

            // check to see if fields are valid
            if (actionFigureName.text == null || actionFigureName.text.toString() == "") {
                good = false
            }

            if (actionFigureColor.text == null || actionFigureColor.text.toString() == "") {
                good = false
            }

            if (actionFigureNotes.text == null || actionFigureNotes.text.toString() == "") {
                good = false
            }

            // if everything is good, get the values and write it to the database
            if (good) {
                val newActionFigure = Animal()

                newActionFigure.category = "Action Figure"

                newActionFigure.name = actionFigureName.text.toString()

                newActionFigure.color = actionFigureColor.text.toString()

                newActionFigure.notes = actionFigureNotes.text.toString()

                db.insertData(newActionFigure)
            } else {
                Toast.makeText(view.context, "Gotta fill in all of the fields kid", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}