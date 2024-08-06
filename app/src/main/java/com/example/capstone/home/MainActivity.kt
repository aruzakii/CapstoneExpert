package com.example.capstone.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.databinding.ActivityMainBinding
import com.example.core.data.Resource
import com.example.core.ui.GithubAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val githubAdapter = GithubAdapter()
        githubAdapter.onItemClick = { selectedData ->
//            val intent = Intent(activity, DetailTourismActivity::class.java)
//            intent.putExtra(DetailTourismActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        mainViewModel.github.observe(this){github ->
            if (github != null) {
                when (github) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        githubAdapter.setData(github.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
//                        binding.viewError.root.visibility = View.VISIBLE
//                        binding.viewError.tvError.text = tourism.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        }

        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = githubAdapter
        }
    }
}