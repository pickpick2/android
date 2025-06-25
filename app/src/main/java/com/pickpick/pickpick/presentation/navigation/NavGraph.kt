package com.pickpick.pickpick.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.pickpick.pickpick.presentation.login.LoginScreen


import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pickpick.pickpick.presentation.info.InfoScreen
import com.pickpick.pickpick.presentation.info.viewmodel.InfoViewModel
import com.pickpick.pickpick.presentation.main.MainScreen
import com.pickpick.pickpick.presentation.policy.PolicyScreen
import com.pickpick.pickpick.presentation.signup.SignUpScreen
import com.pickpick.pickpick.presentation.signup.viewmodel.SignUpViewModel
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

            // 정보 입력 그래프
            infoGraph(navController, onInfoComplete = {})

            // 메인 그래프
            mainGraph(navController)
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
            // ✅ 중첩 그래프 레벨에서 ViewModel 생성 (AuthGraph 스코프)
            val authGraphEntry = remember(backStackEntry) {
                navHostController.getBackStackEntry<AuthRoute.SignUpRoute>()
            }
            val viewModel = hiltViewModel<SignUpViewModel>(authGraphEntry)
            SignUpScreen(
                viewModel = viewModel,
                onNavigateToPolicy = { navHostController.navigate(AuthRoute.PolicyRoute) },
                onNavigateToComplete = { navHostController.navigate(AuthRoute.CompleteRoute) })
        }
        composable<AuthRoute.PolicyRoute> { backStackEntry ->
            val authGraphEntry = remember(backStackEntry) {
                navHostController.getBackStackEntry<AuthRoute.SignUpRoute>()
            }
            val viewModel = hiltViewModel<SignUpViewModel>(authGraphEntry)

            PolicyScreen(
                viewModel = viewModel, onNavigateToSignUp = { navHostController.popBackStack() })
        }
        composable<AuthRoute.UpdatePasswordRoute> { backStackEntry ->
        }
        composable<AuthRoute.CompleteRoute> { backStackEntry ->
        }
    }
}

fun NavGraphBuilder.infoGraph(
    navHostController: NavHostController,
    onInfoComplete: () -> Unit,
) {

    navigation<InfoGraph>(
        startDestination = InfoRoute.ProfileRoute
    ) {
        composable<InfoRoute.ProfileRoute> { backStackEntry ->
            val infoGraphEntry = remember(backStackEntry) {
                navHostController.getBackStackEntry<InfoRoute.ProfileRoute>()
            }
            val viewModel = hiltViewModel<InfoViewModel>(infoGraphEntry)

            InfoScreen(
                viewModel = viewModel,
                onNavigateToComplete = onInfoComplete
            )
        }

        composable<InfoRoute.CompleteRoute> { backStackEntry ->

        }
    }

}

fun NavGraphBuilder.mainGraph(
    navHostController: NavHostController,
) {

    navigation<MainGraph>(
        startDestination = MainRoute.StartRoute
    ) {
        composable<MainRoute.StartRoute> { backStackEntry ->
            MainScreen(
                onCameraClick = {},
                onGalleryClick = {}
            )
        }
    }

}
