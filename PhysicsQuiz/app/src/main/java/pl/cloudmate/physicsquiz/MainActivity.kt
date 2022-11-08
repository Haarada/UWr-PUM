package pl.cloudmate.physicsquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    // components
    val txtviewQuestion: TextView by lazy { findViewById(R.id.questiontxtview) }
    val txtviewQuestionNum: TextView by lazy { findViewById(R.id.nrpytaniatxtview) }
    val txtviewpytanie: TextView by lazy { findViewById(R.id.pytanietxtview)}

    val btntrue: Button by lazy { findViewById(R.id.truebtn) }
    val btnfalse: Button by lazy { findViewById(R.id.falsebtn) }
    val btncheat: Button by lazy { findViewById(R.id.cheatbtn) }

    val txtsum1: TextView by lazy { findViewById(R.id.txtsum1) }
    val txtsum2: TextView by lazy { findViewById(R.id.txtsum2) }
    val txtsum3: TextView by lazy { findViewById(R.id.txtsum3) }


    // vars
    var points = 0
    var currentQuestion = 0
    var correctAnsCount = 0
    var cheatsCount = 0

    // class with question
    val questions = Questions().questionList


    //changing text in textviews
    fun setQuestion(questionNumber: Int) {
        txtviewQuestion.text = questions[questionNumber].q
        txtviewQuestionNum.text = "${questionNumber+1} z ${questions.size}"
    }

    // makes sure not to grab question index out of bounds
    enum class qhEnum { Next, Previous }
    fun questionHandler(param: qhEnum) {
        when(param) {
            qhEnum.Next -> {
                if (currentQuestion >= questions.lastIndex) showSummary()
                else {
                    currentQuestion++
                    setQuestion(currentQuestion)
                }
            }
            else -> {/* should trigger error ¯\(ツ)/¯ */ }
        }
    }

    // function executed on true / false buttons
    fun checkAns(usrAns: Boolean, givenAns: Boolean) {
        if (usrAns == givenAns){
            points += 10
            correctAnsCount++
        }
        questionHandler(qhEnum.Next)
    }

    // function executed in cheat button ( creating new Activity)
    val QNUM = "pl.cloudmate.physicsquiz.QNUM"
    fun cheat() {
        points -= 15
        cheatsCount++
        //questionHandler(qhEnum.Next)
        val intent = Intent(this, CheatActivity::class.java).apply {
            putExtra(QNUM, currentQuestion.toString())
        }
        startActivity(intent)
    }

    // hiding parts of UI after passing past last question (executed in questionHandler)
    fun showSummary() {
        txtviewQuestion.visibility = View.INVISIBLE
        btntrue.visibility = View.INVISIBLE
        btnfalse.visibility = View.INVISIBLE
        btncheat.visibility = View.INVISIBLE
        txtviewpytanie.visibility = View.INVISIBLE
        txtviewQuestionNum.text = "To już koniec"

        txtsum1.text = "$points punktów"
        txtsum2.text = "$correctAnsCount poprawnych odpowiedzi"
        txtsum3.text = "$cheatsCount użytych podpowiedzi"

        txtsum1.visibility = View.VISIBLE
        txtsum2.visibility = View.VISIBLE
        txtsum3.visibility = View.VISIBLE
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setQuestion(0)

        btntrue.setOnClickListener() { checkAns(true, questions[currentQuestion].a) }
        btnfalse.setOnClickListener() { checkAns(false, questions[currentQuestion].a) }
        btncheat.setOnClickListener() { cheat() }

    }
}