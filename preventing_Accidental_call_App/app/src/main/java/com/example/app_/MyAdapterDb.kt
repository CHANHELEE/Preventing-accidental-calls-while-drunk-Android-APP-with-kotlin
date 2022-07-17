package com.example.app_

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_.databinding.ListItemBinding
import com.example.app_.databinding.ListItemDbBinding

class MyAdapterDb(private var dataSet: MutableList<MyElement>): RecyclerView.Adapter<MyAdapterDb.MyViewHolder>() {

    class MyViewHolder(val binding: ListItemDbBinding):RecyclerView.ViewHolder(binding.root)

    override fun getItemCount()=dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder((ListItemDbBinding.inflate(LayoutInflater.from(parent.context),parent,false)))
    }

    fun setList(newList:MutableList<MyElement>){
        this.dataSet =newList
    }

    fun getElement(pos:Int) : MyElement{
        return  dataSet[pos]
    }
    private  lateinit var  itemClickListener: OnItemClickListener

    interface OnItemClickListener  {
        fun onClick (v:View , position: Int)
    }

    fun setItemClickListener(onItemClickListener: OnItemClickListener){
        this.itemClickListener= onItemClickListener
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.PhoneNum.text = dataSet[position].c1
        binding.date.text = dataSet[position].c2
        binding.msg.text = dataSet[position].c3
        binding.relationship.text = dataSet[position].c4
    }



}