package com.wiryatech.adsintermediate.modules.watchlist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.wiryatech.adsintermediate.R
import com.wiryatech.adsintermediate.adapter.AllMovieAdapter
import com.wiryatech.adsintermediate.database.MovieHelper
import com.wiryatech.adsintermediate.model.MovieModel
import com.wiryatech.adsintermediate.modules.detail.DetailActivity
import com.wiryatech.adsintermediate.utils.MappingHelper
import kotlinx.android.synthetic.main.activity_all_movie.*
import kotlinx.android.synthetic.main.content_all_movie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class WatchlistActivity : AppCompatActivity() {

    private var dataList = ArrayList<MovieModel>()

    lateinit var movieHelper: MovieHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_movie)
        setSupportActionBar(toolbar)

        movieHelper = MovieHelper.getInstance(this)
        movieHelper.open()
    }

    private fun getData() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferredValue = async(Dispatchers.IO) {
                var cursor = movieHelper.queryAll()
                MappingHelper.mapCursorToArrayList(cursor)
            }
            dataList = deferredValue.await()
            initView()
        }
    }

    private fun initView() {
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

    override fun onResume() {
        super.onResume()
        getData()
    }
}