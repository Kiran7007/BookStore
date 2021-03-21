package com.sample.gutenberg.ui.activity

import android.os.Bundle
import com.sample.gutenberg.R
import com.sample.gutenberg.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}