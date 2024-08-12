package com.example.capstone.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.capstone.databinding.ActivityDetailBinding
import com.example.core.data.Resource
import com.example.core.domain.model.Github
import com.example.core.domain.model.GithubDetail
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("EXTRA_USERNAME")
        if (username != null) {
            detailViewModel.setUsername(username)
        }

        lifecycleScope.launch {
            detailViewModel.github.collect{github ->
                if (github != null) {
                    when (github) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            github.data?.let { showDetailTourism(it) }
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
//                        binding.viewError.root.visibility = View.VISIBLE
//                        binding.viewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            }
        }


    }


    private fun showDetailTourism(detailGithub: List<GithubDetail>) {

        detailGithub.map {
            binding.name.text = it.name
            binding.username.text = it.login
            binding.followers.text = "Followers ${it.followers.toString()}"
            binding.following.text = "Following ${it.following.toString()}"

            Glide.with(this)
                .load(it.avatarUrl)
                .transform(CircleCrop())
                .into(binding.imgDetailProfile)
        }

    }
}