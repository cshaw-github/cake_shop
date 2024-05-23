package com.vervaintech.cakeshop.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vervaintech.cakeshop.R
import com.vervaintech.cakeshop.ui.components.progressbar.CircularProgress
import com.vervaintech.cakeshop.ui.components.snackbar.CakeSnackbar
import com.vervaintech.cakeshop.ui.components.snackbar.OnActionPerformed
import com.vervaintech.cakeshop.ui.model.UiStatus

@Composable
fun ValidateUiStatus(
	uiStatus: UiStatus,
	snackbar: CakeSnackbar,
	errorMessage: String = stringResource(R.string.no_internet),
	actionLabel: String = stringResource(R.string.try_again),
	progressBarMessage: String = stringResource(R.string.please_wait),
	onActionPerformed: OnActionPerformed = {},
	onSuccess: () -> Unit,
) {
    when (uiStatus) {
        UiStatus.Success -> onSuccess()

        UiStatus.Error -> snackbar.showMessage(
            message = errorMessage,
            actionLabel = actionLabel,
            onActionPerformed = onActionPerformed,
        )

        UiStatus.Loading -> {
            CircularProgress(message = progressBarMessage)
        }

        UiStatus.None -> {
            /*Do Nothing*/
        }
    }
}
