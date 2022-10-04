package com.danuk.mp3pl

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.danuk.mp3pl.databinding.MusicItemBinding

class MusicAdapter : RecyclerView.Adapter<MusicAdapter.MusicHolder>() {

    val musicList = ArrayList<MusicItem>()

    class MusicHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = MusicItemBinding.bind(item)
        fun bind(musicItem: MusicItem) = with(binding){
            listImgTrack.setBackgroundResource(musicItem.obl)
            listSongName.text = musicItem.song_name
            listSingerName.text = musicItem.singer_name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.music_item, parent, false)
        return MusicHolder(view)
    }

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.bind(musicList[position])
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    fun addMusic(musicItem: MusicItem){
        musicList.add(musicItem)
        notifyDataSetChanged()
    }

    fun addAll(list: List<MusicItem>){
        musicList.clear()
        musicList.addAll(list)
        notifyDataSetChanged()
    }

}