package com.example.funfacts.data.remote

import kotlinx.serialization.Serializable

// Example fields from https://uselessfacts.jsph.pl/random.json?language=en
@Serializable
data class FunFactDto(
    val id: String? = null,
    val text: String,          // API returns "text"
    val source: String? = null,
    val source_url: String? = null,
    val language: String? = null,
    val permalink: String? = null
)