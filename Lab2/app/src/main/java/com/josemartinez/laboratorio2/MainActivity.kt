package com.josemartinez.laboratorio2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import java.io.Serializable

class MainActivity : AppCompatActivity(), LapHistory{
    override fun del(elementIndex: Int) {
        lapHistory.removeAt(elementIndex)
    }

    override fun add(element: Int) {
        lapHistory.add(element)
    }

    override fun clear() {
        lapHistory.clear()
        println("Eliminar")
        println(lapHistory)
        updateAdapter()
        Toast.makeText(this, "Se eliminó el historial", Toast.LENGTH_SHORT).show()
    }

    override var lapHistory = ArrayList<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add(2)
        add(3)

        val nuevoEntrenoButton = findViewById<Button>(R.id.nuevoEntrenoButton)
        nuevoEntrenoButton.setOnClickListener {
            val i: Intent = Intent(this, NuevaVuelta::class.java)
                    .putExtra("lapHistory", lapHistory as Serializable)
            startActivityForResult(i, 12)
        }

        val eliminarHistorial = findViewById<Button>(R.id.eliminarHisotiralButton)
        eliminarHistorial.setOnClickListener {
            clear()
        }

        updateAdapter()

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == Activity.RESULT_OK){
            if (data != null) {
                lapHistory = data.extras.get("lapHistory") as ArrayList<Int>
                updateAdapter()
                println(lapHistory)
            } else {
                println("is null")
            }
        }

    }

    private fun updateAdapter() {
        val list = findViewById<ListView>(R.id.entrenosListView)
        list.adapter = CostumArrayAdapter(lapHistory, this)
        list.setOnItemLongClickListener { parent, view, position, id ->
            Toast.makeText(this, "Se ha eliminado un entreno de " + lapHistory[position].toString() + " vueltas.", Toast.LENGTH_SHORT).show()
            del(position)
            updateAdapter()

            true
        }
    }
}


