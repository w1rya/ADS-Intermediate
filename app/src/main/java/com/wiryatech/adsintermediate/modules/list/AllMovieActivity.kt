package com.wiryatech.adsintermediate.modules.list

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wiryatech.adsintermediate.R
import com.wiryatech.adsintermediate.adapter.AllMovieAdapter
import com.wiryatech.adsintermediate.model.MovieModel
import com.wiryatech.adsintermediate.modules.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_all_movie.*
import kotlinx.android.synthetic.main.content_all_movie.*

class AllMovieActivity : AppCompatActivity() {

    private var dataList = ArrayList<MovieModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_movie)
        setSupportActionBar(toolbar)

        dataList = intent.getParcelableArrayListExtra("data")

        rv_all_movie.layoutManager = LinearLayoutManager(this)
        rv_all_movie.adapter =
            AllMovieAdapter(dataList) {
                val intent = Intent(
                    this,
                    DetailActivity::class.java
                )
                    .putExtra("data", it)
                startActivity(intent)
            }
    }
}