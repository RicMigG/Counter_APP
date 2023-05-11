package com.gitlab.ricardomgago.counter

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    // Use late init to initialize our views to be able to be used everywhere.
    private lateinit var numberText: TextView;
    private lateinit var summary: TextView;
    private lateinit var numberInput: EditText;
    private lateinit var interval: EditText;


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to our buttons
        val submitButton: Button = findViewById(R.id.main_activity_btn_submit);
        val randomButton: Button = findViewById(R.id.main_activity_btn_random_number);
        val incrementButton: Button = findViewById(R.id.main_activity_btn_increment);
        val decrementButton: Button = findViewById(R.id.main_activity_btn_decrement);

        // Set the values to our views initialized with late init;
        numberText = findViewById(R.id.main_activity_tv_number);
        numberInput = findViewById(R.id.main_activity_et_number_input);
        interval = findViewById(R.id.main_activity_et_interval);
        summary = findViewById(R.id.main_activity_tv_summary);

        // Set click listeners for each button
        submitButton.setOnClickListener() {
            submitNumber();
        };

        randomButton.setOnClickListener() {
            generateRandomNumber();
        };

        incrementButton.setOnClickListener() {
            changeNumber("+");
        };

        decrementButton.setOnClickListener() {
            changeNumber("-");
        };


    }

    // Get a number from an edit text view and display it to the screen
    private fun submitNumber() {
        var startingNumber = numberInput.text.toString();

        // Check to see if the user left the edit text, if so hardcode 10
        if (startingNumber == "") {
            startingNumber = "10";
        }

        numberText.text = startingNumber;

        // Clear edit text after submission
        numberInput.setText("");
        hideKeyboard();
    }

    // Generate a random number and display it to the screen
    private fun generateRandomNumber() {
        val randomNumber = (-100..100).random();
        numberText.text = randomNumber.toString();
    }

    private fun changeNumber(operation: String) {
        val currentNumber = numberText.text.toString().toInt();
        var incrementValue = interval.text.toString();

        if (incrementValue == "") {
            incrementValue = "1";
        }

        if (operation == "+") {
            val newNumber = currentNumber + incrementValue.toInt();
            numberText.text = newNumber.toString();

            summary.text = "$currentNumber + $incrementValue = $newNumber";
        } else {
            val newNumber = currentNumber - incrementValue.toInt();
            numberText.text = newNumber.toString();

            summary.text = "$currentNumber - $incrementValue = $newNumber";
        }
        hideKeyboard()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager;
        imm.hideSoftInputFromWindow(numberText.windowToken, 0);
    }
}