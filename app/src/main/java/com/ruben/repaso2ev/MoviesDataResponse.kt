package com.ruben.repaso2ev

import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class MoviesDataResponse (
    @SerializedName("id") val movieId: String,
    @SerializedName("name") val name: String,
    @SerializedName("title") val title: String,
    @SerializedName("releaseDate") val releaseDate: String,
    @SerializedName("duration") val duration: String,
    @SerializedName("image") val image: String,
    @SerializedName("synopsis") val synopsis: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("director") val director: String,
    @SerializedName("leadActor") val leadActor: String,
    @SerializedName("writer1") val writer1: String,
    @SerializedName("writer2") val writer2: String,
    @SerializedName("writer3") val writer3: String,
    @SerializedName("writer4") val writer4: String
)