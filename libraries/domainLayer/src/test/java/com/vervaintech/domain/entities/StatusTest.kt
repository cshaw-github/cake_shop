package com.vervaintech.domain.entities

import org.junit.Before
import org.junit.Test
import kotlin.test.assertTrue


class StatusTest {

	private lateinit var status: Status

	@Before
	fun setUp() {
		status = Status(
			data = emptyList(),
			successful = true,
			error = "Not Found"
		)
	}

	@Test
	fun getData() {
		assertTrue { status.data.isEmpty() }
	}

	@Test
	fun getSuccessful() {
		assertTrue { status.successful }
	}

	@Test
	fun getError() {
		assertTrue { status.error == "Not Found" }
	}
}
