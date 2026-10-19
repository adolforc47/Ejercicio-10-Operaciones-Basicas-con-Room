package com.mexiti.costogasolina.navigation

import com.mexiti.costogasolina.ui.views.DeleteView
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.mexiti.costogasolina.ui.views.EditView
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mexiti.costogasolina.room.GasolineDao
import com.mexiti.costogasolina.ui.views.AddView
import com.mexiti.costogasolina.ui.views.HomeView
import com.mexiti.costogasolina.viewmodel.GasolineViewModel

@Composable
fun NavManager(dao: GasolineDao){
    val navController = rememberNavController()
    val viewModel: GasolineViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                GasolineViewModel(dao)
            }
        }
    )

    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(navController, viewModel)
        }
        composable ("AddView") {
            AddView(navController, viewModel)
        }
        composable(
            route = "EditView/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            EditView(
                transactionId = id,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable(
            route = "DeleteView/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0

            DeleteView(
                transactionId = id,
                navController = navController,
                viewModel = viewModel
            )
        }

    }


}