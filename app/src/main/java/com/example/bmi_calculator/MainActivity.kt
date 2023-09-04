package com.example.bmi_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calcBtn = findViewById<Button>(R.id.btnCalculate)

        calcBtn.setOnClickListener {
            val wt = weightText.text.toString();
            val ht = heightText.text.toString();
            if (validateInp(wt,ht)) {
                val bmi = wt.toFloat() / ((ht.toFloat() / 100) * (ht.toFloat() / 100));
                val bmi2Dig = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Dig)
            }
        }
    }

    private fun validateInp(wt:String?,ht:String?):Boolean{
        return when{
            wt.isNullOrEmpty() && ht.isNullOrEmpty()->{
                Toast.makeText(this, "Please Enter Both Height and Weight", Toast.LENGTH_SHORT).show()
                return false
            }
            wt.isNullOrEmpty()->{
                Toast.makeText(this, "Please Enter Your Weight!", Toast.LENGTH_SHORT).show()
                return false
            }
            ht.isNullOrEmpty()->{
                Toast.makeText(this, "Please Enter Your Height!", Toast.LENGTH_SHORT).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayResult(bmi: Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDes = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString();
        info.text = "Normal Range is 18.5 - 24.9"
        info.setTextColor(ContextCompat.getColor(this, R.color.white))

        var resultText = ""
        var color = 0

        when{
            bmi<18.50 ->{
                resultText = "UnderWeight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText = "OverWeight"
                color = R.color.over_weight
            }
            bmi>29.99->{
                resultText = "Obese"
                color = R.color.obese
            }
        }
        resultDes.setTextColor(ContextCompat.getColor(this,color))
        resultDes.text = resultText
    }
}