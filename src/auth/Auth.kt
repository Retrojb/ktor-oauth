package com.example.ktorOauthPractice.auth

import io.ktor.auth.OAuthServerSettings
import io.ktor.http.HttpMethod

val clientCredentials = OAuthServerSettings.OAuth2ServerSettings (
        name = "IdentityServer4",
        authorizeUrl = " ",
        accessTokenUrl = "",
        clientId = "lms_central",
        clientSecret = "my_stupid_token",
        accessTokenRequiresBasicAuth = false,
        requestMethod = HttpMethod.Post,
        defaultScopes = listOf()
)