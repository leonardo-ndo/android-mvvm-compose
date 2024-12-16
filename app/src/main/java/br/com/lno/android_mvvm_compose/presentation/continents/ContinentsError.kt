package br.com.lno.android_mvvm_compose.presentation.continents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.lno.android_mvvm_compose.R

@Composable
fun ContinentsError(onRetry: () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.error_title),
            fontSize = 22.sp
        )
        Text(text = stringResource(id = R.string.error_message))
        Button(onClick = { onRetry() }) {
            Text(
                text = stringResource(id = R.string.error_try_again)
            )
        }
    }
}