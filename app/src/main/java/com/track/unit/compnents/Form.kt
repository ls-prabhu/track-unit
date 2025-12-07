package com.track.unit.compnents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.material3.DatePickerDefaults.dateFormatter
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.track.unit.data.UnitRepository
import com.track.unit.data.Units
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


@Composable
fun Form(Repository: UnitRepository) {
    var currentunit by rememberSaveable { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val datepickerstate = rememberDatePickerState()
    var datevalue by rememberSaveable {
        mutableStateOf(
            java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
                .format(java.util.Date())
        )
    }
    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = currentunit,
            onValueChange = { currentunit = it },
            label = { Text("Enter Units") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1
        )
        OutlinedTextField(
            value = datevalue,
            onValueChange = { datevalue = it },
            label = { Text("date") } ,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            maxLines = 1,
            trailingIcon = {
                IconButton(onClick = {showDatePicker = !showDatePicker}) {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = "select date")
                }
            }
        )
        if (showDatePicker){
            DatePickerDialog(
                onDismissRequest = { showDatePicker = !showDatePicker },
                confirmButton = { TextButton(
                    onClick = {
                        datepickerstate.selectedDateMillis?.let{ millis->
                            val date = java.util.Date(millis)
                            val formatter = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
                            datevalue = formatter.format(date)
                        }
                        showDatePicker = !showDatePicker
                        }) { Text("OK") } },
                dismissButton = { TextButton(onClick = { showDatePicker = !showDatePicker }) { Text("Cancel") } },
            ){
                DatePicker(
                    state = datepickerstate,

                )
            }
        }

        Button(
            onClick = {
                if (currentunit.isNotBlank()) {
                    val dateMillis = parseDayMonthYearToMillis(datevalue) ?: System.currentTimeMillis()
                    scope.launch {
                        Repository.insertFullData(
                            Unit = currentunit.toInt(),
                            SelectedDate = dateMillis
                        )
                        currentunit = ""
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Add",
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Add")
        }
    }
}


fun parseDayMonthYearToMillis(dateStr: String): Long? {
    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return try {
        sdf.parse(dateStr)?.time
    } catch (e: Exception) {
        null
    }
}