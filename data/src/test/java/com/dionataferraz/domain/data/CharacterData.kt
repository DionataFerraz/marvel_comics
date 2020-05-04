package com.dionataferraz.domain.data

import com.dionataferraz.data.model.Character
import com.dionataferraz.data.model.CommonItem
import com.dionataferraz.data.model.Thumbnail

object CharacterData {

    val IRON_MAN = Character(
        1009368,
        "Iron Man",
        "Wounded, captured and forced to build a weapon by his enemies, billionaire industrialist Tony Stark instead created an advanced suit of armor to save his life and escape captivity. Now with a new outlook on life, Tony uses his money and intelligence to make the world a safer, better place as Iron Man.",
        Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/9/c0/527bb7b37ff55", "jpg")
    )

    val IRON_MAN_COMICS = listOf(
        CommonItem(
            "Wolverine Saga (2009) #7",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available", "jpg")
        ),
        CommonItem(
            "Cap Transport (2005) #13Cap Transport (2005) #13",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available", "jpg")
        ),
        CommonItem(
            "True Believers: Iron Man 2020 - War Machine (2020) #1",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/3/70/5e503c8238b91", "jpg")
        )
    )

    val IRON_MAN_SERIES = listOf(
        CommonItem(
            "A+X (2012 - 2014)",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/5/d0/511e88a20ae34", "jpg")
        ),
        CommonItem(
            "Adam: Legend of the Blue Marvel (2008)",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/9/20/4bb4f0966a26a", "jpg")
        ),
        CommonItem(
            "Adam: Legend of the Blue Marvel (2008)",
            Thumbnail("http://i.annihil.us/u/prod/marvel/i/mg/3/20/5449710ac36d2", "jpg")
        )
    )
}