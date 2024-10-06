package com.isabella.stackedburgers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnCadastrar: Button
    private lateinit var btnCardapio: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        btnCadastrar = findViewById(R.id.btnCadastrar)
        btnCardapio = findViewById(R.id.btnCardapio)

        btnCadastrar.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,CadastrarFragment())
                .commit()
        }

        btnCardapio.setOnClickListener {
            val intent = Intent(this, CardapioActivity::class.java)
            startActivity(intent)
        }
    }
}