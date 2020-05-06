package com.dionataferraz.presentation.model.mapper

import com.dionataferraz.domain.model.CharacterDetail
import com.dionataferraz.presentation.model.CharacterPresentation

fun List<CharacterDetail>.toCharacterPresentation() = map { characterDetail ->
    CharacterPresentation(
        id = characterDetail.id,
        name = characterDetail.name,
        description = characterDetail.description,
        image = characterDetail.image
    )
}