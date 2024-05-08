package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

class MainActivity2 : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_kotlin, true),
        Question(R.string.question_c_plus_lus, false),
        Question(R.string.question_python, true))

    private var currentIndex = 0

    private fun updateQuestion(){
        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean){
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer){
            R.string.correct_toast
        } else{
            R.string.incorrect_toast
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.previous_question)

        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        questionTextView.setOnClickListener{
            goToNextOrPrevQuestion(true)
        }

        nextButton.setOnClickListener{
            goToNextOrPrevQuestion(true)
        }

        prevButton.setOnClickListener{
            goToNextOrPrevQuestion(false)
        }

        val questionTextResId = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun goToNextOrPrevQuestion(goToNextAnswer: Boolean) {
        val next = if (goToNextAnswer) 1
        else -1

        currentIndex = (currentIndex + next) % questionBank.size

        if (currentIndex < 0){
            currentIndex = questionBank.size - 1
        }

        updateQuestion()
    }
}