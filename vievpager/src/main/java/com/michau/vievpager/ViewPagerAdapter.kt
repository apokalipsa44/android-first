package com.michau.vievpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view_pager.view.*

class ViewPagerAdapter(val images:List<Int>):
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    inner class ViewPagerHolder(imageView: View):RecyclerView.ViewHolder(imageView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        var view=LayoutInflater.from(parent.context).inflate(R.layout.item_view_pager, parent, false)
        return ViewPagerHolder(view)
    }

    override fun getItemCount(): Int {
       return images.size
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        val currentImage=images[position]
        holder.itemView.iv_image.setImageResource(currentImage)
    }
}