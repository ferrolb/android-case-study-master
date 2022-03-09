package com.target.targetcasestudy.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.ProductsViewModel

private const val DEAL_ID = "deal_id"
private const val TAG = "DealItemDetailFragment"
class DealItemDetailFragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_deal_item, container, false)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val dealId : Int = arguments?.getSerializable(DEAL_ID) as Int
    Log.d(TAG, "Received dealId:$dealId")
    val productsViewModel = ViewModelProviders.of(this).get(ProductsViewModel::class.java)
    val products = productsViewModel.productsLiveData.value
    if (dealId>=0) {
      val dealItem = products?.products?.get(dealId) ?: DealItem(id = 0, title="no found", aisle = "", description = "", imageUrl = "", regularPrice = null, salePrice = null)
      Log.d(TAG, "dealItem:$dealItem")
    }
  }

  companion object {
    fun newInstance(dealId: Int?): DealItemDetailFragment {
      val args = Bundle().apply{
        putSerializable(DEAL_ID, dealId ?: -9)
      }
      return DealItemDetailFragment().apply {
        arguments = args
      }
    }
  }
}
