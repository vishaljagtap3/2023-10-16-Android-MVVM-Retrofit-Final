package com.bitcodetech.mvvmdemo2.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.mvvmdemo2.R
import com.bitcodetech.mvvmdemo2.databinding.UserViewBinding
import com.bitcodetech.mvvmdemo2.models.User
import com.bumptech.glide.Glide

class UsersAdapter(
    private val users : ArrayList<User>
) : RecyclerView.Adapter<UsersAdapter.UserViewHolder>(){

    inner class UserViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val userViewBinding : UserViewBinding
        init {
            userViewBinding = UserViewBinding.bind(view)
        }
    }

    override fun getItemCount() = users.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_view, null)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        //val user = users[position]

        holder.userViewBinding.user = users[position]

        /*holder.userViewBinding.txtUsername.text = "${user.firstName} ${user.lastName}"
        holder.userViewBinding.txtEmail.text = user.email*/

       /* Glide.with(holder.itemView)
            .load(users[position].avatar)
            .circleCrop()
            .error(R.mipmap.ic_launcher)
            .placeholder(R.mipmap.ic_launcher)
            .into(holder.userViewBinding.imgUser)*/
    }

}