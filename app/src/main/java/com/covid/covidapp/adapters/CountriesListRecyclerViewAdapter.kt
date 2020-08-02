package com.covid.covidapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView

import com.covid.covidapp.R
import com.covid.covidapp.base.BaseViewHolder
import com.covid.covidapp.data.CountriesListModelItem
import kotlinx.android.synthetic.main.countries_list_item.view.*


class CountriesListRecyclerViewAdapter(val adapterInteractionListener: AdapterInteractionListener) :
    RecyclerView.Adapter<BaseViewHolder<Int>>() {

    val countriesList: ArrayList<CountriesListModelItem> by lazy {
        ArrayList<CountriesListModelItem>()
    }


    inner class ListItemViewHolder(view: View) : BaseViewHolder<Int>(view) {
        val countryNameTextView = itemView.countryNameTextView

        override fun bind(position: Int) {
            val countriesListModelItem = countriesList.get(position)
            countryNameTextView.text = countriesListModelItem?.name
            setAnimation(itemView)
            itemView.setOnClickListener(View.OnClickListener {
                adapterInteractionListener.onItemClick(countriesListModelItem)
            })
        }

    }

    fun updateList(list: List<CountriesListModelItem>) {
        countriesList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Int> {
        return ListItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.countries_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }


    private fun setAnimation(viewToAnimate: View) {
        // If the bound view wasn't previously displayed on screen, it's animated
        val animation =
            AnimationUtils.loadAnimation(viewToAnimate.context, R.anim.slide_right_in)
        viewToAnimate.startAnimation(animation)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Int>, position: Int) {
        holder.bind(position)
    }

    interface AdapterInteractionListener {

        fun onItemClick(countriesListModelItem: CountriesListModelItem)
    }
}