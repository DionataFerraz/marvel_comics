package com.dionataferraz.presentation.model.mapper

import com.dionataferraz.domain.model.SerieDetail
import com.dionataferraz.presentation.model.SeriePresentation

fun List<SerieDetail>.toSeriePresentation() = map { serieDetail ->
    SeriePresentation(
        title = serieDetail.title,
        image = serieDetail.image
    )
}