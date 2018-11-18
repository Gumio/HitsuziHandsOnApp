package com.gumioji.hitsuziqiitaviewerapp.data

data class Item(
    val id: String? = null,
    val title: String? = null,
    val body: String? = null,
    val url: String? = null,
    val comments_count: Int? = null,
    val likes_count: Int? = null,
    val created_at: String? = null,
    val user: User? = null
) {
    data class User(
        val description: String? = null,
        val facebook_id: String? = null,
        val followees_count: Int = 0,
        val followers_count: Int = 0,
        val github_login_name: String? = null,
        val id: String,
        val items_count: Int  = 0,
        val linkedin_id: String? = null,
        val location: String? = null,
        val name: String? = null,
        val organization: String? = null,
        val permanent_id: Int? = null,
        val profile_image_url: String? = null,
        val twitter_screen_name: String? = null,
        val website_url: String? = null
    )
}