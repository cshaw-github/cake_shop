package com.vervaintech.data.mapper

import com.vervaintech.domain.entities.CakeEntity as DomainCake
import com.vervaintech.data.entities.CakeEntity as DataCake


fun DataCake.toDomain() =  DomainCake(title, desc, image)
