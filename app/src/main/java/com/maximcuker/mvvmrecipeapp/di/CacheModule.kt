package com.maximcuker.mvvmrecipeapp.di

import androidx.room.Room
import com.maximcuker.mvvmrecipeapp.cashe.RecipeDao
import com.maximcuker.mvvmrecipeapp.cashe.database.AppDataBase
import com.maximcuker.mvvmrecipeapp.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: BaseApplication): AppDataBase {
        return Room
            .databaseBuilder(app, AppDataBase::class.java, AppDataBase.DATABASE_NAME)
            .fallbackToDestructiveMigration()  // do not use this in production
            .build()
    }

    @Singleton
    @Provides
    fun provideRecipeDao(app: AppDataBase): RecipeDao {
        return app.recipeDao()
    }
}