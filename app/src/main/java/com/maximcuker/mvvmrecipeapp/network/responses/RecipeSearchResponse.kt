package com.maximcuker.mvvmrecipeapp.network.responses

import com.google.gson.annotations.SerializedName
import com.maximcuker.mvvmrecipeapp.network.model.RecipeDto

data class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)