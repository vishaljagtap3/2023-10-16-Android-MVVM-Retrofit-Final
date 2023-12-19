package com.bitcodetech.mvvmdemo2.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bitcodetech.mvvmdemo2.models.User
import com.bitcodetech.mvvmdemo2.models.UserPostModel
import com.bitcodetech.mvvmdemo2.repository.UsersRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersViewModel(
    private val usersRepository: UsersRepository
) : ViewModel() {

    //val usersLiveData = MutableLiveData<ArrayList<User>>( ArrayList<User>())
    val usersUpdateAvailableLiveData = MutableLiveData<Boolean>()
    val users = ArrayList<User>()

    var pageNumber : Int = 0

    fun fetchUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val users = usersRepository.fetchUsers(++pageNumber)

            withContext(Dispatchers.Main) {
                this@UsersViewModel.users.addAll(users)
                usersUpdateAvailableLiveData.postValue(true)
            }
        }

    }

    val userPostedLiveData = MutableLiveData<Boolean>()
    fun addUser(userPostModel : UserPostModel) {
        CoroutineScope(Dispatchers.IO).launch {
            val responsePostUser = usersRepository.addUser(userPostModel)

            Log.e("tag", responsePostUser.toString())

            withContext(Dispatchers.Main) {
                userPostedLiveData.postValue(true)
            }
        }
    }

}