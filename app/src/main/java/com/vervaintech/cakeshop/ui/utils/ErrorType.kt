package com.vervaintech.cakeshop.ui.utils

import com.vervaintech.cakeshop.R

sealed class ErrorType(val errorType: String, val description: Int) {

	data object NetworkOffline : ErrorType(
		errorType = NO_NETWORK,
		description = R.string.no_internet,
	)

	data object UnknownError : ErrorType(
		errorType = UNKNOWN_ERROR,
		description = R.string.unknown_error,
	)

	companion object {
		const val NO_NETWORK = "NO_NETWORK"
		const val UNKNOWN_ERROR = "UNKNOWN_ERROR"
	}
}
