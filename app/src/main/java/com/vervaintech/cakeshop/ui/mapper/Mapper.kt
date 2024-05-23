package com.vervaintech.cakeshop.ui.mapper

import com.vervaintech.cakeshop.ui.model.CakeEntity as UiCakeEntity
import com.vervaintech.domain.entities.CakeEntity as DomainCakeEntity

fun DomainCakeEntity.toUi() = UiCakeEntity(
	title = this.title,
	desc = this.desc,
	image = this.image
)
