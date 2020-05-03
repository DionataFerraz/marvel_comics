package com.dionataferraz.presentation.model.mapper

import com.dionataferraz.domain.model.ComicDetail
import com.dionataferraz.presentation.model.ComicPresentation

fun List<ComicDetail>.toComicPresentation() = map { comicDetail ->
    ComicPresentation(
        title = comicDetail.title,
        image = comicDetail.image
    )
}