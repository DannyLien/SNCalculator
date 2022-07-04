package com.tom.sncalculator

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.tom.sncalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: ActivityMainBinding

    val list = "0123456789ABCDEFGHJKLMNPRSTVWXYZ"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cancel.setOnClickListener {
            reset()
        }

    }

    fun calculator(view: View) {
        val snStr = binding.sncode.text.toString()
        var snNumber: Double = 0.0
        val snStrLeng = snStr.length
        Log.d(TAG, "calculator: snStrLeng = ${snStrLeng}")
        if (snStr.length == 0) {
            AlertDialogMessage("必須輸入數字0~9或英文字")

        } else {
            for (i in 0..snStr.length - 1) {
                val bitPow = Math.pow(32.0, i.toDouble())
                val inputStr = snStr.substring(snStrLeng - (i + 1), snStrLeng - i).uppercase()
                if (inputStr.equals("I") ||
                    inputStr.equals("O") ||
                    inputStr.equals("Q") ||
                    inputStr.equals("U")
                ) {
                    AlertDialogMessage("輸入錯誤,不可輸入I,O,Q,U")
                    break
                }
                val indexNumber = list.indexOf(inputStr)
                snNumber += (bitPow * indexNumber)
                Log.d(
                    TAG,
                    "calculator: inputStr = ${inputStr} , " +
                            "indexNumber =${indexNumber} , " +
                            "bitPow = ${bitPow} , " +
                            "snNumber = ${snNumber}"
                )
            }
            binding.result.text = "結果:" + snNumber.toInt().toString()
        }

    }

    fun reset() {
        binding.sncode.text.clear()
        binding.result.text = "結果:"
    }

    fun AlertDialogMessage(message: String) {
        AlertDialog.Builder(this)
            .setTitle("警告")
            .setMessage(message)
            .setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
                reset()
            })
            .show()
    }

}