package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Github (
    var id: Int,

    var reposUrl: String,

    var followingUrl: String,

    var login: String,

    var followersUrl: String,

    var avatarUrl: String,

): Parcelable