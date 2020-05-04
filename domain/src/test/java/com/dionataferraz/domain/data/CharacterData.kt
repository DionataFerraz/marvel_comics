package com.dionataferraz.domain.data

import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.CommonItem
import com.dionataferraz.data.model.Thumbnail
import com.dionataferraz.domain.model.mapper.toCharacterDetail
import com.dionataferraz.domain.model.mapper.toCommonItemDetail

object CharacterData {

    val THOR = Character(
        1009664,
        "Thor",
        "As the Norse God of thunder and lightning, Thor wields one of the greatest weapons ever made, the enchanted hammer Mjolnir. While others have described Thor as an over-muscled, oafish imbecile, he's quite smart and compassionate.  He's self-assured, and he would never, ever stop fighting for a worthwhile cause.",
        Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/d/d0/5269657a74350", "jpg")
    )

    val CHARACTER_DETAIL_THOR = THOR.toCharacterDetail()

    val THOR_COMICS = listOf(
        CommonItem(
            "Thor Epic Collection: Into The Dark Nebula (Trade Paperback)",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/4/03/5e7260085e170", "jpg")
        ),
        CommonItem(
            "King Thor (Trade Paperback)",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/a/d0/5e67edbb86989", "jpg")
        ),
        CommonItem(
            "Valkyrie: Jane Foster (2019) #9",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/7/40/5e67d74e7e471", "jpg")
        )
    )

    val COMMON_ITEM_DETAIL_COMICS_THOR = THOR_COMICS.toCommonItemDetail()

    val THOR_SERIES = listOf(
        CommonItem(
            "A+X (2012 - 2014)",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/5/d0/511e88a20ae34", "jpg")
        ),
        CommonItem(
            "Age of Heroes (2010)",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/2/50/4baa86beaef47", "jpg")
        ),
        CommonItem(
            "Alpha Flight (1983 - 1994)",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/f/50/57c5a37acf5cc", "jpg")
        )
    )

    val COMMON_ITEM_DETAIL_SERIES_THOR = THOR_SERIES.toCommonItemDetail()

}