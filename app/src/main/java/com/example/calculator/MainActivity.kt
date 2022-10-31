package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.calculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listeners()
    }

    fun listeners()
    {
        binding.btn0.setOnClickListener() { setTextFields("0") }
        binding.btn1.setOnClickListener() { setTextFields("1") }
        binding.btn2.setOnClickListener() { setTextFields("2") }
        binding.btn3.setOnClickListener() { setTextFields("3") }
        binding.btn4.setOnClickListener() { setTextFields("4") }
        binding.btn5.setOnClickListener() { setTextFields("5") }
        binding.btn6.setOnClickListener() { setTextFields("6") }
        binding.btn7.setOnClickListener() { setTextFields("7") }
        binding.btn8.setOnClickListener() { setTextFields("8") }
        binding.btn9.setOnClickListener() { setTextFields("9") }
        binding.btnV.setOnClickListener() { setTextFields("-") }
        binding.btnS.setOnClickListener() { setTextFields("+") }
        binding.btnM.setOnClickListener() { setTextFields("*") }
        binding.btnD.setOnClickListener() { setTextFields("/") }
        binding.btnLs.setOnClickListener() { setTextFields("(") }
        binding.btnRs.setOnClickListener() { setTextFields(")") }

        binding.btnAC.setOnClickListener()
        {
            binding.mathOperation.text = ""
            binding.resultText.text = ""
        }

        binding.btnB.setOnClickListener()
        {
            val txt = binding.mathOperation.text.toString()
            if(txt.isNotEmpty()) binding.mathOperation.text = txt.substring(0, txt.length - 1)
            binding.resultText.text = ""
        }

        binding.btnE.setOnClickListener()
        {
            try
            {
                val ex = ExpressionBuilder(binding.mathOperation.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if(result == longRes.toDouble())
                    binding.resultText.text = longRes.toString()
                else
                    binding.resultText.text = result.toString()
            }
            catch(e:Exception)
            {
                Log.d("MyLog", "message: ${e.message}")
            }
        }

    }

    fun setTextFields(str : String)
    {
        if( binding.resultText.text != "")
        {
            binding.mathOperation.text = binding.resultText.text

            binding.resultText.text = ""
        }
        binding.mathOperation.append(str)
    }
}