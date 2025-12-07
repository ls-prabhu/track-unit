@file:Suppress("UnusedMaterial3ScaffoldPaddingParameter")
package com.track.unit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.example.compose.AppTheme
import com.track.unit.data.UnitDatabase
import com.track.unit.data.UnitRepository
import com.track.unit.main.MainScreen

data class tabBar(
    val title: String,
    val selecteditem : ImageVector,
    val unselecteditem : ImageVector,
    val batches : Int ? = null
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = UnitDatabase.getInstance(this)
        val unitRepository = UnitRepository(database.unitDao())

        setContent {
            val navController = rememberNavController()
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavBar(navController = navController) }
                ) { innerpadding->
                    NavHostContainer(
                        navHostController = navController,
                        innerpadding = innerpadding,
                        unitRepository = unitRepository
                    )
                }
            }
        }
    }
}

