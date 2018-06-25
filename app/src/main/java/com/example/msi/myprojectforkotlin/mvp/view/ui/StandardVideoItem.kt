package com.example.msi.myprojectforkotlin.mvp.view.ui

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.example.msi.myprojectforkotlin.R
import com.example.msi.myprojectforkotlin.bean.Item
import com.example.msi.myprojectforkotlin.utils.durationFormat
import com.example.msi.myprojectforkotlin.utils.timePreFormat
import kotlinx.android.synthetic.main.item_home_standard.view.*

/**
 * Created by MSI on 2018/5/23.
 */
class StandardVideoItem : FrameLayout {


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        View.inflate(context, R.layout.item_home_standard, this)
    }

    /**
     * type表示该item用于哪里，稍有不同
     */
    fun setData(item: Item, type: String) {
        val data = item.data

        if (data?.cover?.homepage == null) {

        }
        val cover = data?.cover?.feed
        var avatar = data?.author?.icon
        var avatarRes = R.mipmap.pgc_default_avatar

        if (avatar == null || "" == avatar) {
            avatar = data?.provider?.icon
        }

        Glide.with(context).load(cover).centerCrop().into(iv_cover)

        val ivAvatarCircle = object : BitmapImageViewTarget(iv_avatar) {
            override fun setResource(resource: Bitmap?) {
                super.setResource(resource)
                val circularBitmapDrawable = RoundedBitmapDrawableFactory.create(resources, resource)
                circularBitmapDrawable.setCircular(true);
                iv_avatar.setImageDrawable(circularBitmapDrawable);
            }
        }
        if (avatar == null || "" == avatar) {
            Glide.with(context).load(avatarRes).asBitmap().centerCrop().into(ivAvatarCircle)
        } else {
            Glide.with(context).load(avatar).asBitmap().centerCrop().into(ivAvatarCircle)
        }
        tv_title.text = item.data?.title
        var tagText = ""
        if (type.equals("feed")) {
            tagText = ""
        } else if (type.equals("categorydetail")) {
            if(data?.author!=null){
                tagText = data?.author?.name + " / "
            }else if(data?.provider!=null){
                tagText = data?.provider?.name + " / "
            }else{
                tagText = ""
            }
        }
        data?.tags?.take(4)?.forEach { tagText += (it.name + " / ") }
        val timeFormat = durationFormat(data?.duration)
        tagText += timeFormat
        tv_tag.text = tagText

        if (type.equals("feed")) {
            tv_tag2.text = data?.category
        } else if (type == "categorydetail") {
            data?.date?.let {
                tv_tag2.text = timePreFormat(it)
            }
        }

    }
}