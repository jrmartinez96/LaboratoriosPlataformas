package com.josemartinez.laboratorio2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

public class CostumArrayAdapter(arrayItems: ArrayList<Int>, context: Context) : BaseAdapter() {

    private val itemsA : ArrayList<Int>
    private val mInflator: LayoutInflater

    init {
        this.itemsA = arrayItems
        this.mInflator = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return itemsA.count()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItem(position: Int): Any {
        return itemsA[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val t: TextView
        if (convertView == null){
            view = this.mInflator.inflate(R.layout.list_row, parent, false)

            t = view.findViewById<TextView>(R.id.list_row_textView)

        } else {
            view = convertView
            t = view.findViewById<TextView>(R.id.list_row_textView)
        }

        if(this.itemsA[position] == 1){
            t.text = this.itemsA[position].toString() + " vuelta"
        } else {
            t.text = this.itemsA[position].toString() + " vueltas"
        }

        return view;
    }

}