package com.bitcodetech.mvvmdemo2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.mvvmdemo2.R
import com.bitcodetech.mvvmdemo2.factory.MyViewModelFactory
import com.bitcodetech.mvvmdemo2.models.UserPostModel
import com.bitcodetech.mvvmdemo2.network.UsersApiService
import com.bitcodetech.mvvmdemo2.repository.UsersRepository
import com.bitcodetech.mvvmdemo2.viewmodels.UsersViewModel

class AddUserFragment : Fragment() {

    private lateinit var edtName : EditText
    private lateinit var edtJob : EditText
    private lateinit var btnAddUser : Button

    private lateinit var usersViewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_user_fragment, null)

        initViews(view)
        initViewModels()
        initListeners()
        initObservers()

        return view
    }

    private fun initObservers() {
        usersViewModel.userPostedLiveData.observe(
            viewLifecycleOwner
        ) {
            removeCurrentFragment()
        }
    }

    private fun removeCurrentFragment() {
        parentFragmentManager.popBackStack()
    }

    private fun initListeners() {
        btnAddUser.setOnClickListener {
            usersViewModel.addUser(
                UserPostModel(
                    edtName.text.toString(),
                    edtJob.text.toString()
                )
            )
        }
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

    private fun initViews(view : View) {
        edtJob = view.findViewById(R.id.edtJob)
        edtName = view.findViewById(R.id.edtName)
        btnAddUser = view.findViewById(R.id.btnAddUser)
    }

}