package com.danuk.mp3pl

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.ImageButton
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class PlayerMiniActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_player_mini)

            Log.d("MyLog", "ActivityPlayer is created")

            val mediaPlayer: MediaPlayer = MediaPlayer.create(this,R.raw.music1)

//            val myUri: Uri = .... // initialize Uri here
//            val mediaPlayer = MediaPlayer().apply {
//                setAudioAttributes(
//                    AudioAttributes.Builder()
//                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
//                        .setUsage(AudioAttributes.USAGE_MEDIA)
//                        .build()
//                )
//                setDataSource(applicationContext, myUri)
//                prepare()
//                start()
//            }

            val playBtn = findViewById<ImageButton>(R.id.play_btn)
            val seekBar = findViewById<SeekBar>(R.id.seek_bar)

            seekBar.progress = 0
            seekBar.max = mediaPlayer.duration

            fun playPause(){
                if(mediaPlayer.isPlaying){
                    Log.d("MyLog", "Pause music")
                    mediaPlayer.pause()
                    playBtn.setBackgroundResource(R.drawable.play_arrow)
                }
                else {
                    Log.d("MyLog", "Play music")
                    mediaPlayer.start()
                    playBtn.setBackgroundResource(R.drawable.pause)
                }
            }

            playPause()
            Log.d("MyLog", "Play music")

            playBtn.setOnClickListener{
                playPause()
            }

            seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                    if(changed){
                        mediaPlayer.seekTo(pos)
                    }
                }
                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }
            })

            /*
                fun createTimeLabel(time: Int): String{
                    var timeLabel = ""
                    val min = time / 1000 / 60
                    val sec = time / 1000 % 60

                    timeLabel = "$min:"
                    if(sec < 10) timeLabel += "0"
                    timeLabel += sec

                    return timeLabel
                }*/

            @SuppressLint("HandlerLeak")
            val handler = object : Handler(){
                override fun handleMessage(msg: Message) {
                    val currentPosition = msg.what

                    seekBar.progress = currentPosition

                    //    var elapsedTime = createTimeLabel(currentPosition)
                    //    elapsedTimeLabel.text = elapsedTime

                    //    var remainingTime = createTimeLabel(mediaPlayer.duration - currentPosition)
                    //    remainingTimeLabel.text = "-$remainingTime"

                }
            }

            Thread(Runnable {
                while (true){
                    try{
                        val msg = Message()
                        msg.what = mediaPlayer.currentPosition
                        handler.sendMessage(msg)
                        Thread.sleep(1)
                    } catch (_: InterruptedException){

                    }
                }
            }).start()

        }
}

