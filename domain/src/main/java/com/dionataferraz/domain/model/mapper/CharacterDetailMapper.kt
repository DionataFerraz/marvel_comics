package com.dionataferraz.domain.model.mapper

import com.dionataferraz.data.model.Character
import com.dionataferraz.domain.model.CharacterDetail

fun Character.toCharacterDetail() = CharacterDetail(
    id = id,
    name = name,
    description = description,
    image = thumbnail.run { "$path.$extension" }
)