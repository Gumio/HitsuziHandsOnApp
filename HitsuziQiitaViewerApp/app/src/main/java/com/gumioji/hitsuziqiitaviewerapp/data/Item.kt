package com.gumioji.hitsuziqiitaviewerapp.data

data class Item(
    val id: String?,
    val title: String?,
    val body: String?,
    val url: String?,
    val comments_count: Int?,
    val likes_count: Int?,
    val created_at: String?,
    val user: User?
) {
    data class User(
        val description: String?,
        val facebook_id: String?,
        val followees_count: Int,
        val followers_count: Int,
        val github_login_name: String?,
        val id: String,
        val items_count: Int,
        val linkedin_id: String?,
        val location: String?,
        val name: String?,
        val organization: String?,
        val permanent_id: Int?,
        val profile_image_url: String?,
        val twitter_screen_name: String?,
        val website_url: String?
    )
}