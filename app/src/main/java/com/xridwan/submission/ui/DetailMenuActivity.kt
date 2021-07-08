package com.xridwan.submission.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xridwan.submission.databinding.ActivityDetailMenuBinding
import com.xridwan.submission.model.Sheet

class DetailMenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMenuBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Sheet>(EXTRA_DATA) as Sheet

        supportActionBar?.apply {
            title = data.feature
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}