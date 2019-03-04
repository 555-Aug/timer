package com.example.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val handler = Handler()
    var timeValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText.text = "3:00"
        val runnable = object :Runnable {
            override fun run() {
                timeValue++
                timeToText(timeValue)?.let {
                    timerText.text = it
                }
                handler.postDelayed(this, 1000)
            }
        }

        var isRunning = true
        playStop.setOnClickListener {
            when (isRunning) {
                true -> {
                    isRunning = false
                    handler.post(runnable)
                    playStop.setImageResource(R.drawable.ic_stop_black_24dp)
                }
                false -> {
                    isRunning = true
                    handler.removeCallbacks(runnable)
                    playStop.setImageResource(R.drawable.ic_play_arrow_black_24dp)
                }
            }
        }
    }

    private fun timeToText(time: Int = 0): String? {
        return when {
            time < 0 -> null
            time == 0 -> "00:00"
            else -> {
                val m = time % 3600 / 60
                val s = time % 60
                "%1$02d:%2$02d".format(m, s)
            }
        }
    }
}
