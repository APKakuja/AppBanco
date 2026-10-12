package com.example.appbanco.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appbanco.R

class Resultats : AppCompatActivity() {

    private lateinit var tvResultsStatus: TextView
    private lateinit var tvResultsNumber: TextView
    private lateinit var tvResultsDescription: TextView
    private lateinit var btnReCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultats)
        val result:Double = intent.extras?.getDouble("IMC_RESULT") ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initListeners() {

        btnReCalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {

        tvResultsNumber.text = result.toString()
        when(result){
            in 0.00..18.50 -> {
                tvResultsStatus.text = getString(R.string.low_weight)
                tvResultsStatus.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                tvResultsDescription.text = getString(R.string.low_weight_description)
            }
            in 18.51..24.99 -> {
                tvResultsStatus.text = getString(R.string.normal_weight)
                tvResultsStatus.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                tvResultsDescription.text = getString(R.string.normal_weight_description)
            }

            in 25.00..29.99 -> {
                tvResultsStatus.text = getString(R.string.over_weight)
                tvResultsStatus.setTextColor(ContextCompat.getColor(this, R.color.peso_sobrepeso))
                tvResultsDescription.text = getString(R.string.over_weight_description)
            }

            in 30.00..99.00 -> {
                tvResultsStatus.text = getString(R.string.obese_weight)
                tvResultsStatus.setTextColor(ContextCompat.getColor(this, R.color.peso_obesidad))
                tvResultsDescription.text = getString(R.string.obese_weight_description)
            }
            else -> {
                tvResultsStatus.text = getString(R.string.Error)
                tvResultsNumber.text = getString(R.string.Error)
                tvResultsDescription.text = getString(R.string.Error)
            }
        }
    }

    private fun initComponents() {
        tvResultsStatus = findViewById(R.id.tvResultsStatus)
        tvResultsNumber = findViewById(R.id.tvResultsNumber)
        tvResultsDescription = findViewById(R.id.tvResultsDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)
    }
}