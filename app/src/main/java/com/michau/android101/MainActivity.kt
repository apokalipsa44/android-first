package com.michau.android101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

const val STATE_PENDING_OPERATION = "PnedingOperation"
const val STATE_FIRST_NUMBER = "FirstNumber"
const val STATE_IS_FIEST_NUMBER = "false"

class MainActivity : AppCompatActivity() {
    var firstNumber: Double? = null
    var secondNumber: Double = 0.0
    var pendingOperation: String = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val listener = View.OnClickListener { v ->
            val b = v as Button
            newNumber_textView.append(b.text)
        }
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)

        val operationListener = View.OnClickListener { v ->
            val operation = (v as Button).text.toString()
            val value = newNumber_textView.text.toString()
            if (value.isNotEmpty()) {
                performOperation(operation, value)
            }
            pendingOperation = operation
            operator_textView.text = operation
        }

        buttonDiv.setOnClickListener(operationListener)
        buttonEquals.setOnClickListener(operationListener)
        buttonPlus.setOnClickListener(operationListener)
        buttonMinus.setOnClickListener(operationListener)
        buttonMultiply.setOnClickListener(operationListener)
        buttonDiv.setOnClickListener(operationListener)

    }

    fun performOperation(operation: String, value: String) {
        if (firstNumber == null) {
            firstNumber = value.toDouble()
        } else {
            secondNumber = value.toDouble()
            if (pendingOperation == "=") {
                pendingOperation = operation
            }
            when (pendingOperation) {
                "=" -> firstNumber = secondNumber
                "+" -> firstNumber = firstNumber!! + secondNumber
                "-" -> firstNumber = firstNumber!! - secondNumber
                "*" -> firstNumber = firstNumber!! * secondNumber
                "/" -> {
                    if (secondNumber == 0.0) {
                        firstNumber = Double.NaN
                    } else {
                        firstNumber = firstNumber!! / secondNumber
                    }
                }
            }
            result_editText.setText(firstNumber.toString())
            newNumber_textView.setText("")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (firstNumber != null) {
            outState.putDouble(STATE_FIRST_NUMBER, firstNumber!!)
            outState.putBoolean(STATE_IS_FIEST_NUMBER, true)
        }
        outState.putString(STATE_PENDING_OPERATION, pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        firstNumber = if (savedInstanceState.getBoolean(STATE_IS_FIEST_NUMBER, false)) {
            savedInstanceState.getDouble(STATE_FIRST_NUMBER)
        } else {
            null
        }
        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION)!!
        operator_textView.text = pendingOperation
    }
}
