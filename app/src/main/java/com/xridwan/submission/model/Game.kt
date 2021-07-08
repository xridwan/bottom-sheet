package com.xridwan.submission.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val poster: Int? = null,
    val games: String? = null,
    val release: String? = null,
    val description: String? = null
) : Parcelable