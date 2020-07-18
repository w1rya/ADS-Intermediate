package com.wiryatech.adsintermediate.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieModel(

    var id: Int? = 0,
    var title: String? = "",
    var desc: String? = "",
    var genre: String? = "",
    var poster: Int? = 0,
    var trailer: Int? = 0,
    var rating: Float

) : Parcelable