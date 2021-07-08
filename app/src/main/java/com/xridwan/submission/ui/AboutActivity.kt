package com.xridwan.submission.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xridwan.submission.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "About"
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}