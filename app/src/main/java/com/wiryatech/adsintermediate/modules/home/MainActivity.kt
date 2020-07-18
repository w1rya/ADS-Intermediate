package com.wiryatech.adsintermediate.modules.home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.wiryatech.adsintermediate.modules.list.AllMovieActivity
import com.wiryatech.adsintermediate.R
import com.wiryatech.adsintermediate.adapter.MovieAdapter
import com.wiryatech.adsintermediate.data.Dummy
import com.wiryatech.adsintermediate.database.DatabaseContract
import com.wiryatech.adsintermediate.model.MovieModel
import com.wiryatech.adsintermediate.modules.detail.DetailActivity
import com.wiryatech.adsintermediate.modules.login.LoginActivity
import com.wiryatech.adsintermediate.modules.watchlist.WatchlistActivity
import com.wiryatech.adsintermediate.utils.Const
import com.wiryatech.adsintermediate.utils.Const.LOGIN_CODE
import com.wiryatech.adsintermediate.utils.UserPreference
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var dataList = ArrayList<MovieModel>()
    private lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userPreference = UserPreference(this)
        rv_movie_shimmer.startShimmer()

        val handler = Handler()
        handler.postDelayed({
            initListener()
            getData()

            rv_movie_shimmer.stopShimmer()
            rv_movie_shimmer.visibility = View.GONE
            rv_movie.visibility = View.VISIBLE
        }, 3000)
    }

    private fun initListener() {
        btn_login.setOnClickListener {
            if (userPreference.getStatusUser()) {
                startActivity(Intent(this, WatchlistActivity::class.java))
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, LOGIN_CODE)
            }
        }
    }

    private fun initView() {
        rv_movie.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        rv_movie.adapter =
            MovieAdapter(dataList) {
                val intent = Intent(this, DetailActivity::class.java)
                    .putExtra("data", it)
                startActivity(intent)
            }

        tv_view_all.setOnClickListener{
            val intent = Intent(this, AllMovieActivity::class.java)
                .putExtra("data", dataList)
            startActivity(intent)
        }

        if (userPreference.getStatusUser()) {
            btn_login.text = userPreference.getUserName()
            iv_logout.visibility = View.VISIBLE
        } else {
            btn_login.text = "LOGIN"
            iv_logout.visibility = View.INVISIBLE
        }
    }

    private fun getData() {
        for (i in Dummy.titleMovie.indices) {
            dataList.add(
                MovieModel(
                    i+1,
                    Dummy.titleMovie[i],
                    Dummy.descMovie[i],
                    Dummy.genreMovie[i],
                    Dummy.posterMovie[i],
                    Dummy.trailerMovie[i],
                    Dummy.ratingMovie[i]
                )
            )
        }

        initView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == LOGIN_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                startActivity(Intent(this, WatchlistActivity::class.java))
                initView()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initView()
    }
}