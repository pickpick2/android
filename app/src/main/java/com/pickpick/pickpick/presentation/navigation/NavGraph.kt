package com.pickpick.pickpick.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.pickpick.pickpick.presentation.login.LoginScreen


import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pickpick.pickpick.presentation.complete.CompleteScreen
import com.pickpick.pickpick.presentation.findemail.FindEmailScreen
import com.pickpick.pickpick.presentation.findpassword.FindPasswordScreen
import com.pickpick.pickpick.presentation.policy.PolicyScreen
import com.pickpick.pickpick.presentation.resetpw.ResetPWScreen
import com.pickpick.pickpick.presentation.signup.SignUpScreen
import com.pickpick.pickpick.presentation.signup.viewmodel.SignUpViewModel
import com.pickpick.pickpick.presentation.splash.SplashScreen
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
                SplashScreen(
                    onNavigateToLogin = {
                        navController.navigate(AuthGraph) {
                            popUpTo(SplashRoute) {
                                inclusive = true
                            }
                        }
                    }
                )
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
            FindEmailScreen(
                onNavigateToLogin = {
                    navHostController.navigate(AuthRoute.LoginRoute) {
                        popUpTo(AuthRoute.LoginRoute) {
                            inclusive = true
                        }
                    }
                },
                onNavigateToPassword = { navHostController.navigate(AuthRoute.FindPasswordRoute) },
            )
        }
        composable<AuthRoute.FindPasswordRoute> { backStackEntry ->
            FindPasswordScreen(
                onNavigateToResetPW = { navHostController.navigate(AuthRoute.ResetPasswordRoute) },
                onNavigateToEmail = {
                    navHostController.navigate(AuthRoute.FindEmailRoute) {
                        popUpTo(AuthRoute.FindEmailRoute) {
                            inclusive = true
                        }
                    }
                })
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
        composable<AuthRoute.ResetPasswordRoute> { backStackEntry ->
            ResetPWScreen(
                onNavigateToComplete = { navHostController.navigate(AuthRoute.CompleteRoute) })
        }
        composable<AuthRoute.CompleteRoute> { backStackEntry ->
            CompleteScreen(
                onNavigateToLogin = {
                    navHostController.navigate(AuthRoute.LoginRoute) {
                        popUpTo(AuthRoute.LoginRoute) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
