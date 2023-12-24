package org.example.entity

import kotlinx.serialization.Serializable

@Serializable
data class Film(var name: String, var description: String)
