package com.joerae.elizabethsstuffedanimals

import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        // set widgets
        val addToy = findViewById<Button>(R.id.btnViewToys)
        val rg = findViewById<RadioGroup>(R.id.rgToy)

        // 	Default fragment set is View Toys fragment
        var frag = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
        frag = ViewToyFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, frag)
                .commit()


        // RadioGroup/RadioButtons – setOnCheckedChangeListener
        // Replace the fragment in the fragment holder based on RadioButton selected

        rg.setOnCheckedChangeListener {
            rg,checkedID ->

            val rb = rg.findViewById<RadioButton>(checkedID)

            when (rb.id) {
                R.id.rbStuffedAnimal -> {
                    var fragAnimal = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                    fragAnimal = AddAnimal()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragAnimal)
                            .commit()
                }

                R.id.rbDoll -> {
                    var fragDoll = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                    fragDoll = AddDoll()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragDoll)
                            .commit()
                }

                R.id.rbActionFigure -> {
                    var fragActionFigure = supportFragmentManager.findFragmentById(R.id.fragmentContainer)
                    fragActionFigure = AddActionFigure()
                    supportFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, fragActionFigure)
                            .commit()
                }
            }
        }

        // add a new toy button
        addToy.setOnClickListener {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, frag)
                    .commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}