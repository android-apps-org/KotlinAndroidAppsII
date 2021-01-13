package com.jdemaagd.devbyteviewer.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jdemaagd.devbyteviewer.R

/**
 * This is a single activity application that uses the Navigation library
 * Content is displayed by Fragments
 */
class DevByteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev_byte_viewer)
    }
}
