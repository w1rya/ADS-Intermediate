package com.wiryatech.adsintermediate.modules.detail

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.wiryatech.adsintermediate.R
import com.wiryatech.adsintermediate.database.DatabaseContract.NoteColumns
import com.wiryatech.adsintermediate.database.MovieHelper
import com.wiryatech.adsintermediate.model.MovieModel
import com.wiryatech.adsintermediate.modules.login.LoginActivity
import com.wiryatech.adsintermediate.utils.Const.LOGIN_CODE
import com.wiryatech.adsintermediate.utils.UserPreference
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    lateinit var data: MovieModel
    private lateinit var movieHelper: MovieHelper
    private var values = ContentValues()

    private var statusFav = false
    lateinit var userPreference: UserPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        data = intent.getParcelableExtra("data")

        userPreference = UserPreference(this)

        movieHelper = MovieHelper.getInstance(this)
        movieHelper.open()

        initView()
        initListener()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, bundle: Intent?) {
        super.onActivityResult(requestCode, resultCode, bundle)

        if (requestCode == LOGIN_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                if (statusFav) {
                    movieHelper.deleteById(data.id.toString())
                    iconFav(false)
                } else {
                    values.put(NoteColumns._ID, data.id)
                    values.put(NoteColumns.TITLE, data.title)
                    values.put(NoteColumns.DESC, data.desc)
                    values.put(NoteColumns.GENRE, data.genre)
                    values.put(NoteColumns.POSTER, data.poster)
                    values.put(NoteColumns.TRAILER, data.trailer)
                    values.put(NoteColumns.RATING, data.rating)

                    movieHelper.insert(values)
                    iconFav(true)
                }
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, LOGIN_CODE)
            }
        }
    }
    private fun initView() {
        tv_title.text = data.title
        tv_genre.text = data.genre
        tv_desc.text = data.desc

        checkStatusFav()
    }

    private fun initListener() {
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        videoView.setVideoURI(Uri.parse("android.resource://" +packageName+ "/" +data.trailer))
        videoView.start()

        iv_fav.setOnClickListener {
            if (userPreference.getStatusUser()) {
                if (statusFav) {
                    movieHelper.deleteById(data.id.toString())
                    iconFav(false)
                } else {
                    values.put(NoteColumns._ID, data.id)
                    values.put(NoteColumns.TITLE, data.title)
                    values.put(NoteColumns.DESC, data.desc)
                    values.put(NoteColumns.GENRE, data.genre)
                    values.put(NoteColumns.POSTER, data.poster)
                    values.put(NoteColumns.TRAILER, data.trailer)
                    values.put(NoteColumns.RATING, data.rating)

                    movieHelper.insert(values)
                    iconFav(true)
                }
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                startActivityForResult(intent, LOGIN_CODE)
            }
        }
    }

    private fun iconFav(status: Boolean) {
        if (status) {
            statusFav = status
            iv_fav.setImageResource(R.drawable.ic_baseline_favorite)
        } else {
            statusFav = status
            iv_fav.setImageResource(R.drawable.ic_baseline_favorite_border)
        }
    }

    private fun checkStatusFav() {
        val cursor = movieHelper.queryById(data.id.toString())

        if (cursor.moveToNext()) {
            iconFav(true)
        } else {
            iconFav(false)
        }
    }
}