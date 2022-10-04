package com.danuk.mp3pl

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.danuk.mp3pl.databinding.ActivityMainBinding
import com.danuk.mp3pl.databinding.ActivityPlayerMiniBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bindingPlayer: ActivityPlayerMiniBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        fun setPlayer(){
            //val intentPlayer = Intent(this, PlayerMiniActivity::class.java)
            //intentPlayer.putExtra("isExist",  false)
            setContentView(bindingPlayer.root)
//            if (!(intentPlayer.extras?.get("isExist") as Boolean)){
//                val view: View = layoutInflater.inflate(R.layout.activity_player_mini, binding.playingGroup, false)
//                binding.root.     Как вызвать view/activity не привязывая его к этому активити
//                binding.playingGroup.addView(view)
//                binding.playingGroup.visibility = View.VISIBLE
//                intentPlayer.putExtra("isExist",  true)
//            }
        }
//        findViewById<ImageButton>(R.id.line_location_2).setOnClickListener{
        binding.lineLocation2.setOnClickListener{
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
            Log.d("MyLog","StartListActivity")
        }
//        findViewById<ImageButton>(R.id.line_location_1).setOnClickListener{
        binding.lineLocation3.setOnClickListener{
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
//        findViewById<ImageButton>(R.id.image_main_test).setOnClickListener{
        binding.imageMainTest.setOnClickListener{
            setPlayer()
            binding.playingGroup.findViewById<ImageButton>(R.id.play_btn).callOnClick()
        }
    }
}