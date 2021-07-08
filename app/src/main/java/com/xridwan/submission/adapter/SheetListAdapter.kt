package com.xridwan.submission.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xridwan.submission.R
import com.xridwan.submission.databinding.SheetItemListBinding
import com.xridwan.submission.model.Sheet

class SheetListAdapter(private val sheetList: ArrayList<Sheet>) :
    RecyclerView.Adapter<SheetListAdapter.SheetViewHolder>() {

    private val limit: Int = 5

    private lateinit var onItemCLickCallBack: OnItemCLickCallBack

    fun setOnItemClick(onItemCLickCallBack: OnItemCLickCallBack) {
        this.onItemCLickCallBack = onItemCLickCallBack
    }

    inner class SheetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = SheetItemListBinding.bind(itemView)
        fun bind(sheets: Sheet) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(sheets.icon)
                    .apply(RequestOptions())
                    .into(binding.sheetImg)

                sheetTitle.text = sheets.feature

                itemView.setOnClickListener {
                    onItemCLickCallBack.onItemClicked(sheets)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SheetViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sheet_item_list, parent, false)
        return SheetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SheetViewHolder, position: Int) {
        val animation =
            AnimationUtils.loadAnimation(holder.itemView.context, android.R.anim.slide_in_left)

        holder.bind(sheetList[position])
        holder.itemView.animation = animation
    }

    override fun getItemCount(): Int = if (sheetList.size > limit) limit else sheetList.size

    interface OnItemCLickCallBack {
        fun onItemClicked(data: Sheet)
    }
}