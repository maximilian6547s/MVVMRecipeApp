package com.maximcuker.mvvmrecipeapp.di

import android.content.Context
import com.maximcuker.mvvmrecipeapp.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app:Context) : BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRandomString() :String {
        return "hey look a random string"
    }

}