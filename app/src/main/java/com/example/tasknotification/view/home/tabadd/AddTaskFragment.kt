package com.example.tasknotification.view.home.tabadd

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.InputType.TYPE_NULL
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.example.tasknotification.R
import com.example.tasknotification.data.local.Project
import com.example.tasknotification.data.local.Task
import com.example.tasknotification.helper.customview.setReadOnly
import com.example.tasknotification.utils.SUCCESS
import com.example.tasknotification.utils.convertDateToDateTime
import com.example.tasknotification.utils.mapToTypedArray
import com.example.tasknotification.view.BaseFragment
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment
import kotlinx.android.synthetic.main.fragment_add_task.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class AddTaskFragment : BaseFragment(), View.OnClickListener {

    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }


    private val mAddTaskViewModel: AddTaskViewModel by viewModel()

    private var mEdFrom: EditText? = null
    private var mEdProject: EditText? = null
    private var mEdTitle: EditText? = null
    private var mEdDescription: EditText? = null
    private var mTvTimeStart: TextView? = null
    private var mImgShowCalendarStart: ImageView? = null
    private var mTvTimeEnd: TextView? = null
    private var mImgShowCalendarEnd: ImageView? = null
    private var mTvRemind: TextView? = null
    private var mImgRemindChoose: ImageView? = null
    private var mImgChooseSound: ImageView? = null
    private var mImgAddMember: ImageView? = null
    private var mBtnScheduleTask: Button? = null
    private var mAlertDialog: AlertDialog.Builder? = null

    private var mPositionDialogProject: Int = 0
    private var mPositionDialogRemind: Int = 0

    private var mListProject = listOf<Project>()
    private var mDateStart: Date? = Date()
    private var mDateEnd: Date? = Date()
    private var mTimeRemind: Int = 10
    private var mIdProject: String? = null
    private var mProject: Project? = null


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAlertDialog = AlertDialog.Builder(context!!)

        mEdFrom = view.findViewById(R.id.edFrom)
        mEdProject = view.findViewById(R.id.edToProject)
        mEdTitle = view.findViewById(R.id.edTitle)
        mEdDescription = view.findViewById(R.id.edDescription)
        mTvTimeStart = view.findViewById(R.id.tvTimeStart)
        mImgShowCalendarStart = view.findViewById(R.id.imgShowCalendarStart)
        mTvTimeEnd = view.findViewById(R.id.tvTimeEnd)
        mImgShowCalendarEnd = view.findViewById(R.id.imgShowCalendarEnd)
        mTvRemind = view.findViewById(R.id.tvRemind)
        mImgRemindChoose = view.findViewById(R.id.imgRemindChoose)
        mImgChooseSound = view.findViewById(R.id.imgSoundChoose)
        mImgAddMember = view.findViewById(R.id.imgAddMember)
        mBtnScheduleTask = view.findViewById(R.id.btnScheduleTask)

        mImgShowCalendarStart!!.setOnClickListener(this)
        mImgShowCalendarEnd!!.setOnClickListener(this)
        mImgRemindChoose!!.setOnClickListener(this)
        mImgChooseSound!!.setOnClickListener(this)
        mImgAddMember!!.setOnClickListener(this)
        mBtnScheduleTask!!.setOnClickListener(this)
        mEdProject!!.setOnClickListener(this)


        mTvTimeStart!!.text = convertDateToDateTime(mDateStart!!)
        mTvTimeEnd!!.text = convertDateToDateTime(mDateEnd!!)

        mEdFrom!!.setReadOnly(true, TYPE_NULL)
        mEdFrom!!.setText("me")
        mEdProject!!.setReadOnly(true, TYPE_NULL)

        val resId = R.anim.layout_animation
        val animation = AnimationUtils.loadLayoutAnimation(context!!, resId)
        relative_add!!.layoutAnimation = animation

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mAddTaskViewModel.mAllProject.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            mListProject = it
        })

        mAddTaskViewModel.showError.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            Toast.makeText(context!!, it.toString(), Toast.LENGTH_SHORT).show()
        })

        mAddTaskViewModel.statusDB.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == SUCCESS) {
                mEdFrom!!.setText("")
                mEdProject!!.setText("")
                mEdDescription!!.setText("")
                mEdTitle!!.setText("")
                Toast.makeText(context!!, "Success add task", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context!!, "Failed add task", Toast.LENGTH_SHORT).show()
            }
        })
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.edToProject -> {
                showProject()
            }

            R.id.imgShowCalendarEnd -> {
                showCalenderEnd()
            }

            R.id.imgShowCalendarStart -> {
                showCalenderStart()
            }

            R.id.imgRemindChoose -> {
                showRemind()
            }
            R.id.imgSoundChoose -> {
                chooseSound()
            }
            R.id.imgAddMember -> {
                addMember()
            }
            R.id.btnScheduleTask -> {
                scheduleTask()
            }
        }
    }

    private fun showProject() {

        val mArrayProject = mListProject.mapToTypedArray {
            it.title
        }
        mAlertDialog!!.setTitle("List project")

        mAlertDialog!!.setSingleChoiceItems(
            mArrayProject, mPositionDialogProject
        ) { _, which ->
            mPositionDialogProject = which

        }

        mAlertDialog!!.setPositiveButton(
            "Done"
        ) { _, _ ->
            run {
                mAlertDialog!!.setCancelable(true)
                mEdProject!!.setText(mListProject[mPositionDialogProject].title)
                mProject = mListProject[mPositionDialogProject]
                mIdProject = mListProject[mPositionDialogProject].idProject
            }
        }

        mAlertDialog!!.show()


    }

    private fun scheduleTask() {
        if (mEdTitle!!.text.isNotEmpty() && mEdProject!!.text.isNotEmpty() && mEdDescription!!.text.isNotEmpty()
            && mDateStart != null && mDateEnd != null && mIdProject != null
        ) {

            val task = Task()

            task.idProject = mIdProject!!
            task.idProject = mProject!!.idProject
            task.nameProject = mProject!!.title
            task.title = mEdTitle!!.text.toString()
            task.startTime = mDateStart!!
            task.endTime = mDateEnd!!
            task.remindTime = mTvRemind!!.text.toString()
            task.description = mEdDescription!!.text.toString()

            mAddTaskViewModel.insertTask(task)
        } else {
            Toast.makeText(context!!, "null", Toast.LENGTH_SHORT).show()
        }

    }

    private fun addMember() {
        Toast.makeText(context!!, "Coming soon !!!", Toast.LENGTH_SHORT).show()
    }

    private fun chooseSound() {
        Toast.makeText(context!!, "Coming soon !!!", Toast.LENGTH_SHORT).show()
    }


    @SuppressLint("SetTextI18n")
    private fun showRemind() {

        val mListRemind = resources.getStringArray(R.array.time_remind)

        mAlertDialog!!.setTitle(getString(R.string.remind_time))


        mAlertDialog!!.setSingleChoiceItems(
            mListRemind, mPositionDialogRemind
        ) { _, which ->
            mPositionDialogRemind = which
        }

        mAlertDialog!!.setPositiveButton(
            "Done"
        ) { _, _ ->
            run {
                mAlertDialog!!.setCancelable(true)
                mTimeRemind = mListRemind[mPositionDialogRemind].substring(0, 2).toInt()
                mTvRemind!!.text =
                    resources.getString(R.string.before) + " " + mListRemind[mPositionDialogRemind]
            }
        }

        mAlertDialog!!.show()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showCalenderStart() {
        val dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
            "Start time",
            "Done",
            ""
        )

        val date = LocalDateTime.now()

        dateTimeDialogFragment.startAtCalendarView()
        dateTimeDialogFragment.set24HoursMode(true)
        dateTimeDialogFragment.minimumDateTime =
            GregorianCalendar(date.year, date.monthValue, date.dayOfMonth).time
        dateTimeDialogFragment.maximumDateTime =
            GregorianCalendar(2025, Calendar.DECEMBER, 31).time
        dateTimeDialogFragment.setDefaultDateTime(
            GregorianCalendar(
                date.year,
                date.monthValue,
                date.dayOfMonth,
                date.hour,
                date.minute
            ).time
        )

        try {
            dateTimeDialogFragment.simpleDateMonthAndDayFormat =
                SimpleDateFormat("dd MMMM", Locale.getDefault())
        } catch (e: SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException) {

        }

        dateTimeDialogFragment.setOnButtonClickListener(object :
            SwitchDateTimeDialogFragment.OnButtonClickListener {
            override fun onPositiveButtonClick(date: Date) {
                mDateStart = date
                mTvTimeStart!!.text = convertDateToDateTime(date)
            }

            override fun onNegativeButtonClick(date: Date) {

            }
        })


        dateTimeDialogFragment.show(activity!!.supportFragmentManager, "dialog_time")


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showCalenderEnd() {
        val dateTimeDialogFragment = SwitchDateTimeDialogFragment.newInstance(
            "End time",
            "Done",
            ""
        )

        val date = LocalDateTime.now()

        dateTimeDialogFragment.startAtCalendarView()
        dateTimeDialogFragment.set24HoursMode(true)
        dateTimeDialogFragment.minimumDateTime =
            GregorianCalendar(date.year, date.monthValue, date.dayOfMonth).time
        dateTimeDialogFragment.maximumDateTime =
            GregorianCalendar(2025, Calendar.DECEMBER, 31).time
        dateTimeDialogFragment.setDefaultDateTime(
            GregorianCalendar(
                date.year,
                date.monthValue,
                date.dayOfMonth,
                date.hour,
                date.minute
            ).time
        )

        try {
            dateTimeDialogFragment.simpleDateMonthAndDayFormat =
                SimpleDateFormat("dd MMMM", Locale.getDefault())
        } catch (e: SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException) {

        }

        dateTimeDialogFragment.setOnButtonClickListener(object :
            SwitchDateTimeDialogFragment.OnButtonClickListener {
            override fun onPositiveButtonClick(date: Date) {
                mDateEnd = date
                mTvTimeEnd!!.text = convertDateToDateTime(date)
            }

            override fun onNegativeButtonClick(date: Date) {

            }
        })


        dateTimeDialogFragment.show(activity!!.supportFragmentManager, "dialog_time")


    }


}
