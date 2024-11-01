package com.example.catchdeezballs

import android.app.Activity
import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.catchdeezballs.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var score = 0
    var imageListe = ArrayList<ImageView>()
    var runnable = Runnable {  }
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageListe.add(binding.imageView1)
        imageListe.add(binding.imageView2)
        imageListe.add(binding.imageView3)
        imageListe.add(binding.imageView4)
        imageListe.add(binding.imageView5)
        imageListe.add(binding.imageView6)
        imageListe.add(binding.imageView7)
        imageListe.add(binding.imageView8)
        imageListe.add(binding.imageView9)
        imageListe.add(binding.imageView10)
        imageListe.add(binding.imageView11)
        imageListe.add(binding.imageView12)

        hide()

        //Countdown Timer
        object : CountDownTimer(15000,1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.time.text = "Time: ${millisUntilFinished/1000}"
            }

            override fun onFinish() {
                binding.time.text = "Time is up!"
                handler.removeCallbacks(runnable)
                for (image in imageListe) {
                    image.visibility = View.INVISIBLE
                }

                var alert = AlertDialog.Builder(this@MainActivity)
                alert.setTitle("Game Over T_T")
                alert.setMessage("Wanna play one more round? O_o ")
                alert.setPositiveButton("Yes",DialogInterface.OnClickListener { dialog, which ->
                    var main1 = intent
                    finish()
                    startActivity(main1)
                })

                alert.setNegativeButton("No",DialogInterface.OnClickListener { dialog, which ->
                    Toast.makeText(this@MainActivity,"Game session is done !",Toast.LENGTH_SHORT).show()

                })
                alert.show()
            }
        }.start()

    }

    fun raise_score(view: View){

        score +=1
        binding.textView2.text = "Score : $score"
    }

    fun hide() {

        runnable = object : Runnable{
            override fun run() {
                for (image in imageListe) {
                    image.visibility = View.INVISIBLE
                }
                val randomindex = Random.nextInt(imageListe.size)
                imageListe[randomindex].visibility = View.VISIBLE

                handler.postDelayed(runnable,1200)

            }
        }
        handler.post(runnable)

    }





}