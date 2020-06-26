package com.nawaf.example.randomquote.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
class Quote (
    @field:Json(name = "id") @PrimaryKey val id: String,
    @field:Json(name = "en") val content: String,
    @field:Json(name = "author") val author: String
) {


}