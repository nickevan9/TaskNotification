package com.example.tasknotification.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasknotification.R
import com.example.tasknotification.callback.ProjectCallBackListener
import com.example.tasknotification.data.local.Project
import java.util.concurrent.Executors




/**
 * Created by nickevan on 17,October,2019
 */
class ProjectAdapter constructor(
    val context: Context, private val mOnProjectCallBackListener: ProjectCallBackListener
) : ListAdapter<Project, ProjectAdapter.ViewHolder>(
    AsyncDifferConfig.Builder<Project>(ProjectDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = mInflater.inflate(R.layout.item_project, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: MutableList<Project>?) {
        super.submitList(if (list != null) ArrayList(list) else null)
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val mImgIcon: ImageView = itemView.findViewById(R.id.img_icon)

        private val mTvName: TextView =
            itemView.findViewById(R.id.tvNameTask)
        private val mTvTotalTask: TextView = itemView.findViewById(R.id.tvTotalTask)

        @SuppressLint("DefaultLocale")
        fun bind(project: Project) {

            val layerDrawable =
                ContextCompat.getDrawable(context, R.drawable.circle_custom) as LayerDrawable

            val mShape: GradientDrawable =
                layerDrawable.findDrawableByLayerId(R.id.color_circle).current as GradientDrawable

            if (project.color.isEmpty()) {
                mShape.setColor(Color.parseColor("#000000"))
            } else {
                mShape.setColor(Color.parseColor(project.color))

                mImgIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.circle_custom
                    )
                )
            }
//            val upperString = project.title.substring(0, 1).toUpperCase() + project.title.substring(1)

            mTvName.text = project.title
            mTvTotalTask.text = project.totalTask.toString()
        }


    }
}

class ProjectDiffCallBack : DiffUtil.ItemCallback<Project>() {
    override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem.idProject == newItem.idProject
    }

    override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
        return oldItem == newItem
    }
}