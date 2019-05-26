package com.shenawynkov.productsdemo.view.product

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shenawynkov.productsdemo.model.product.Products
import kotlinx.android.synthetic.main.product_item.view.*


class ProductsAdapter(var list: List<Products>, var context: Context, var productListener: ProductListener) :
    RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {


    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


        var tvTitle = view.tv_title

        var tvPrice = view.tv_price

        var tvImage = view.imageView
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(com.shenawynkov.productsdemo.R.layout.product_item, parent, false)

        return MyViewHolder(view)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvTitle.text = list.get(position).name
        holder.tvPrice.text = list.get(position).price.toString() + " $"


        //image
        holder.tvImage.layout(0, 0, 0, 0)
        Glide.with(context).load(list.get(position).image.link).centerCrop().into(holder.tvImage)
                holder.tvImage.getLayoutParams().height = list.get(position).image.height
        holder.tvImage.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT
        holder.tvImage.requestLayout()
        //moveToDetails
        holder.view.setOnClickListener {
            productListener.itemSelected(list.get(holder.adapterPosition))
        }


    }


    override fun getItemCount() = list.size
}