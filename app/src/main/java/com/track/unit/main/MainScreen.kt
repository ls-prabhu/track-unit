@file:OptIn(ExperimentalMaterial3Api::class)

package com.track.unit.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.track.unit.compnents.Form
import com.track.unit.data.UnitRepository

@Composable
fun MainScreen(unitRepository: UnitRepository) {
    val showSheet = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        FloatingActionButton(
            onClick = { showSheet.value = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(32.dp)
        ) {
            Text("+")
        }

        if(showSheet.value){
            ModalBottomSheet(onDismissRequest = { showSheet.value = false }){
                Form(Repository = unitRepository)
            }
        }

    }

}