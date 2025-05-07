package com.bosta.task.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bosta.task.R
import com.bosta.task.databinding.ActivityMainBinding
import com.bosta.task.domain.models.City
import com.bosta.task.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CitiesAdapter
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        adapter = CitiesAdapter()
        binding.citiesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.citiesRecyclerView.adapter = adapter

        // Search
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) =
                Unit

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchCities(s.toString())
            }
        })

        // Observe State
        lifecycleScope.launch {
            viewModel.cities.collectLatest { state ->
                when (state) {
                    is DataState.Success -> {
                        showData(state.data)
                    }

                    is DataState.Error -> {
                        showError(state.exception.message ?: getString(R.string.error_message))
                    }

                    is DataState.Loading -> {
                        showLoading()
                    }
                }
            }
        }

        viewModel.fetchCities()
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.errorTextView.visibility = View.GONE
        binding.citiesRecyclerView.visibility = View.GONE
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.errorTextView.visibility = View.VISIBLE
        binding.errorTextView.text = message
        binding.citiesRecyclerView.visibility = View.GONE
    }

    private fun showData(data: List<City>) {
        binding.progressBar.visibility = View.GONE
        binding.errorTextView.visibility = View.GONE
        binding.citiesRecyclerView.visibility = View.VISIBLE
        adapter.submitList(data.map { it.toCityItem() })
    }

    private fun City.toCityItem(): CityItem {
        return CityItem(
            this.name,
            this.districts.map {
                DistrictItem(
                    it.districtName,
                    it.pickupAvailability
                )
            }
        )
    }
}