package com.example.msi.myprojectforkotlin.mvp.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.msi.myprojectforkotlin.bean.Item
import com.example.msi.myprojectforkotlin.mvp.view.activity.DetailActivity
import com.example.msi.myprojectforkotlin.mvp.view.ui.HomeBanner
import com.example.msi.myprojectforkotlin.mvp.view.ui.HomeTextHeaderItem
import com.example.msi.myprojectforkotlin.mvp.view.ui.StandardVideoItem
import com.example.msi.myprojectforkotlin.utils.DisplayManager
import com.example.msi.myprojectforkotlin.utils.toActivityWithSerializable

/**
 * Created by MSI on 2018/5/23.
 */
class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var isNewBanner = false
    var itemList: ArrayList<Item> = ArrayList()

//    //field只有在访问属性的时候才会产生，其他时候是不会产生
        set(value) {
            field = value
            isNewBanner = true
            notifyDataSetChanged()
        }


    fun addData(itemList: ArrayList<Item>) {
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    //banner用了的Item数量（包括type为banner2的）
    var bannerItemListCount = 0

    private val TYPE_BANNER = 1
    private val TYPE_STANDARD = 2
    private val TYPE_HEADER_TEXT = 3
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_BANNER -> ViewHolder(HomeBanner(parent!!.context))

            TYPE_STANDARD -> {
                val textView = StandardVideoItem(parent!!.context)
                ViewHolder(textView)
            }
            TYPE_HEADER_TEXT -> {
                val headerText = HomeTextHeaderItem(parent!!.context)
                headerText.layoutParams = RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                        DisplayManager.dip2px(56f)!!)
                ViewHolder(headerText)


            }
            else -> return ViewHolder(null)
        }

    }

    override fun getItemCount(): Int {
        if (itemList.size > bannerItemListCount) {
            return itemList.size - bannerItemListCount + 1
        } else if (itemList.size == 0) {
            return 0
        } else {
            return 1
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val itemType = getItemViewType(position)
        when (itemType) {
            TYPE_BANNER -> {
                if (isNewBanner) {
                    isNewBanner = false
                    (holder?.itemView as HomeBanner).setData(itemList.take(bannerItemListCount).toCollection(ArrayList()))
                }
            }
            TYPE_STANDARD -> (holder?.itemView as StandardVideoItem).let {
                it.setOnClickListener { v -> v.context.toActivityWithSerializable<DetailActivity>(itemList[position + bannerItemListCount - 1]) }
                it.setData(itemList[position + bannerItemListCount - 1], "feed")
            }

            TYPE_HEADER_TEXT -> (holder?.itemView as HomeTextHeaderItem).setHeaderText(itemList[position + bannerItemListCount - 1].data?.text)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_BANNER
        }
        if (itemList[position + bannerItemListCount - 1].type == "textHeader") {
            return TYPE_HEADER_TEXT
        } else {
            return TYPE_STANDARD
        }
    }

    fun setBannerSize(size: Int) {
        bannerItemListCount = size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView)
}