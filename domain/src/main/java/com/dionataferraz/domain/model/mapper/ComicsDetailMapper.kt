package com.dionataferraz.domain.model.mapper

import com.dionataferraz.data.model.Comic
import com.dionataferraz.domain.model.ComicDetail

fun List<Comic>.toComicsDetail() = map { comic ->
    ComicDetail(
        title = comic.title,
        image = comic.thumbnail.run { "$path.$extension" }
    )
}
