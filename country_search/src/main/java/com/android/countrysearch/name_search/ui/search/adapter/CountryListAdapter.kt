package com.android.countrysearch.name_search.ui.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.decode.SvgDecoder
import coil.load
import com.android.countrysearch.core.ext.inflate
import com.android.countrysearch.name_search.R
import com.android.countrysearch.name_search.databinding.CountryItemBinding

import com.android.countrysearch.name_search.model.CountryModel

typealias RecentSearchClickListener = (CountryModel) -> Unit

class CountryListAdapter(private val onClick: RecentSearchClickListener) :
    ListAdapter<CountryModel, CountryListAdapter.CountryItemViewHolder>(diffUtilCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        return CountryItemViewHolder(CountryItemBinding.bind(parent.inflate(R.layout.country_item)))
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class CountryItemViewHolder(private val binding: CountryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageLoader = coil.ImageLoader.Builder(binding.root.context)
            .componentRegistry {
                add(SvgDecoder(binding.root.context))
            }.build()

        fun bind(country: CountryModel, clickListener: RecentSearchClickListener) {
            binding.name.text = country.name
            binding.name.setOnClickListener {
                clickListener(country)
            }
            binding.flag.load(country.flag, imageLoader){
                crossfade(true)
                error(R.drawable.ic_error_page_2)
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
