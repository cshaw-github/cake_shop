package com.vervaintech.cakeshop.ui.model

import com.vervaintech.cakeshop.R
import com.vervaintech.utils.Uitls.NO_NETWORK
import com.vervaintech.utils.Uitls.UNKNOWN_ERROR

sealed class ErrorType(val errorType: String, val description: Int) {

	data object NetworkOffline : ErrorType(
		errorType = NO_NETWORK,
		description = R.string.no_internet,
	)

	data object UnknownError : ErrorType(
		errorType = UNKNOWN_ERROR,
		description = R.string.unknown_error,
	)
}
