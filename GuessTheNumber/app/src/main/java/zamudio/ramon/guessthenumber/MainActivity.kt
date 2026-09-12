package zamudio.ramon.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var maxNumber : Int= 100
    var minNumber : Int= 0
    var num : Int = 0

    var won : Boolean= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val guessings : TextView = findViewById<TextView>(R.id.guessings)
        val down : Button = findViewById<Button>(R.id.btn_down)
        val up : Button = findViewById<Button>(R.id.btn_up)
        val generate :Button = findViewById<Button>(R.id.generate)
        val guessed : Button = findViewById<Button>(R.id.guessed)

        generate.setOnClickListener {
            num = Random.nextInt(minNumber,maxNumber)
            guessings.setText(num.toString())
            guessed.visibility = View.VISIBLE
            guessed.setText("Guessed")
        }

        up.setOnClickListener {
            minNumber = num
            if(chekingLimits()){
                num = Random.nextInt(minNumber,maxNumber)
                guessings.setText(num.toString())
            }else{
                guessings.setText("no puede ser")
            }

        }

        down.setOnClickListener {
            maxNumber = num
            if(chekingLimits()){
                num = Random.nextInt(minNumber,maxNumber)
                guessings.setText(num.toString())
            }else{
                guessings.setText("no puede ser")
            }

        }

        guessed.setOnClickListener {
            if(!won){
                guessings.setText("Adiviné tu número es el: "+num)
                guessed.setText("volver a jugar")
                won = true
            }else{
                generate.visibility = View.VISIBLE
                guessings.setText("tap generate to start")
                guessed.visibility = View.GONE

            }

        }

    }

    fun chekingLimits(): Boolean{
        return minNumber!=maxNumber
    }
    fun restValues(){
        minNumber = 0
        maxNumber = 100
        won = false
        num = 0
    }


}