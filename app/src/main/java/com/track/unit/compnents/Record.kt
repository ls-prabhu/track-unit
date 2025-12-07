package com.track.unit.compnents

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.track.unit.data.UnitRepository
import com.track.unit.data.Units
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun Record(unit : Units) {
    val dateFormat = SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault())
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp)) {

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Date : ${dateFormat.format(unit.today)}")
            Text(text = "Units : ${unit.unit}")
            Text(text = "Price : ${unit.price}")
            Text(text = "amount : ${unit.amount} $")
        }

    }
}