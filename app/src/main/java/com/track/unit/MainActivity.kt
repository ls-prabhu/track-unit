@file:Suppress("UnusedMaterial3ScaffoldPaddingParameter")
package com.track.unit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.compose.AppTheme
import com.track.unit.data.UnitDatabase
import com.track.unit.data.UnitRepository
import com.track.unit.main.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = UnitDatabase.getInstance(this)
        val unitRepository = UnitRepository(database.unitDao())

        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    MainScreen(unitRepository = unitRepository)
                }
            }
        }
    }
}

