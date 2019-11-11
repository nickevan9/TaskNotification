package com.example.tasknotification.view.home.tabtask


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.tasknotification.R
import com.example.tasknotification.callback.TaskCallBackListener
import com.example.tasknotification.utils.convertDateToDayTitle
import com.example.tasknotification.view.BaseFragment
import com.example.tasknotification.view.adapter.TaskAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.example.tasknotification.data.local.Task
import com.example.tasknotification.utils.SUCCESS
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar
import java.util.*


/**
 * A simple [Fragment] subclass.
 */
@Suppress("DEPRECATION")
class TaskFragment : BaseFragment(), TaskCallBackListener {
    override fun checkTask(position: Int) {
        Toast.makeText(context!!, "Checked position", Toast.LENGTH_SHORT).show()
        val task = mListTask[position]
        task.statusCheck = 1
        mTaskViewModel.insertTask(task)
    }

    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_task, container, false)
    }

    private val mTaskViewModel: TaskViewModel by viewModel()

    private var mTvToday: TextView? = null
    private var mRvToday: RecyclerView? = null
    private var mTaskAdapter: TaskAdapter? = null
    private var mLinearTask: LinearLayout? = null
    private var mCalendarCollapsible: CollapsibleCalendar? = null
    private val mCalendarStart = Calendar.getInstance()
    private val mCalendarEnd = Calendar.getInstance()
    private var mListTask = listOf<Task>()


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mTvToday = view.findViewById(R.id.tvToday)
        mRvToday = view.findViewById(R.id.rvTask)
        mLinearTask = view.findViewById(R.id.linear_task)
        mCalendarCollapsible = view.findViewById(R.id.calendar_view)

        mTaskAdapter = TaskAdapter(context!!, this)
        mRvToday!!.setHasFixedSize(true)
        mRvToday!!.adapter = mTaskAdapter

        mTvToday!!.text = resources.getString(R.string.today) + " " + convertDateToDayTitle(Date())

        mCalendarStart.time = Date()
        mCalendarStart.timeZone = TimeZone.getDefault()
        mCalendarStart.set(Calendar.HOUR_OF_DAY, 0)
        mCalendarStart.set(Calendar.MINUTE, 0)
        mCalendarStart.set(Calendar.SECOND, 0)
        mCalendarStart.set(Calendar.MILLISECOND, 0)


        mCalendarEnd.time = Date()
        mCalendarEnd.timeZone = TimeZone.getDefault()
        mCalendarEnd.set(Calendar.HOUR_OF_DAY, 23)
        mCalendarEnd.set(Calendar.MINUTE, 59)
        mCalendarEnd.set(Calendar.SECOND, 59)
        mCalendarEnd.set(Calendar.MILLISECOND, 999)


        mCalendarCollapsible!!.setCalendarListener(object : CollapsibleCalendar.CalendarListener {
            override fun onMonthChange() {

            }

            override fun onDataUpdate() {

            }

            override fun onWeekChange(position: Int) {

            }

            override fun onDaySelect() {

                mCalendarStart.set(
                    mCalendarCollapsible!!.selectedDay.year,
                    mCalendarCollapsible!!.selectedDay.month,
                    mCalendarCollapsible!!.selectedDay.day, 0, 0, 0
                )

                mCalendarEnd.set(
                    mCalendarCollapsible!!.selectedDay.year,
                    mCalendarCollapsible!!.selectedDay.month,
                    mCalendarCollapsible!!.selectedDay.day, 23, 59, 59
                )

                mHandlerLeak.post {
                    if (mCalendarStart.time.date == Date().date && mCalendarStart.time.date == Date().month) {
                        mTvToday!!.text =
                            resources.getString(R.string.today) + " " + convertDateToDayTitle(Date())
                    } else {
                        mTvToday!!.text = convertDateToDayTitle(mCalendarStart.time)
                    }
                }

                mTaskViewModel.loadTaskByDate(mCalendarStart.time, mCalendarEnd.time)
                    .observe(viewLifecycleOwner, Observer {
                        if (it != null) {
                            val resId = R.anim.layout_animation_slow
                            val animation = AnimationUtils.loadLayoutAnimation(context!!, resId)
                            mRvToday!!.layoutAnimation = animation
                            mTaskAdapter!!.submitList(it as MutableList<Task>?)
                        }
                    })
            }

            override fun onItemClick(v: View?) {

            }

        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mTaskViewModel.mAllTasks
            .observe(viewLifecycleOwner, Observer {
                mLinearTask!!.visibility = View.VISIBLE
                if (it != null) {
                    val resId = R.anim.layout_animation_slow
                    val animation = AnimationUtils.loadLayoutAnimation(context!!, resId)
                    mRvToday!!.layoutAnimation = animation
                    mTaskAdapter!!.submitList(it as MutableList<Task>?)
                    mListTask = it
                }
            })

        mTaskViewModel.showError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
        })

//        mTaskViewModel.showLoading.observe(viewLifecycleOwner, Observer {
//            if (it) {
////                showLoadingProgress()
//            } else {
////                hideLoading()
//                mLinearTask!!.visibility = View.VISIBLE
//            }
//        })

        mTaskViewModel.statusDB.observe(viewLifecycleOwner, Observer {
            if (it == SUCCESS) {
                Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun ignoreTask(position: Int) {

    }

    override fun detailTask(position: Int) {

    }

}
