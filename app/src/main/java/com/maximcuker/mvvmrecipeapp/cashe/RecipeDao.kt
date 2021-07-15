package com.maximcuker.mvvmrecipeapp.cashe

import androidx.room.Dao
import androidx.room.Insert
import com.maximcuker.mvvmrecipeapp.cashe.model.RecipeEntity

@Dao
interface RecipeDao {

    @Insert
    suspend fun insertRecipe(recipe:RecipeEntity): Long

}