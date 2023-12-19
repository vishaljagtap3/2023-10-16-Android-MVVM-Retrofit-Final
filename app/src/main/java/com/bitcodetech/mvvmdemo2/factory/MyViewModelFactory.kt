package com.bitcodetech.mvvmdemo2.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bitcodetech.mvvmdemo2.repository.UsersRepository
import com.bitcodetech.mvvmdemo2.viewmodels.UsersViewModel

class MyViewModelFactory(
    private val usersRepository: UsersRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UsersViewModel(usersRepository) as T
    }

}