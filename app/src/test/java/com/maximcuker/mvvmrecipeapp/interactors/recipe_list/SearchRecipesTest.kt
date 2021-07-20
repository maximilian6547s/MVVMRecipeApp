package com.maximcuker.mvvmrecipeapp.interactors.recipe_list

import com.google.gson.GsonBuilder
import com.maximcuker.mvvmrecipeapp.cache.AppDatabaseFake
import com.maximcuker.mvvmrecipeapp.cache.RecipeDaoFake
import com.maximcuker.mvvmrecipeapp.cashe.model.RecipeEntityMapper
import com.maximcuker.mvvmrecipeapp.network.RecipeService
import com.maximcuker.mvvmrecipeapp.network.data.MockWebServerResponses.recipeListResponse
import com.maximcuker.mvvmrecipeapp.network.model.RecipeDtoMapper
import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.HttpURLConnection

class SearchRecipesTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var baseUrl: HttpUrl
    private val appDataBase = AppDatabaseFake()

    // system in test
    private lateinit var searchRecipes:SearchRecipes

    // dependencies
    private lateinit var recipeService: RecipeService
    private lateinit var recipeDao: RecipeDaoFake
    private val dtoMapper = RecipeDtoMapper()
    private val entityMapper = RecipeEntityMapper()


    @BeforeEach
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        baseUrl = mockWebServer.url("/api/recipe/")
        recipeService = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeService::class.java)

        recipeDao = RecipeDaoFake(appDataBase)

        // instantiate the system in test
        searchRecipes = SearchRecipes(
            recipeDao = recipeDao,
            recipeService = recipeService,
            entityMapper = entityMapper,
            dtoMapper = dtoMapper
        )
    }

    @Test
    fun mockWebServerSetup() {
        // condition the response
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(recipeListResponse)
        )
    }

    @AfterEach
    fun tearDown() {
        mockWebServer.shutdown()
    }

}