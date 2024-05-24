package com.vervaintech.cakeshop.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vervaintech.cakeshop.R
import com.vervaintech.cakeshop.ui.components.progressbar.CircularProgress
import com.vervaintech.cakeshop.ui.components.snackbar.CakeSnackbar
import com.vervaintech.cakeshop.ui.components.snackbar.OnActionPerformed
import com.vervaintech.cakeshop.ui.model.UiStatus
import com.vervaintech.cakeshop.ui.model.ErrorType
import com.vervaintech.utils.Uitls

@Composable
fun ValidateUiStatus(
	uiStatus: UiStatus,
	snackbar: CakeSnackbar,
	progressBarMessage: String = stringResource(R.string.please_wait),
	onActionRetry: OnActionPerformed = {},
) {
	when (uiStatus) {
		is UiStatus.Error -> snackbar.showMessage(
			message = stringResource(getErrorMessage(uiStatus.message)),
			actionLabel = stringResource(R.string.try_again),
			onActionPerformed = onActionRetry,
		)

		is UiStatus.Loading -> {
			CircularProgress(message = progressBarMessage)
		}

		is UiStatus.None,
		is UiStatus.Success -> {
			/*Do Nothing*/
		}
	}
}

private fun getErrorMessage(errorType: String): Int = when (errorType) {
	Uitls.NO_NETWORK -> ErrorType.NetworkOffline.description
	else -> ErrorType.UnknownError.description
}
