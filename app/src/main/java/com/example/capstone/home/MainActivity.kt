package com.example.capstone.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone.databinding.ActivityMainBinding
import com.example.capstone.detail.DetailActivity
import com.example.core.data.Resource
import com.example.core.data.source.remote.response.DetailUserGithubResponse
import com.example.core.ui.GithubAdapter
import kotlinx.coroutines.launch
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
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("EXTRA_USERNAME", selectedData.login)
            startActivity(intent)
        }

        binding.searchBar.setOnClickListener  {
            binding.searchView.setupWithSearchBar(binding.searchBar)
            binding.searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event->
                    val query = textView.text.toString()
                    binding.searchBar.setText(query)
                    binding.searchView.hide()
                    mainViewModel.setQuery(query)
                    false
                }
        }

        lifecycleScope.launch {
            mainViewModel.github.collect{github ->
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
        }





        with(binding.rvUsers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = githubAdapter
        }
    }
}