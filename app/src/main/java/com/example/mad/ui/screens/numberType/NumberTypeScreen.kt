package com.example.mad.ui.screens.numberType

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mad.R

const val TYPE_MATH = "\uD83E\uDDEE  Random math"
const val TYPE_YEAR = "\uD83D\uDCC5  Random year"
const val TYPE_TRIVIA = "\uD83D\uDE01  Trivia"

@Composable
fun NumberTypeScreen(onClickDetail: (numberType: String) -> Unit = {}, ) {
    //for the overview screen we use a hardcoded list base on the constants defined above
    val numberTypes by remember { mutableStateOf(listOf(TYPE_TRIVIA, TYPE_YEAR, TYPE_MATH)) }

    Column {
        Text(
            text = stringResource(R.string.overview_header),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.h4,
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .align(Alignment.CenterHorizontally)
        )

        LazyColumn {
            items(items = numberTypes) { numberType: String ->
                Surface(modifier = Modifier.clickable { onClickDetail(numberType) }) {
                    NumberTypeCard(numberType = numberType)
                }
            }
        }
    }
}

@Composable
fun NumberTypeCard(numberType: String) {
    Card(
        backgroundColor = Color.White,
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = numberType,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(16.dp),
            style = TextStyle(fontSize = 22.sp)
        )
    }
}