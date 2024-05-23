import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.vervaintech.cakeshop.R

@Composable
fun CakeDialog(
	title: String,
	description: String,
	onDismissRequest: () -> Unit,
) {
	AlertDialog(
		title = {
			Text(text = title)
		},
		text = {
			Text(text = description)
		},
		onDismissRequest = {},
		confirmButton = {},
		dismissButton = {
			TextButton(
				onClick = {
					onDismissRequest()
				}
			) {
				Text(stringResource(R.string.close))
			}
		}
	)
}
