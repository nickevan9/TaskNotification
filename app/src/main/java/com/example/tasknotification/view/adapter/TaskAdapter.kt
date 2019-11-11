package com.example.tasknotification.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tasknotification.R
import com.example.tasknotification.callback.TaskCallBackListener
import com.example.tasknotification.data.local.Task
import com.example.tasknotification.utils.convertDateToDateTime
import com.example.tasknotification.utils.convertDateToDateTime2
import com.example.tasknotification.utils.convertDateToTime
import com.example.tasknotification.utils.convertDateToTimeMarker
import com.google.android.material.chip.Chip
import java.util.concurrent.Executors


/**
 * Created by nickevan on 15,October,2019
 */

class TaskAdapter constructor(
    val context: Context, private val mOnTaskCallBackListener: TaskCallBackListener
) : ListAdapter<Task, TaskAdapter.ViewHolder>(
    AsyncDifferConfig.Builder<Task>(TaskDiffCallBack())
        .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
        .build()
) {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = mInflater.inflate(R.layout.item_task, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

        holder.mBtnIgnore.setOnClickListener {
            mOnTaskCallBackListener.ignoreTask(position)
        }

        holder.itemView.setOnClickListener {
            mOnTaskCallBackListener.detailTask(position)
        }

        holder.mCkTask.setOnClickListener {
            if (holder.mCkTask.isChecked) {
                mOnTaskCallBackListener.checkTask(position)
                getItem(position).statusCheck = 1
                holder.itemView.alpha = 0.5F
                holder.mCkTask.isEnabled = false
                holder.mBtnIgnore.isEnabled = false
            }
        }
    }

    override fun submitList(list: MutableList<Task>?) {
        super.submitList(list)
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val mCkTask: CheckBox = itemView.findViewById(R.id.ckTask)
        private val mTvTimeStart: TextView = itemView.findViewById(R.id.tvTimeStart)
        private val mTvTimeStartEnd: TextView = itemView.findViewById(R.id.tvTimeStartEnd)
        private val mTvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val mBtnIgnore: ImageView = itemView.findViewById(R.id.imgIgnore)
        private val mTvDescription: TextView = itemView.findViewById(R.id.tvDescription)
        private val mChip: Chip = itemView.findViewById(R.id.chipType)

        @SuppressLint("SetTextI18n")
        fun bind(task: Task) {
            mTvTimeStart.text = convertDateToTimeMarker(task.startTime)

            if (task.statusCheck == 1) {
                itemView.alpha = 0.5F
                mCkTask.isEnabled = false
                mBtnIgnore.isEnabled = false
                mCkTask.isChecked = true
            }

            val upperString = task.title.substring(0, 1).toUpperCase() + task.title.substring(1)

            mTvTitle.text = upperString
            mTvDescription.text = task.description

            if (task.startTime.date == task.endTime.date) {
                mTvTimeStartEnd.text =
                    convertDateToTime(task.startTime) + " - " + convertDateToTimeMarker(task.endTime)
            } else {
                mTvTimeStartEnd.text =
                    convertDateToDateTime2(task.startTime) + " " + context.resources.getString(R.string.toColor) + " " + convertDateToDateTime2(
                        task.endTime
                    )
            }

            mChip.text = task.nameProject

            when (task.status) {
                0 -> {
                    mCkTask.isChecked = false
                }
                1 -> {
                    mCkTask.isChecked = true
                    mCkTask.isEnabled = false
                }
                2 -> {
                    mCkTask.isEnabled = false
                    mCkTask.isChecked = false
                    mBtnIgnore.visibility = View.INVISIBLE
                }
            }


        }


    }
}

class TaskDiffCallBack : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.idTask == newItem.idTask
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}