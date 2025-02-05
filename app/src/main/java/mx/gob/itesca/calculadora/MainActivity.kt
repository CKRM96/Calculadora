package mx.gob.itesca.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var twNumber1: TextView
    private lateinit var twNumber2: TextView
    private var operador: String = ""
    private var isSecondNumber = false // Controla si estamos ingresando el segundo número

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar TextViews
        twNumber1 = findViewById(R.id.tw_n1)
        twNumber2 = findViewById(R.id.tw_n2)


        // Asignar eventos a los botones
        setButtonListeners()
    }

    private fun setButtonListeners() {
        val numberButtons = mapOf(
            R.id.cero to "0", R.id.uno to "1", R.id.dos to "2",
            R.id.tres to "3", R.id.cuatro to "4", R.id.cinco to "5",
            R.id.seis to "6", R.id.siete to "7", R.id.ocho to "8", R.id.nueve to "9"
        )

        // Agregar funcionalidad a los botones de números
        for ((id, value) in numberButtons) {
            findViewById<Button>(id).setOnClickListener { addNumber(value) }
        }

        // Botones de operadores
        findViewById<Button>(R.id.suma).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.resta).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.multi).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.dividir).setOnClickListener { setOperator("/") }

        // Botón de resultado
        findViewById<Button>(R.id.resultado).setOnClickListener { calculate() }

        // Botón de borrar
        findViewById<Button>(R.id.borrar).setOnClickListener { clearFields() }
    }

    private fun addNumber(value: String) {
        if (!isSecondNumber) {
            if (twNumber1.text == "0") twNumber1.text = value
            else twNumber1.text = "${twNumber1.text}$value"
        } else {
            if (twNumber2.text == "0") twNumber2.text = value
            else twNumber2.text = "${twNumber2.text}$value"
        }
    }

    private fun setOperator(op: String) {
        if (twNumber1.text.isNotEmpty()) {
            operador = op
            isSecondNumber = true
        }
    }

    private fun calculate() {
        val num1 = twNumber1.text.toString().toDoubleOrNull()
        val num2 = twNumber2.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null || operador.isEmpty()) {
            twNumber1.text = "Error"
            return
        }

        val result = when (operador) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else "Error: División por 0"
            else -> "Error"
        }

        twNumber1.text = "$result"
    }

    private fun clearFields() {
        twNumber1.text = ""
        twNumber2.text = ""
        operador = ""
        isSecondNumber = false
    }
}
