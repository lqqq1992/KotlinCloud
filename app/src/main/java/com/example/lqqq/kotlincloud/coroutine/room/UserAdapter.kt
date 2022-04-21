package com.example.lqqq.kotlincloud.coroutine.room

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.lqqq.kotlincloud.databinding.ItemUserBinding
import java.util.ArrayList

class UserAdapter(private val context: Context): RecyclerView.Adapter<UserViewHolder>() {
    private val data = ArrayList<User>()
    fun setData(users:List<User>){
        this.data.clear()
        this.data.addAll(users)
        Log.d("---","$data")
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(context),parent,false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data[position]
        val binding = holder.binding as ItemUserBinding
        binding.msg.text = "${item.id}:${item.firstName}${item.lastName}-${item.age}"
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
class UserViewHolder(val binding:ViewBinding) : RecyclerView.ViewHolder(binding.root) {}