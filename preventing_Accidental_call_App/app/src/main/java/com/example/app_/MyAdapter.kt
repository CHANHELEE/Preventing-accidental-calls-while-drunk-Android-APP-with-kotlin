package com.example.app_


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_.databinding.ListItemBinding

class MyAdapter(private val dataSet:Array<ImageElement>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    class MyViewHolder(val binding: ListItemBinding, var counter:Int): RecyclerView.ViewHolder(binding.root) {}
    var count=0
    override fun getItemCount() =dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        count++
        Log.d("Tag","Viewholder NUm: $count")
        return MyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false),count)
    }

    /*fun addItem(item:String){
        dataSet.add(item)
        this.notifyDataSetChanged()
        this.notifyItemInserted(dataSet.size-1)
    }*/

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding
        binding.imageContent.setImageResource(dataSet[position].image)
        binding.itemText2.text=dataSet[position].msg
        binding.itemRoot.setOnClickListener {
            Log.d("Tag","position $position counter ${holder.counter} clicked")
        }
        /*binding.itemRoot.setOnLongClickListener {
            Log.d("TAG","position $position long clicked")
            dataSet.removeAt(position)
            this.notifyDataSetChanged()
            this.notifyItemRemoved(position)
            this.notifyItemRangeChanged(position,dataSet.size-position)
            true
        }*/
    }
}