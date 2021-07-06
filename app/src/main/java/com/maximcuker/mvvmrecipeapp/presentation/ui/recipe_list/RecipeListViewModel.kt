package com.maximcuker.mvvmrecipeapp.presentation.ui.recipe_list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.maximcuker.mvvmrecipeapp.network.model.RecipeDtoMapper
import com.maximcuker.mvvmrecipeapp.repository.RecipeRepository
import javax.inject.Named

class RecipeListViewModel
@ViewModelInject
constructor(
    private val randomString: String,
    private val repository: RecipeRepository,
    private @Named("auth_token") val token: String

) : ViewModel() {
    init {
        println("VIEWMODEL: ${randomString}")
        println("VIEWMODEL: ${repository}")
        println("VIEWMODEL: ${token}")
    }

    fun getRepo() = repository

    fun getRandomString() = randomString

    fun getToken() = token

}