package com.example.calculadora

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.example.calculadora.databinding.ActivityMainBinding
import kotlin.math.exp
import kotlin.math.pow

class MainActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding : ActivityMainBinding
    var operandoUno: Int = 0;
    var operandoDos: Int = 0;
    var idOperacion: Int = 0;
    var operando: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonAC.setOnClickListener(this)
        binding.buttonCambioSignos.setOnClickListener(this)
        binding.buttonPorcentaje.setOnClickListener(this)
        binding.buttonDivision.setOnClickListener(this)
        binding.buttonNumSiete.setOnClickListener(this)
        binding.buttonNumOcho.setOnClickListener(this)
        binding.buttonNumNueve.setOnClickListener(this)
        binding.buttonMultiplica.setOnClickListener(this)
        binding.buttonNumCuatro.setOnClickListener(this)
        binding.buttonNumCinco.setOnClickListener(this)
        binding.buttonNumSeis.setOnClickListener(this)
        binding.buttonResta.setOnClickListener(this)
        binding.buttonNumUno.setOnClickListener(this)
        binding.buttonNumDos.setOnClickListener(this)
        binding.buttonNumTres.setOnClickListener(this)
        binding.buttonSumar.setOnClickListener(this)
        binding.buttonNumCero.setOnClickListener(this)
        binding.buttonIgual.setOnClickListener(this)
        binding.buttonDiezElevado?.setOnClickListener(this)
        binding.buttonEX?.setOnClickListener(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("calculatorTextV", binding.calculatorTextV.text.toString())
        outState.putInt("operandoUno", operandoUno)
        outState.putInt("operandoDos", operandoDos)
        outState.putInt("idOperacion", idOperacion)
        outState.putBoolean("operando", operando)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.calculatorTextV.text = savedInstanceState.getString("calculatorTextV","0")
        operandoUno = savedInstanceState.getInt("operandoUno", 0)
        operandoDos = savedInstanceState.getInt("operandoDos", 0)
        idOperacion = savedInstanceState.getInt("idOperacion", 0)
        operando = savedInstanceState.getBoolean("operando", true)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_AC-> clearCalc(v as Button)
            R.id.button_cambioSignos-> operation(v as Button)
            R.id.button_porcentaje-> operation(v as Button)
            R.id.button_division-> operation(v as Button)
            R.id.button_numSiete-> addNumero(v as Button)
            R.id.button_numOcho-> addNumero(v as Button)
            R.id.button_numNueve-> addNumero(v as Button)
            R.id.button_multiplica-> operation(v as Button)
            R.id.button_numCuatro-> addNumero(v as Button)
            R.id.button_numCinco-> addNumero(v as Button)
            R.id.button_numSeis-> addNumero(v as Button)
            R.id.button_resta-> operation(v as Button)
            R.id.button_numUno-> addNumero(v as Button)
            R.id.button_numDos-> addNumero(v as Button)
            R.id.button_numTres-> addNumero(v as Button)
            R.id.button_sumar-> operation(v as Button)
            R.id.button_numCero-> addNumero(v as Button)
            R.id.button_igual-> equal(v as Button)
            R.id.button_diezElevado-> operation(v as Button)
            R.id.button_eX-> operation(v as Button)
        }
    }

    fun addNumero(button: Button){
        if(!operando){
            binding.calculatorTextV.append(button.text)
        }else{
            binding.calculatorTextV.text = (button.text)
            operando = false
        }
    }
    fun operation(button: Button){
        operandoUno = binding.calculatorTextV.text.toString().toInt()
        when(button){
            binding.buttonSumar->{idOperacion = 0}
            binding.buttonResta->{idOperacion = 1}
            binding.buttonMultiplica->{idOperacion = 2}
            binding.buttonDivision->{idOperacion = 3}
            binding.buttonPorcentaje->{idOperacion = 4}
            binding.buttonCambioSignos->{idOperacion = 5}
            binding.buttonDiezElevado->{idOperacion = 6}
            binding.buttonEX->{idOperacion = 7}
        }
        operando = true
    }
    fun equal(button: Button){
        operandoDos = binding.calculatorTextV.text.toString().toInt()
        var result: Number = 0;
        when(idOperacion){
            0->{
                result = operandoUno.toInt() + operandoDos.toInt()
            }
            1->{
                result = operandoUno.toInt() - operandoDos.toInt()
            }
            2->{
                result = operandoUno.toInt() * operandoDos.toInt()
            }
            3->{
                result = operandoUno.toInt() / operandoDos.toInt()
            }
            4->{
                result = operandoUno.toDouble() * (operandoDos.toDouble()/100.0)
            }
            5->{
                result = -operandoDos.toInt()
            }
            6->{
                result = 10.0.pow(operandoDos.toDouble())
            }
            7->{
                result = exp(operandoDos.toDouble())
            }
        }
        binding.calculatorTextV.text = result.toString()
        operando = true
    }
    fun clearCalc(button: Button){
        binding.calculatorTextV.text = "0"
        operando = true
    }
}
