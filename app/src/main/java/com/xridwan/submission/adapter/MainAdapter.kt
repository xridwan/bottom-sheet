package com.xridwan.submission.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xridwan.submission.R
import com.xridwan.submission.databinding.GameItemLayoutBinding
import com.xridwan.submission.model.Game

class MainAdapter(private val game: ArrayList<Game>) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private lateinit var onItemCLickCallBack: OnItemCLickCallBack

    fun setOnItemClick(onItemCLickCallBack: OnItemCLickCallBack) {
        this.onItemCLickCallBack = onItemCLickCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.game_item_layout, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val animation =
            AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)

        holder.bind(game[position])
        holder.itemView.animation = animation
    }

    override fun getItemCount(): Int = game.size

    inner class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = GameItemLayoutBinding.bind(itemView)

        fun bind(game: Game) {
            Glide.with(itemView.context)
                .load(game.poster)
                .apply(RequestOptions())
                .into(binding.imgPoster)

            binding.tvGames.text = game.games

            itemView.setOnClickListener {
                onItemCLickCallBack.onItemClicked(game)
            }
        }
    }

    interface OnItemCLickCallBack {
        fun onItemClicked(data: Game)
    }
}