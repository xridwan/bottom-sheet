package com.xridwan.submission.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sheet(
    val icon: Int? = null,
    val feature: String? = null,
) : Parcelable