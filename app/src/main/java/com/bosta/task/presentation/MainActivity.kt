package com.bosta.task.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bosta.task.R
import com.bosta.task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: CitiesAdapter

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

        adapter.submitList(getSampleData())
    }

    private fun getSampleData(): List<CityItem> {
        return listOf(
            CityItem(
                "Bani Suif", listOf(
                    DistrictItem("Bani Suif - Bani Suif", true),
                    DistrictItem("Bani Suif Center - Snor", false),
                    DistrictItem("Biba - Biba", true)
                )
            ),
            CityItem(
                "Cairo", listOf(
                    DistrictItem("Nasr City", true),
                    DistrictItem("Heliopolis", true),
                    DistrictItem("Maadi", false)
                )
            ),
            CityItem(
                "Aswan", listOf(
                    DistrictItem("Kom Ombo", true),
                    DistrictItem("Edfu", false)
                )
            )
        )
    }
}