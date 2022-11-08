package pl.cloudmate.physicsquiz

import android.content.Intent
import android.content.Intent.CATEGORY_BROWSABLE
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class CheatActivity : AppCompatActivity() {

    // components
    val txtviewQuestion: TextView by lazy { findViewById(R.id.txtviewquestion) }
    val txtviewOdp: TextView by lazy { findViewById(R.id.txtviewodp) }
    val btnsearch: Button by lazy { findViewById(R.id.btnsearch) }
    val btnReturn: Button by lazy { findViewById(R.id.btnreturn) }

    // class with question
    val questions = Questions().questionList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        // load sent Extra from main Activity
        val QNUM = "pl.cloudmate.physicsquiz.QNUM"
        val questionNum = intent.getStringExtra(QNUM)?.toInt()

        fun textHandler() {
            txtviewQuestion.text = questions[questionNum!!].q
            txtviewOdp.text = if(questions[questionNum!!].a) "Prawdą" else "Fałszem"

        }

        // return to main Activity
        fun goBack() {
            Intent().apply { }
            finish()
        }



        textHandler()
        btnReturn.setOnClickListener() { goBack() }

        // opening web browser with question
        btnsearch.setOnClickListener() {
            val url = "https://www.google.com/search?q=${questions[questionNum!!].q}"
            val internetIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                addCategory(CATEGORY_BROWSABLE)
            }

            if (internetIntent.resolveActivity(packageManager) != null) {
                startActivity(internetIntent)
            }
        }

    }


}