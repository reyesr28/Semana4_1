package com.example.semana4_1

import Beans.Usuarios
import Interface.PlaceHolderApi
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit  var service:PlaceHolderApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev.formandocodigo.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service=retrofit.create<PlaceHolderApi>(PlaceHolderApi::class.java)

        getAllUsers();
    }

    private fun getAllUsers() {
        service.getListado().enqueue(object: Callback<List<Usuarios>>{
            override fun onResponse(
                call: Call<List<Usuarios>>,
                response: Response<List<Usuarios>>
            ) {
                val usu=response?.body()
                val TextView=findViewById<TextView>(R.id.txtRes)
                if(usu!=null){
                    for(item in usu){
                        var text: String=""
                        text=text +("Codigo: ${item.codigo} \n")
                        text+=("Nombre: ${item.nombre} \n")
                        text+=("Edad: ${item.edad} \n")
                        text+=("Correo: ${item.correo} \n\n")
                        TextView.append(text)
                    }
                }
            }
            override fun onFailure(call: Call<List<Usuarios>>, t: Throwable) {
                t?.printStackTrace()
            }
        })
    }
}













