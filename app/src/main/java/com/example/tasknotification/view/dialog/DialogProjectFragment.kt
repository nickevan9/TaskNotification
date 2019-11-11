package com.example.tasknotification.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasknotification.R
import com.example.tasknotification.callback.ColorClickListener
import com.example.tasknotification.data.local.Color
import com.example.tasknotification.data.local.Project
import com.example.tasknotification.utils.SUCCESS
import com.example.tasknotification.view.adapter.ColorAdapter
import com.example.tasknotification.view.home.tabmenu.MenuViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

/**
 * Created by nickevan on 17,October,2019
 */


class DialogProjectFragment : DialogFragment(), View.OnClickListener,
    ColorClickListener {


    companion object {
        fun newInstance() = DialogProjectFragment()
    }

    private val mMenuViewModel: MenuViewModel by viewModel()
    private var mColorAdapter: ColorAdapter? = null

    private var mEdName: EditText? = null
    private var mRvColor: RecyclerView? = null
    private var mEdDescription: EditText? = null
    private var mBtnOk: Button? = null
    private val mListColor = mutableListOf<Color>()
    private var mProject: Project? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_project, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mColorAdapter = ColorAdapter(context!!, this)
        mProject = Project()

        mEdName = view.findViewById(R.id.edNameProject)
        mEdDescription = view.findViewById(R.id.edDescription)
        mRvColor = view.findViewById(R.id.rvColor)
        mBtnOk = view.findViewById(R.id.btnOk)

        mRvColor!!.layoutManager = GridLayoutManager(context, 5)
        mRvColor!!.setHasFixedSize(true)
        mRvColor!!.adapter = mColorAdapter

        mListColor.add(Color("#3a7ae0", "Blue", true))
        mListColor.add(Color("#c21930", "Red"))
        mListColor.add(Color("#d1ff05", "Yellow"))
        mListColor.add(Color("#08034d", "Purple"))
        mListColor.add(Color("#629982", "Gray"))
        mListColor.add(Color("#000000", "Black"))
        mListColor.add(Color("#2e2525", "Brown"))
        mListColor.add(Color("#c97200", "Orange"))

        mProject!!.color = mListColor[0].colorString

        mBtnOk!!.setOnClickListener(this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mColorAdapter!!.submitList(mListColor)
        mMenuViewModel.statusDB.observe(viewLifecycleOwner,androidx.lifecycle.Observer {
            if (it == SUCCESS){
                Toast.makeText(context!!,getString(R.string.success_project),Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context!!,getString(R.string.failed_project),Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()

        val params = dialog!!.window!!.attributes
        params.width = (resources.displayMetrics.widthPixels * 0.9).toInt()
        params.height = android.view.WindowManager.LayoutParams.WRAP_CONTENT
        dialog!!.window!!.attributes = params
    }


    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnOk -> {
                if (mEdName!!.text.isNotEmpty() && mEdDescription!!.text.isNotEmpty()) {
                    mProject!!.dateDay = Date()
                    mProject!!.title = mEdName!!.text.toString()
                    mProject!!.description = mEdDescription!!.text.toString()

                    Toast.makeText(context!!, mProject.toString(), Toast.LENGTH_SHORT).show()
                    mMenuViewModel.insert(mProject!!)
                    dismiss()

                } else {
                    Toast.makeText(context!!, "Nhap vao thong tin", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onColorClick(position: Int) {
        if (mListColor[position].checked) {
            mProject!!.color = mListColor[position].colorString
        }
    }


}