package com.example.msi.myprojectforkotlin.bean

import java.io.Serializable

/**
 * Created by MSI on 2018/5/22.
 */
data class Category(val id: Long, val name: String, val description: String, val bgPicture: String, val bgColor: String, val headerImage: String) : Serializable