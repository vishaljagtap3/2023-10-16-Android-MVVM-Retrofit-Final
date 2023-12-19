package com.bitcodetech.mvvmdemo2.repository

import com.bitcodetech.mvvmdemo2.models.User
import com.bitcodetech.mvvmdemo2.models.UserPostModel
import com.bitcodetech.mvvmdemo2.models.UserPostResponse
import com.bitcodetech.mvvmdemo2.network.UsersApiService

class UsersRepository(
    private val usersApiService: UsersApiService
) {

    suspend fun fetchUsers(pageNumber: Int) : ArrayList<User>{
        return usersApiService.fetchUsers(pageNumber).users
    }

    suspend fun addUser(
        userPostModel: UserPostModel
    ) : UserPostResponse {
        return usersApiService.addUser(userPostModel)
    }

}