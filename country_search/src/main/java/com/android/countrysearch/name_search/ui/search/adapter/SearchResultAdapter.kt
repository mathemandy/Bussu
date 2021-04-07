package com.android.countrysearch.name_search.ui.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.countrysearch.core.ext.inflate
import com.android.countrysearch.name_search.R
import com.android.countrysearch.name_search.databinding.SearchResultBinding
import com.android.countrysearch.name_search.model.CountryModel
import com.android.countrysearch.name_search.ui.search.adapter.SearchResultAdapter.SearchResultViewHolder

typealias SearchResultClickListener = (CountryModel) -> Unit

class SearchResultAdapter(private val onClick: SearchResultClickListener) :
    ListAdapter<CountryModel, SearchResultViewHolder>(diffUtilCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchResultViewHolder {
        return SearchResultViewHolder(SearchResultBinding.bind(parent.inflate(R.layout.search_result)))
    }

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class SearchResultViewHolder(private val binding: SearchResultBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: CountryModel, onClick: SearchResultClickListener) {
            binding.country.text = country.name
            binding.country.setOnClickListener {
                onClick(country)
            }
        }
    }

    companion object {
        val diffUtilCallback: DiffUtil.ItemCallback<CountryModel>
            get() = object : DiffUtil.ItemCallback<CountryModel>() {
                override fun areItemsTheSame(
                    oldItem: CountryModel,
                    newItem: CountryModel
                ): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(
                    oldItem: CountryModel,
                    newItem: CountryModel
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
