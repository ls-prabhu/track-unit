package com.track.unit.logs// kotlin
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import com.track.unit.compnents.Record
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.track.unit.data.UnitRepository
import kotlinx.coroutines.launch

// 1) BottomSheetScaffold (from androidx.compose.material)
// Pros: built-in scaffold behavior, swipe to collapse/expand, integrated with scaffold content.
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun History(repository: UnitRepository,form: @Composable () -> Unit) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    val Records = repository.readAllData.collectAsState(initial = emptyList())
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showBottomSheet = true
                scope.launch {
                    sheetState.show()
                }
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        // The main content of the History screen goes here

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)) {
            items(Records.value.size){
                Record(Records.value[it])
            }
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                    scope.launch {
                        sheetState.hide()
                    }
                },
                sheetState = sheetState
            ) {
                form()
            }
        }
    }
}
