package com.example.mad.ui.screens.numberDetail

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad.R
import com.example.mad.data.api.util.Resource
import com.example.mad.data.model.Numbers

@Composable
fun NumberDetailScreen(
    numberType: String,
    viewModel: NumbersViewModel = NumbersViewModel(Application())
) {
    // Make sure to import :
    // import androidx.compose.runtime.getValue
    // import androidx.compose.runtime.livedata.observeAsState
    val numberResource: Resource<Numbers>? by viewModel.numberResource.observeAsState()


    /*
       Determine in which state what to show
       You could expand this in the future by for example also adding a loading indicator
     */
    val numberText = when (numberResource) {
        is Resource.Success -> numberResource?.data?.text
        is Resource.Error -> numberResource?.message
        is Resource.Loading -> stringResource(R.string.loading_text)
        is Resource.Empty -> stringResource(id = R.string.empty_number_placeholder)
        else -> stringResource(R.string.something_wrong_state)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = numberType,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )

        Text(
            text = numberText ?: stringResource(id = R.string.empty_number_placeholder),
            textAlign = TextAlign.Center,
            style = TextStyle(fontSize = 20.sp),
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 8.dp)
                .heightIn(min = 96.dp)
        )
        //heightIn = min height for varying text length

        ExtendedFloatingActionButton(
            text = { Text(text = stringResource(R.string.get_new_number)) },
            onClick = { viewModel.getNumber(numberType) },
            icon = { Icon(Icons.Filled.Refresh, "") },
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
        )
    }
}