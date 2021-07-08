package com.xridwan.submission.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.xridwan.submission.R
import com.xridwan.submission.databinding.SheetItemLayoutBinding
import com.xridwan.submission.model.Sheet

class SheetAdapter(private val sheetList: ArrayList<Sheet>) :
    RecyclerView.Adapter<SheetAdapter.SheetViewHolder>() {
    private lateinit var onItemCLickCallBack: OnItemCLickCallBack

    fun setOnItemClick(onItemCLickCallBack: OnItemCLickCallBack) {
        this.onItemCLickCallBack = onItemCLickCallBack
    }

    inner class SheetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = SheetItemLayoutBinding.bind(itemView)
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
            LayoutInflater.from(parent.context).inflate(R.layout.sheet_item_layout, parent, false)
        return SheetViewHolder(view)
    }

    override fun onBindViewHolder(holder: SheetViewHolder, position: Int) {
        holder.bind(sheetList[position])
    }

    override fun getItemCount(): Int = sheetList.size

    interface OnItemCLickCallBack {
        fun onItemClicked(data: Sheet)
    }
}