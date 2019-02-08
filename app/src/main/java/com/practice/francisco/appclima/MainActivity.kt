package com.practice.francisco.appclima

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.practice.francisco.appclima.models.Ciudad
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var tvCiudad:TextView? = null
    var tvGrados:TextView? = null
    var tvEstatus:TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCiudad = findViewById(R.id.tvCiudad)
        tvGrados = findViewById(R.id.tvGrados)
        tvEstatus = findViewById(R.id.tvEstatus)

        val ciudad = intent.getStringExtra("com.practice.francisco.appclima.ciudades.CIUDAD")

        //Toast.makeText(this, ciudad, Toast.LENGTH_SHORT).show()

        if(Network.hayRed(this)){
            //Ejecutar solicitud HTTP
            solicitudHTTPVolley("http://api.openweathermap.org/data/2.5/weather?id="+ciudad+"&"+
                    "appid=2edccce187da809020bff313106d2553&"+
                    "units=metric&lang=es")
            //2edccce187da809020bff313106d2553
            //Pasto : 3678328
        }else{
            Toast.makeText(this, "No hay red", Toast.LENGTH_SHORT).show()
        }


        /*val ciudadPopayan = com.practice.francisco.appclima.models.Ciudad("Popayán", 15, "Soleado")
        val ciudadBerlin = com.practice.francisco.appclima.models.Ciudad("Berlin", 30, "Cielo despejado")

        if(ciudad =="ciudad-popayán"){
            //Mostrar informacion de Popayan.
            tvCiudad?.text = ciudadPopayan.nombre
            tvGrados?.text = ciudadPopayan.grados.toString()+"°"
            tvCiudad?.text = ciudadPopayan.estatus
        }else if (ciudad =="ciudad-berlin"){
            tvCiudad?.text = ciudadBerlin.nombre
            tvGrados?.text = ciudadBerlin.grados.toString()+"°"
            tvCiudad?.text = ciudadBerlin.estatus
        }else{
            Toast.makeText(this, "No se encuentra inforamcion sobre esta ciudad", Toast.LENGTH_SHORT).show()
        }*/
    }

    //Metodo para Volley
    private fun solicitudHTTPVolley(url:String){
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(Request.Method.GET, url, Response.Listener<String> {
                response ->
            try {
                Log.d("SolicitudHTTPVolley", response)
                val gson = Gson()
                val ciudad = gson.fromJson(response, Ciudad::class.java)

                tvCiudad?.text = ciudad.name
                tvGrados?.text = ciudad.main?.temp.toString()+"°"
                tvEstatus?.text = ciudad.weather?.get(0)?.description

                Log.d("GSON", ciudad.name)
            }catch (e: Exception){

            }
        }, Response.ErrorListener {  })
        queue.add(solicitud)
    }
}
