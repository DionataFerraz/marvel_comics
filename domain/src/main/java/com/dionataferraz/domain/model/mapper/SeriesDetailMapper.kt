package com.dionataferraz.domain.model.mapper

import com.dionataferraz.data.model.Serie
import com.dionataferraz.domain.model.SerieDetail

fun List<Serie>.toSerieDetail() = map { comic ->
    SerieDetail(
        title = comic.title,
        image = comic.thumbnail.run { "$path.$extension" }
    )
}
