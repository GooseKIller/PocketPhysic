package com.example.physicsformulas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewFormulas(val context: Context, val data:ArrayList<DatabaseHelper.FormulaDataModel>): RecyclerView.Adapter<RecyclerViewFormulas.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.recycler_view_row_formula, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.formula.text = item.formula
        holder.formulaName.text = item.name
        holder.termins.text = item.termins.replace(",", ",\n")
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var formulaName:TextView = itemView.findViewById(R.id.formula_name)
        var formula:TextView = itemView.findViewById(R.id.formula)
        var termins:TextView = itemView.findViewById(R.id.termins)
    }
}