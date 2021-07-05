package com.maximcuker.mvvmrecipeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "AppDebug"

    @Inject
    lateinit var app: BaseApplication

    @Inject
    lateinit var someRandomString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        Log.d(TAG, "OnCrate: ${someRandomString}")
        Log.d(TAG, "OnCreate: ${app}")
    }
}