package com.target.targetcasestudy.ui

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.ProductsViewModel
import java.util.*

private const val TAG = "DealListFragment"

class DealListFragment : Fragment() {

    private lateinit var productsViewModel: ProductsViewModel
    private lateinit var dealsRecyclerView: RecyclerView

    interface Callbacks {
        fun onDealSelected(dealId: Int?)
    }

    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productsViewModel.productsLiveData.observe(viewLifecycleOwner, Observer { products ->
            Log.d(TAG, "Products retrieved:$products")
            dealsRecyclerView.adapter = DealItemAdapter(products.products ?: emptyList())
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deal_list, container, false)
        dealsRecyclerView = view.findViewById(R.id.recycler_view)
        dealsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

    private inner class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        lateinit var deal: DealItem
        var imageView: ImageView
        var priceView: TextView
        var titleView: TextView
        var locationTextView: TextView
        var shipOrView: TextView

        init {
            itemView.setOnClickListener(this)
            imageView = itemView.findViewById(R.id.deal_list_item_image_view)
            priceView = itemView.findViewById(R.id.deal_list_item_price)
            titleView = itemView.findViewById(R.id.deal_list_item_title)
            locationTextView = itemView.findViewById(R.id.location_text)
            shipOrView = itemView.findViewById(R.id.ship_or)
        }

        override fun onClick(v: View?) {
            callbacks?.onDealSelected(deal.id)
        }
    }

    private inner class DealItemAdapter(private val dealItems: List<DealItem>) :
        RecyclerView.Adapter<DealItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.deal_list_item, parent, false)
            return DealItemViewHolder(view)
        }

        override fun getItemCount(): Int {
            return dealItems.size
        }

        // This is for the deprecation of the non-themed resources.getColor() call
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
            val item = dealItems[position]
            viewHolder.deal = item

            // load the image from image url
            val url =
                if (viewHolder.deal.imageUrl != null) "${viewHolder.deal.imageUrl}?w=360" else null
            Glide.with(viewHolder.itemView)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
                .fallback(R.drawable.flowers)
                .into(viewHolder.imageView)

            viewHolder.titleView.text = item.title
            viewHolder.priceView.text = item.regularPrice?.displayString
            viewHolder.locationTextView.text = item.aisle?.toUpperCase(Locale.getDefault()) ?: ""

            // TODO: LOCALIZATION - once the app is localized this would have to be addressed
            val spannable = SpannableString(SHIP_OR_TEXT)
            spannable.setSpan(
                ForegroundColorSpan(resources.getColor(R.color.or_text_color, null)),
                5, 7,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            viewHolder.shipOrView.text = spannable
        }
    }

    companion object {
        // In real life this would be in the string table for localization
        const val SHIP_OR_TEXT = "ship or"
    }
}
