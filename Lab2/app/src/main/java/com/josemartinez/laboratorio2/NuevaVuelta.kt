package com.josemartinez.laboratorio2

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import java.io.Serializable

class NuevaVuelta : AppCompatActivity() {
    // Data variables
    var lapHistory = ArrayList<Int>()
    val predefined_vueltas = ArrayList<Int>()
    var numeroVueltas: Int

    // Components variables
    private lateinit var  numeroVueltasText : TextView


    init {
        numeroVueltas = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_vuelta)

        //GET LAP HISTORY
        lapHistory = getIntent().extras.get("lapHistory") as ArrayList<Int>

        // ADD PREDEFINED VUELTAS
        predefined_vueltas.add(1)
        predefined_vueltas.add(3)
        predefined_vueltas.add(5)
        predefined_vueltas.add(10)


        numeroVueltasText = findViewById<TextView>(R.id.numeroVueltasTextView)
        numeroVueltasText.text = numeroVueltas.toString()

        val minus = findViewById<Button>(R.id.minusNumeroVueltasButton)
        val plus = findViewById<Button>(R.id.plusNumeroVueltasButton)
        val agregarVuelta = findViewById<Button>(R.id.agregar_vuelta_button)
        val regresarInicio = findViewById<Button>(R.id.regresar_inicio_button)

        minus.setOnClickListener{
            numeroVueltas--
            if(numeroVueltas < 0){
                numeroVueltas = 0
                Toast.makeText(this, "No se puede agregar menos de 0 vueltas", Toast.LENGTH_SHORT).show()
            }

            numeroVueltasText.text = numeroVueltas.toString()
        }

        plus.setOnClickListener{
            numeroVueltas++
            numeroVueltasText.text = numeroVueltas.toString()
        }

        agregarVuelta.setOnClickListener {
            lapHistory.add(numeroVueltas)
            Toast.makeText(this, "Se agregó un nuevo entreno de " + numeroVueltas.toString() + " vueltas", Toast.LENGTH_SHORT).show()
            regresarAInicio()
        }

        regresarInicio.setOnClickListener {
            regresarAInicio()
        }

        updateAdapter()
    }

    private fun regresarAInicio(){
        val returnIntent: Intent = Intent()
        returnIntent.putExtra("lapHistory", lapHistory as Serializable)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    private fun setVueltas(vueltas: Int){
        numeroVueltas = vueltas
        numeroVueltasText.text = vueltas.toString()
    }

    private fun updateAdapter() {
        val list = findViewById<ListView>(R.id.vueltas_predefinidas_listview)
        list.adapter = CostumArrayAdapter(predefined_vueltas, this)
        list.setOnItemClickListener { parent, view, position, id ->
            setVueltas(predefined_vueltas[position])
            Toast.makeText(this, "Se ha seleccionado la opción predefinida de " + predefined_vueltas[position].toString() + " vueltas", Toast.LENGTH_SHORT).show()
        }
    }
}
