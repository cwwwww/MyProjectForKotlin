package com.example.msi.myprojectforkotlin.bean

/**
 * Created by MSI on 2018/5/22.
 */
data class Issue(val releaseTime:Long,val type:String,val date:Long,val total:Int,val publishTime:Long,val itemList:ArrayList<Item>,var count:Int,val nextPageUrl:String)
