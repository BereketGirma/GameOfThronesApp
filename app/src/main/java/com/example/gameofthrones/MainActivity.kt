package com.example.gameofthrones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient

import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers



private lateinit var photoList: MutableList<String>
private lateinit var names:MutableList<String>
private lateinit var families:MutableList<String>
private lateinit var titles:MutableList<String>
private lateinit var rvcharacters : RecyclerView
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDogImageURL()

        rvcharacters = findViewById(R.id.char_list)
        photoList = mutableListOf()
        names = mutableListOf()
        families = mutableListOf()
        titles = mutableListOf()
    }

    private fun getDogImageURL() {
        val client = AsyncHttpClient()

        client["https://thronesapi.com/api/v2/Characters", object : JsonHttpResponseHandler() {


            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                val character = json.jsonArray

                for(i in 0 until character.length()){
                    val photos = character.getJSONObject(i).getString("imageUrl")
                    val name = character.getJSONObject(i).getString("fullName")
                    val family = character.getJSONObject(i).getString("family")
                    val title = character.getJSONObject(i).getString("title")
                    photoList.add(photos)
                    names.add(name)
                    families.add(family)
                    titles.add(title)

                }
                val adapter = infoAdapater(photoList,names,families,titles)
                rvcharacters.adapter = adapter
                rvcharacters.layoutManager = LinearLayoutManager(this@MainActivity)

                rvcharacters.addItemDecoration(DividerItemDecoration(this@MainActivity,LinearLayoutManager.VERTICAL))

                Log.d("Character Success", "$json")
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                throwable: Throwable?
            ) {
                Log.d("API Call Error", errorResponse)
            }
        }]
    }
}