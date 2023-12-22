package com.example.androidlab3kotlin

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView

class TextAdapter(private val mList: ArrayList<News>): RecyclerView.Adapter<TextAdapter.ViewHolder>() {
    var data: ArrayList<News> = mList
    @SuppressLint("NotifyDataSetChanged")
    set(newValue) {
        field = newValue
        notifyDataSetChanged()
    }


    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = data[position]
        val context = holder.itemView.context

        holder.Title.text = news.title
        holder.Link.text = news.link
    }

    @SuppressLint("NotifyDataSetChanged")
    fun SetItems(Item: News){
        data.add(Item)
        notifyDataSetChanged()
    }

    fun SetAllItems(ItemList: ArrayList<News>){
        for (i in 0 until ItemList.size){
            SetItems(ItemList[i])
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun ClearItems(){
        data.clear()
        notifyDataSetChanged()
    }
    class ViewHolder(newsView: View) : RecyclerView.ViewHolder(newsView){
        val Title: TextView = newsView.findViewById(R.id.Title)
        val Link: TextView = newsView.findViewById(R.id.Link)
    }

}

