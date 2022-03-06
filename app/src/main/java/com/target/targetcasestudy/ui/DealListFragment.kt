package com.target.targetcasestudy.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem
import com.target.targetcasestudy.data.StaticData


class DealListFragment : Fragment() {

  interface Callbacks {
    fun onDealSelected(dealId: Int)
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

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val view =  inflater.inflate(R.layout.fragment_deal_list, container, false)

    view.findViewById<RecyclerView>(R.id.recycler_view).layoutManager = LinearLayoutManager(requireContext())
    view.findViewById<RecyclerView>(R.id.recycler_view).adapter = DealItemAdapter()

    return view
  }

  private inner class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    lateinit var deal: DealItem
    init {
      itemView.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
      callbacks?.onDealSelected(deal.id)
    }
  }

  private inner class DealItemAdapter : RecyclerView.Adapter<DealItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
      val inflater = LayoutInflater.from(parent.context)
//    val view = inflater.inflate(R.layout.deal_list_item, parent, false)
      val view = inflater.inflate(R.layout.new_deal_list_item, parent, false)
      return DealItemViewHolder(view)
    }

    override fun getItemCount(): Int {
      return StaticData.deals.size
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
      val item = StaticData.deals[position]
      viewHolder.deal = item
      viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_title).text = item.title
      viewHolder.itemView.findViewById<TextView>(R.id.deal_list_item_price).text = item.price
    }
  }
}
