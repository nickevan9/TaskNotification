package com.example.tasknotification.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasknotification.R
import com.example.tasknotification.callback.ColorClickListener
import com.example.tasknotification.data.local.Color
import de.hdodenhof.circleimageview.CircleImageView
import java.util.concurrent.Executors


/**
 * Created by nickevan on 18,October,2019
 */

class ColorAdapter constructor(
    val context: Context,
    private val mColorClickListener: ColorClickListener
) : ListAdapter<Color, ColorAdapter.ViewHolder>(
    AsyncDifferConfig.Builder<Color>(ColorDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = mInflater.inflate(R.layout.item_color, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(getItem(position))
        val color = getItem(position)
        holder.mImgColor.setColorFilter(android.graphics.Color.parseColor(color.colorString))
        if (color.checked) {
            holder.mImgChecked.visibility = View.VISIBLE
        } else {
            holder.mImgChecked.visibility = View.INVISIBLE
        }

        holder.mTvTitle.text = color.title
        holder.mTvTitle.setTextColor(android.graphics.Color.parseColor(color.colorString))

        holder.itemView.setOnClickListener {
            color.checked = !color.checked

            if (color.checked) {
                holder.mImgChecked.visibility = View.VISIBLE
            } else {
                holder.mImgChecked.visibility = View.INVISIBLE
            }

            mColorClickListener.onColorClick(position)
        }
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val mImgColor: CircleImageView = itemView.findViewById(R.id.imgColor)
        val mImgChecked: ImageView = itemView.findViewById(R.id.imgChecked)
        val mTvTitle: TextView = itemView.findViewById(R.id.tvColor)

    }
}

class ColorDiffCallBack : DiffUtil.ItemCallback<Color>() {
    override fun areItemsTheSame(oldItem: Color, newItem: Color): Boolean {
        return oldItem.colorString == newItem.colorString
    }

    override fun areContentsTheSame(oldItem: Color, newItem: Color): Boolean {
        return oldItem == newItem
    }
}
