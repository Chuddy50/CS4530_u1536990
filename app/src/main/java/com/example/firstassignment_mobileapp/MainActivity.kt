package com.example.firstassignment_mobileapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_BUTTON_TEXT = "com.example.a1buttons.EXTRA_BUTTON_TEXT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttons: List<Button> = listOf(
            findViewById(R.id.btnOne),
            findViewById(R.id.btnTwo),
            findViewById(R.id.btnThree),
            findViewById(R.id.btnFour),
            findViewById(R.id.btnFive)
        )

        val onAnyButtonClicked: (Button) -> Unit = { btn ->
            val label = btn.text.toString()
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(EXTRA_BUTTON_TEXT, label)
            }
            startActivity(intent)
        }

        buttons.forEach { button ->
            button.setOnClickListener { onAnyButtonClicked(button) }
        }
    }
}
