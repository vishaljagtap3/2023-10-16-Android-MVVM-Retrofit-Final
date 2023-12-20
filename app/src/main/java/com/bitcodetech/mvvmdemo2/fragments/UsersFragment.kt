package com.bitcodetech.mvvmdemo2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.mvvmdemo2.R
import com.bitcodetech.mvvmdemo2.adapters.UsersAdapter
import com.bitcodetech.mvvmdemo2.databinding.AddUserFragmentBinding
import com.bitcodetech.mvvmdemo2.databinding.UsersFragmentBinding
import com.bitcodetech.mvvmdemo2.factory.MyViewModelFactory
import com.bitcodetech.mvvmdemo2.network.UsersApiService
import com.bitcodetech.mvvmdemo2.repository.UsersRepository
import com.bitcodetech.mvvmdemo2.viewmodels.UsersViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class UsersFragment : Fragment() {

    private lateinit var binding : UsersFragmentBinding

    private lateinit var usersViewModel : UsersViewModel
    private lateinit var usersAdapter : UsersAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UsersFragmentBinding.inflate(inflater)

        initViews()
        initViewModels()
        initAdapter()
        initObservers()
        initListeners()

        usersViewModel.fetchUsers()

        return binding.root
    }

    private fun initListeners() {
        binding.recyclerUsers.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    usersViewModel.fetchUsers()
                }
            }
        })

        binding.btnAddUser.setOnClickListener {
            addAddUserFragment()
        }
    }

    private fun addAddUserFragment() {

        val addUserFragment = AddUserFragment()

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, addUserFragment, null)
            .addToBackStack(null)
            .commit()


    }

    private fun initObservers() {
        usersViewModel.usersUpdateAvailableLiveData.observe(
            viewLifecycleOwner
        ) {
            if(it) {
                usersAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun initAdapter() {
        usersAdapter = UsersAdapter(usersViewModel.users)
        binding.recyclerUsers.adapter = usersAdapter
    }

    private fun initViewModels() {
        usersViewModel = ViewModelProvider(
            this,
            MyViewModelFactory(
                UsersRepository(
                    UsersApiService.getInstance()
                )
            )
        ).get(UsersViewModel::class.java)
    }

    private fun initViews() {
        binding.recyclerUsers.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

}