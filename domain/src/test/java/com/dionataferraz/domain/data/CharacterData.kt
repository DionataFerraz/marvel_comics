package com.dionataferraz.domain.data

import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.Thumbnail
import com.dionataferraz.domain.model.mapper.toCharacterDetail

object CharacterData {

    val THOR = Character(
        1009664,
        "Thor",
        "As the Norse God of thunder and lightning, Thor wields one of the greatest weapons ever made, the enchanted hammer Mjolnir. While others have described Thor as an over-muscled, oafish imbecile, he's quite smart and compassionate.  He's self-assured, and he would never, ever stop fighting for a worthwhile cause.",
        Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350", "jpg")
    )

    val CHARACTER_DETAIL_THOR = THOR.toCharacterDetail()

}