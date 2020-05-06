package com.dionataferraz.presentation.model

import java.io.Serializable

data class CharacterPresentation(
    val id: Int,
    val name: String,
    val description: String,
    val image: String
) : Serializable