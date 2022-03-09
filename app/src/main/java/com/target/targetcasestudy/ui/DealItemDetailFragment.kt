package com.target.targetcasestudy.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem

private const val DEAL_ITEM = "deal_item"
private const val TAG = "DealItemDetailFragment"

class DealItemDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_deal_item, container, false)

        val dealItem: DealItem = arguments?.getSerializable(DEAL_ITEM) as DealItem

        val imageView = view.findViewById<ImageView>(R.id.deal_list_item_image_view)
        val itemSalePriceView = view.findViewById<TextView>(R.id.item_sale_price)
        val itemRegularPriceView = view.findViewById<TextView>(R.id.item_reg_price)
        val itemTitleView = view.findViewById<TextView>(R.id.deal_list_item_title)
        val itemDescriptionView = view.findViewById<TextView>(R.id.item_description)

        // load the image from image url
        val url =
            if (dealItem.imageUrl != null) "${dealItem.imageUrl}?w=360" else null
        Glide.with(view)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .fallback(R.drawable.flowers)
            .into(imageView)

        itemSalePriceView.text = dealItem.salePrice?.displayString ?: ""
        itemRegularPriceView.text = dealItem.regularPrice?.displayString ?: ""
        itemTitleView.text = dealItem.title
        itemDescriptionView.movementMethod = ScrollingMovementMethod()
        itemDescriptionView.text = dealItem.description

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dealItem: DealItem = arguments?.getSerializable(DEAL_ITEM) as DealItem
        Log.d(TAG, "Received dealId:$dealItem")
    }

    companion object {
        fun newInstance(dealItem: DealItem): DealItemDetailFragment {
            val args = Bundle().apply {
                putSerializable(DEAL_ITEM, dealItem)
            }
            return DealItemDetailFragment().apply {
                arguments = args
            }
        }
    }
}
