package com.example.androidlab3kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

const val apikey = "pub_35008c8a5c1fcd6cbde65485c4744be3f8506"
class MainActivity : AppCompatActivity() {
    var data = ArrayList<News>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerview = findViewById<RecyclerView>(R.id.NewsView)
        recyclerview.layoutManager = LinearLayoutManager(this)
        val adapter = TextAdapter(data)
        recyclerview.adapter = adapter
        //requestNewsData("computers")


    }

    fun clickSearchButton(view: View){
        val EdText = findViewById<EditText>(R.id.TitleNewsText)
        val new = EdText.text.toString()
        if (new != ""){
            requestNewsData(new)
//            val recyclerview = findViewById<RecyclerView>(R.id.NewsView)
//            val adapter: TextAdapter = recyclerview.adapter as TextAdapter

//            val resultArray: ArrayList<News> = requestNewsData(new)
//            Log.d("MyArray", resultArray.toString())
//            adapter.SetAllItems(resultArray)
        }
    }
    private fun requestNewsData(search: String){
//        val url1 = "https://newsdata.io/api/1/news?apikey=pub_35008c8a5c1fcd6cbde65485c4744be3f8506&q=computers"
        val url = "https://newsdata.io/api/1/news?apikey=" +
                apikey +
                "&q="+
                search +
                "&language=en"

//        Log.d("MyUrl:  ", url)
//        Log.d("MyUrl1: ", url1)
        val que = Volley.newRequestQueue(baseContext)
        var res = ""
        val request = StringRequest(
            Request.Method.GET,
            url,
            {result -> parseNewsData(result) },
            {err -> Log.d("MyLog","Error: $err")}
        )
        que.add(request)
    }

    private fun parseNewsData(result: String) {
        val resultList = ArrayList<News>()
        val mainNews = JSONObject(result)
        val newsArray = mainNews.getJSONArray("results")
        for (i in 0 until newsArray.length()) {
            val fullNews = newsArray[i] as JSONObject
            val item = News(
                    fullNews.getString("title"),
                    fullNews.getString("link")
                    )
            resultList.add(item)
        }

        data = resultList
        val recyclerview = findViewById<RecyclerView>(R.id.NewsView)
        val adapter: TextAdapter = recyclerview.adapter as TextAdapter
//        Log.d("MyComplete", resultList.toString())
        adapter.SetAllItems(resultList)
    }

}

data class News(
    var title: String,
    var link: String
)