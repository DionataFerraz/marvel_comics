package com.dionataferraz.presentation.data

import com.dionataferraz.domain.model.CharacterDetail
import com.dionataferraz.domain.model.CommonItemDetail
import com.dionataferraz.presentation.model.mapper.toCharacterPresentation
import com.dionataferraz.presentation.model.mapper.toCommonItemPresentation

object CharacterData {

    val THOR = CharacterDetail(
        1009664,
        "Thor",
        "As the Norse God of thunder and lightning, Thor wields one of the greatest weapons ever made, the enchanted hammer Mjolnir. While others have described Thor as an over-muscled, oafish imbecile, he's quite smart and compassionate.  He's self-assured, and he would never, ever stop fighting for a worthwhile cause.",
        "http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350.jpg"
    )

    val CHARACTER_PRESENTATION_THOR = THOR.toCharacterPresentation()

    val THOR_COMICS = listOf(
        CommonItemDetail(
            "Thor Epic Collection: Into The Dark Nebula (Trade Paperback)",
            "http://i.annihil.us/u/prod/marvel/i/mg/4/03/5e7260085e170.jpg"
        ),
        CommonItemDetail(
            "King Thor (Trade Paperback)",
            "http://i.annihil.us/u/prod/marvel/i/mg/a/d0/5e67edbb86989.jpg"
        ),
        CommonItemDetail(
            "Valkyrie: Jane Foster (2019) #9",
            "http://i.annihil.us/u/prod/marvel/i/mg/7/40/5e67d74e7e471.jpg"
        )
    )

    val COMMON_ITEM_DETAIL_COMICS_THOR = THOR_COMICS.toCommonItemPresentation()

    val THOR_SERIES = listOf(
        CommonItemDetail(
            "A+X (2012 - 2014)",
            "http://i.annihil.us/u/prod/marvel/i/mg/5/d0/511e88a20ae34.jpg"
        ),
        CommonItemDetail(
            "Age of Heroes (2010)",
            "http://i.annihil.us/u/prod/marvel/i/mg/2/50/4baa86beaef47.jpg"
        ),
        CommonItemDetail(
            "Alpha Flight (1983 - 1994)",
            "http://i.annihil.us/u/prod/marvel/i/mg/f/50/57c5a37acf5cc.jpg"
        )
    )

    val COMMON_ITEM_DETAIL_SERIES_THOR = THOR_SERIES.toCommonItemPresentation()

}