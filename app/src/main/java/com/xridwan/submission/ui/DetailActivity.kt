package com.xridwan.submission.ui

import android.os.Bundle
import android.text.Html
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.xridwan.submission.R
import com.xridwan.submission.databinding.ActivityDetailBinding
import com.xridwan.submission.model.Game

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.getParcelableExtra<Game>(EXTRA_DATA) as Game

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            elevation = 0.0f
            title = data.games
        }

        val animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)

        binding.apply {
            data.poster?.let { Picasso.get().load(it).into(detailPoster) }
            data.poster?.let { Picasso.get().load(it).into(detailImg) }
            detailGame.text = data.games
            detailRelease.text = getString(R.string.label_release, data.release)
            detailDesc.text = Html.fromHtml(data.description)

            detailPoster.animation = animation
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}