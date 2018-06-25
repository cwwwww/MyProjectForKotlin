package com.example.msi.myprojectforkotlin.mvp.view.ui

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import com.bumptech.glide.Glide
import com.example.msi.myprojectforkotlin.R
import com.example.msi.myprojectforkotlin.bean.Item
import com.example.msi.myprojectforkotlin.utils.EmptyControlVideo
import com.example.msi.myprojectforkotlin.utils.TAG
import com.shuyu.gsyvideoplayer.utils.GSYVideoType
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer
import kotlinx.android.synthetic.main.item_home_banner.view.*

/**
 * Created by MSI on 2018/5/23.
 */
class HomeBannerItem : FrameLayout {
    lateinit var data: Item

    constructor(context: Context?, data: Item) : super(context) {
        this.data = data
        initView()
        setUpView()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {

    }


    var isVideo = false

    fun setUpView() {

        val thumbPlayUrl = data.data?.thumbPlayUrl

        imageView.visibility = View.VISIBLE
        val feedImgUrl = data.data?.cover?.feed
        Glide.with(context).load(feedImgUrl).centerCrop().into(imageView)

        if (thumbPlayUrl == null || "" == thumbPlayUrl) {
            isVideo = false
            videoView.visibility = View.GONE
        } else {
            isVideo = true
            videoView.visibility = View.VISIBLE
            videoView.setUp(thumbPlayUrl, false, "")
            GSYVideoType.setShowType(GSYVideoType.SCREEN_MATCH_FULL)
        }
    }

    var isInitVideoView = false

    private fun initVideoView() {
        isInitVideoView = true
        videoView.setVideoAllCallBack(object : EmptyControlVideo.EmptyControlVideoCallBack() {
            override fun onPrepared(url: String?, vararg objects: Any?) {
                Log.d(TAG, "onPrepared");//加载成功
                imageView.visibility = View.INVISIBLE
            }

            override fun onAutoComplete(url: String?, vararg objects: Any?) {
                Log.d(TAG, "onAutoComplete");//播放完成
                imageView.visibility = View.VISIBLE
                videoView.startPlayLogic()

            }

            override fun onPlayError(url: String?, vararg objects: Any?) {
                Log.d(TAG, "onPlayError")
                imageView.visibility = View.VISIBLE
                videoView.startPlayLogic()
            }
        })
    }

    /**
     * 开始播放
     */
    fun play() {
        if (!isInitVideoView && videoView.visibility == View.VISIBLE) {
            videoView.startPlayLogic()
            initVideoView()
        }
    }

    /**
     * 释放播放器
     */
    fun releasePlayer() {
        isInitVideoView = false
        if (videoView.visibility == View.VISIBLE) {
            videoView.setStandardVideoAllCallBack(null);
            GSYVideoPlayer.releaseAllVideos();
        }
    }


    private fun initView() {
        val view = View.inflate(context, R.layout.item_home_banner, null)
        addView(view)
    }


}