package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import  retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private val baseURL="https://jsonplaceholder.typicode.com/"
    lateinit var mainBinding: ActivityMainBinding
    lateinit var adapter: PostAdapter

    var postList=ArrayList<Posts>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding=ActivityMainBinding.inflate(layoutInflater)
        val view=mainBinding.root
        setContentView(view)
           mainBinding.recycleView.layoutManager=LinearLayoutManager(this)
      showPosts()
    }
    fun showPosts()
    {
    val retrofit= Retrofit.Builder().baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        val retrofitAPI:RetrofitAPI=retrofit.create(RetrofitAPI::class.java)
        val call:Call<List<Posts>> = retrofitAPI.getAllPosts()
        call.enqueue(object:Callback<List<Posts>>{
            override fun onResponse(call: Call<List<Posts>>,response:Response<List<Posts>> ){
                if(response.isSuccessful)
                {
                    mainBinding.progressBar.isVisible=false
                    mainBinding.recycleView.isVisible=true
                    postList=response.body() as  ArrayList<Posts>
                    adapter= PostAdapter(postList)
                    mainBinding.recycleView.adapter=adapter
                }



            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(applicationContext,t.localizedMessage,Toast.LENGTH_LONG).show()
            }


        })


    }
}