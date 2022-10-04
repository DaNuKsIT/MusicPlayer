package com.danuk.mp3pl

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.danuk.mp3pl.databinding.ActivityListBinding

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private val adapter = MusicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("MyLog", "ActivityList is created")

        binding.lineLocation1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.lineLocation3.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        val intentPlayer = Intent(this, PlayerMiniActivity::class.java)
        intentPlayer.putExtra("isExist",  false)
        fun setPlayer() {
            if (!(intentPlayer.extras?.get("isExist") as Boolean)) {
                val view: View = layoutInflater.inflate(R.layout.activity_player_mini, binding.playingGroup, false)
                binding.playingGroup.addView(view)
                binding.playingGroup.visibility = View.VISIBLE
                intentPlayer.putExtra("isExist", true)
            }
        }
        setPlayer()

//        var dirID = arrayListOf(R.raw.music1, R.raw.music2)
        var i = 0
        fun initMusic() {
            binding.apply {
                recyclerView.layoutManager = LinearLayoutManager(this@ListActivity)
                recyclerView.adapter = adapter
                binding.addButton.setOnClickListener {
                    if (i > 1) {
                        i = 0
                    }
                    Log.d("MyLog", "ButtonADD")
                    val music = MusicItem(R.drawable.img_track, "$i - Песня", "Исполнитель")
                    Log.d("MyLog", "$i music is ran")
                    adapter.addMusic(music)
                    i++
                }
            }
        }
        initMusic()
        Log.d("MyLog", "RunInit")

        //Клик на песню
//        fun getPlayingMusic(musicIndex: Int){
//            var intent = Intent(this, PlayerMiniActivity::class.java)
//            intent.putExtra("MusicPlay", musicIndex)
//        }


//        Log.d("MyLog", "Test")
//        val arrayMusic = arrayListOf<MusicItem>()
    }

}
