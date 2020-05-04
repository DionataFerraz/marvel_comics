package com.dionataferraz.presentation.model.mapper

import com.dionataferraz.domain.model.CommonItemDetail
import com.dionataferraz.presentation.model.CommonItemPresentation

fun List<CommonItemDetail>.toCommonItemPresentation() = map { comicDetail ->
    CommonItemPresentation(
        title = comicDetail.title,
        image = comicDetail.image
    )
}