package com.example.mad_lab_pro_f

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val questions = listOf(
        Question("What is the capital of France?", "Paris", "London", "Berlin", "Rome", 1),
        Question("What is 2 + 2?", "3", "4", "5", "6", 2),
        Question("Who wrote 'To Kill a Mockingbird'?", "Harper Lee", "J.K. Rowling", "Ernest Hemingway", "Mark Twain", 1)
    )
    private var currentQuestionIndex = 0
    private var selectedAnswer = -1 // Track the selected option (1-4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to the views
        val questionText = findViewById<TextView>(R.id.questionText)
        val option1 = findViewById<Button>(R.id.option1)
        val option2 = findViewById<Button>(R.id.option2)
        val option3 = findViewById<Button>(R.id.option3)
        val option4 = findViewById<Button>(R.id.option4)
        val submitBtn = findViewById<Button>(R.id.submitBtn)

        // Display the first question
        displayQuestion(questionText, option1, option2, option3, option4)

        // Set click listeners for each option
        option1.setOnClickListener { selectedAnswer = 1 }
        option2.setOnClickListener { selectedAnswer = 2 }
        option3.setOnClickListener { selectedAnswer = 3 }
        option4.setOnClickListener { selectedAnswer = 4 }

        submitBtn.setOnClickListener {
            if (selectedAnswer != -1) {
                if (questions[currentQuestionIndex].correctOption == selectedAnswer) {
                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
                }
                // Move to the next question
                currentQuestionIndex++
                if (currentQuestionIndex < questions.size) {
                    selectedAnswer = -1 // Reset selection for the next question
                    displayQuestion(questionText, option1, option2, option3, option4)
                } else {
                    Toast.makeText(this, "Quiz Completed!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayQuestion(
        questionText: TextView, option1: Button, option2: Button,
        option3: Button, option4: Button
    ) {
        val currentQuestion = questions[currentQuestionIndex]
        questionText.text = currentQuestion.question
        option1.text = currentQuestion.option1
        option2.text = currentQuestion.option2
        option3.text = currentQuestion.option3
        option4.text = currentQuestion.option4
    }


    private fun getSelectedAnswer(
        option1: Button, option2: Button, option3: Button, option4: Button
    ): Int {
        return when {
            option1.isPressed -> 1
            option2.isPressed -> 2
            option3.isPressed -> 3
            option4.isPressed -> 4
            else -> -1
        }
    }

    data class Question(
        val question: String,
        val option1: String,
        val option2: String,
        val option3: String,
        val option4: String,
        val correctOption: Int
    )
}
