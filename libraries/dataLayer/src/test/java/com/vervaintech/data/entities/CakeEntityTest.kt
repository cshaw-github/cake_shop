package com.vervaintech.data.entities

import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class CakeEntityTest {

	private lateinit var cake: CakeEntity

	@Before
	fun setUp() {
		cake = CakeEntity(
			title = "Test",
			desc = "Description",
			image = "Image"
		)
	}

	@Test
	fun getTitle() {
		assertEquals("Test", cake.title)
	}

	@Test
	fun getDesc() {
		assertEquals("Description", cake.desc)
	}

	@Test
	fun getImage() {
		assertEquals("Image", cake.image)
	}
}
