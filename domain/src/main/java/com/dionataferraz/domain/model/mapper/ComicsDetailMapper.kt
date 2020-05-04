package com.dionataferraz.domain.model.mapper

import com.dionataferraz.data.model.CommonItem
import com.dionataferraz.domain.model.CommonItemDetail

fun List<CommonItem>.toCommonItemDetail() = map { comic ->
    CommonItemDetail(
        title = comic.title,
        image = comic.thumbnail.run { "$path.$extension" }
    )
}
