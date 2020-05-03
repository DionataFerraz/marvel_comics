package com.dionataferraz.presentation.model.mapper

import com.dionataferraz.domain.model.CharacterDetail
import com.dionataferraz.presentation.model.CharacterPresentation

fun CharacterDetail.toCharacterPresentation() = CharacterPresentation(
    id = id,
    name = name,
    description = description,
    image = image
)