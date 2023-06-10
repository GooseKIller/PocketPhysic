package com.example.physicsformulas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(val context: Context, var data: ArrayList<DatabaseHelper.TerminDataModel>, val recyclerViewInterface: RecyclerViewInterface?): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.resycler_view_row_name, parent, false)
        return MyViewHolder(view, recyclerViewInterface)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data[position]
        holder.nameTermin.text = " ${item.id}. ${item.name}"
    }

    class MyViewHolder(itemView: View, recyclerViewInterfaces: RecyclerViewInterface?): RecyclerView.ViewHolder(itemView){
        var nameTermin: Button = itemView.findViewById(R.id.name_termins_button)
        init {
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION) recyclerViewInterfaces?.onItemClick(position)
            }
            nameTermin.setOnClickListener {
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION) recyclerViewInterfaces?.onItemClick(position)
            }
        }
    }
}