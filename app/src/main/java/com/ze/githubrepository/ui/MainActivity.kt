package com.ze.githubrepository.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.ze.githubrepository.R
import com.ze.githubrepository.cache.CacheDisk

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CacheDisk.initDatabase(this)
    }
}