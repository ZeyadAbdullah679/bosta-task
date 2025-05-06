package com.bosta.task.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bosta.task.databinding.ItemCityBinding
import com.bosta.task.databinding.ItemDistrictBinding

class CitiesAdapter : ListAdapter<CityItem, CitiesAdapter.CityViewHolder>(CityDiffCallback()) {

    inner class CityViewHolder(val binding: ItemCityBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = getItem(position)
        val b = holder.binding

        b.cityName.text = city.name
        b.arrowIcon.rotation = if (city.isExpanded) 180f else 0f
        b.districtsContainer.removeAllViews()

        if (city.isExpanded) {
            b.districtsContainer.visibility = View.VISIBLE
            city.districts.forEach { district ->
                val districtBinding = ItemDistrictBinding.inflate(
                    LayoutInflater.from(b.root.context),
                    b.districtsContainer,
                    false
                )
                districtBinding.districtName.text = district.name
                districtBinding.statusLabel.visibility = if (!district.isCovered) View.VISIBLE else View.GONE
                b.districtsContainer.addView(districtBinding.root)
            }
        } else {
            b.districtsContainer.visibility = View.GONE
        }

        b.cityHeader.setOnClickListener {
            val updatedList = currentList.toMutableList()
            updatedList[position] = city.copy(isExpanded = !city.isExpanded)
            submitList(updatedList)
        }
    }

    class CityDiffCallback : DiffUtil.ItemCallback<CityItem>() {
        override fun areItemsTheSame(oldItem: CityItem, newItem: CityItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CityItem, newItem: CityItem): Boolean {
            return oldItem == newItem
        }
    }
}
