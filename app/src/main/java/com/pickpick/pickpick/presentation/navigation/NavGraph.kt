package com.pickpick.pickpick.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.pickpick.pickpick.presentation.login.LoginScreen


import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pickpick.pickpick.presentation.start.StartScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavControllerProvider(
        navController = navController
    ) {
        NavHost(
            navController = navController,
            startDestination = SplashRoute,
            modifier = modifier,
        ) {
            composable<SplashRoute> {
                LaunchedEffect(Unit) {
                    navController.navigate(AuthGraph)
                }
                Text("Splash")
            }

            // 인증 관련 그래프 (로그인, 회원가입 등)
            authGraph(navController, onAuthComplete = {})

        }


    }
}

fun NavGraphBuilder.authGraph(
    navHostController: NavHostController,
    // 인증 완료 콜백 (다른 그래프로 이동)
    onAuthComplete: () -> Unit,
) {
    navigation<AuthGraph>(
        startDestination = AuthRoute.StartRoute
    ) {
        composable<AuthRoute.StartRoute> { backStackEntry ->
            StartScreen(
                onNavigateToMain = onAuthComplete,
                onNavigateToLogin = { navHostController.navigate(AuthRoute.LoginRoute) },
                onNavigateToSignUp = { navHostController.navigate(AuthRoute.SignUpRoute) })
        }
        composable<AuthRoute.LoginRoute> { backStackEntry ->
            LoginScreen(
                onNavigateToMain = onAuthComplete,
                onNavigateToEmail = { navHostController.navigate(AuthRoute.FindEmailRoute) },
                onNavigateToPassword = { navHostController.navigate(AuthRoute.FindPasswordRoute) },
            )
        }
        composable<AuthRoute.FindEmailRoute> { backStackEntry ->
        }
        composable<AuthRoute.FindPasswordRoute> { backStackEntry ->
        }
        composable<AuthRoute.SignUpRoute> { backStackEntry ->
        }
        composable<AuthRoute.PolicyRoute> { backStackEntry ->
        }
        composable<AuthRoute.UpdatePasswordRoute> { backStackEntry ->
        }
        composable<AuthRoute.CompleteRoute> { backStackEntry ->
        }
    }
}
