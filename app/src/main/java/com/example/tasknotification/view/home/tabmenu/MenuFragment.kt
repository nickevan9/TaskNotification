package com.example.tasknotification.view.home.tabmenu


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.tasknotification.R
import com.example.tasknotification.callback.ProjectCallBackListener
import com.example.tasknotification.data.local.Project
import com.example.tasknotification.view.BaseFragment
import com.example.tasknotification.view.adapter.ProjectAdapter
import com.example.tasknotification.view.dialog.DialogProjectFragment
import com.google.android.material.card.MaterialCardView
import org.koin.android.viewmodel.ext.android.viewModel

import android.view.animation.AnimationUtils


/**
 * A simple [Fragment] subclass.
 */
class MenuFragment : BaseFragment(), ProjectCallBackListener, View.OnClickListener {


    override fun setLayout(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    private val mMenuViewModel: MenuViewModel by viewModel()

    private var navController: NavController? = null
    private var mProjectAdapter: ProjectAdapter? = null

    private var mRvProject: RecyclerView? = null
    private var mCardAddProject: MaterialCardView? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        mProjectAdapter = ProjectAdapter(context!!, this)

        mRvProject = view.findViewById(R.id.rvProject)
        mCardAddProject = view.findViewById(R.id.card_add)

        mRvProject!!.layoutManager = GridLayoutManager(context!!, 2)

        mRvProject!!.adapter = mProjectAdapter

        mCardAddProject!!.setOnClickListener(this)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mMenuViewModel.mAllProject.observe(viewLifecycleOwner, Observer {
            if (it!= null){
                val resId = R.anim.layout_animation
                val animation = AnimationUtils.loadLayoutAnimation(context!!, resId)
                mRvProject!!.layoutAnimation = animation
                mProjectAdapter!!.submitList(it as MutableList<Project>?)
            }

        })

    }


    override fun detailProject(position: Int) {

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.card_add -> {
                val dialogProject = DialogProjectFragment.newInstance()
                dialogProject.show(childFragmentManager, "DialogProjectFragment")
            }
        }
    }

}
