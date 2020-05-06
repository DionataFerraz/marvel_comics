package com.dionataferraz.domain.model.mapper

import com.dionataferraz.data.model.Character
import com.dionataferraz.domain.model.CharacterDetail

fun List<Character>.toCharacterDetail() = map { character ->
    CharacterDetail(
        id = character.id,
        name = character.name,
        description = character.description,
        image = character.thumbnail.run { "$path.$extension" }
    )
}