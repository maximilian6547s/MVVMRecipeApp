package com.maximcuker.mvvmrecipeapp.cashe.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maximcuker.mvvmrecipeapp.cashe.RecipeDao
import com.maximcuker.mvvmrecipeapp.cashe.model.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1)
abstract class AppDataBase:RoomDatabase() {

    abstract fun recipeDao():RecipeDao

    companion object{

        val DATABASE_NAME = "recipe_db"
    }

}