package com.example.media

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

object SessionCookieJar : CookieJar {

    private var cookies: MutableList<Cookie> = mutableListOf()


    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies = cookies.toMutableList()
    }


    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies
    }
}