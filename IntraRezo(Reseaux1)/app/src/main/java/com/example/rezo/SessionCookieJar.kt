package com.example.rezo

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

object SessionCookieJar : CookieJar {
    private var cookies: MutableList<Cookie> = mutableListOf()

    /**
     * Sauvegarde les cookies reçus dans la réponse HTTP
     */
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        this.cookies = cookies.toMutableList()
    }

    /**
     * Charge les cookies à envoyer dans la requête HTTP
     */
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookies
    }
}