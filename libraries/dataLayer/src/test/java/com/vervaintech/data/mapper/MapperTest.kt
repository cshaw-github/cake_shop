package com.vervaintech.data.mapper

import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue
import com.vervaintech.data.entities.CakeEntity as DataCake

class MapperTest {

	private lateinit var dataCake: DataCake

	@Before
	fun setUp() {
		dataCake = DataCake(
			title = "Test",
			desc = "Description",
			image = "Image"
		)
	}

	@Test
	fun dataCakeToDomainTest() {
		val domainCake = dataCake.toDomain()
		assertTrue { domainCake.title == dataCake.title }
		assertTrue { domainCake.desc == dataCake.desc }
		assertTrue { domainCake.image == dataCake.image }
	}
}
