package com.pickpick.pickpick.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.pickpick.pickpick.presentation.SplashScreen
import com.pickpick.pickpick.presentation.album.AlbumScreen
import com.pickpick.pickpick.presentation.auth.complete.CompleteScreen
import com.pickpick.pickpick.presentation.auth.findemail.FindEmailScreen
import com.pickpick.pickpick.presentation.auth.findpassword.FindPasswordScreen
import com.pickpick.pickpick.presentation.auth.info.InfoScreen
import com.pickpick.pickpick.presentation.auth.info.viewmodel.InfoViewModel
import com.pickpick.pickpick.presentation.auth.login.LoginScreen
import com.pickpick.pickpick.presentation.auth.policy.PolicyScreen
import com.pickpick.pickpick.presentation.auth.resetpw.ResetPWScreen
import com.pickpick.pickpick.presentation.auth.signup.SignUpScreen
import com.pickpick.pickpick.presentation.auth.signup.SignupCompleteScreen
import com.pickpick.pickpick.presentation.auth.signup.viewmodel.SignUpViewModel
import com.pickpick.pickpick.presentation.auth.start.StartScreen
import com.pickpick.pickpick.presentation.main.MainScreen
import com.pickpick.pickpick.presentation.pick.backgroundresult.BackgroundResultScreen
import com.pickpick.pickpick.presentation.pick.captureresult.CaptureResultScreen
import com.pickpick.pickpick.presentation.pick.imagedecorator.DrawingScreen
import com.pickpick.pickpick.presentation.pick.room.CreateRoomScreen
import com.pickpick.pickpick.presentation.pick.room.ReadyScreen
import com.pickpick.pickpick.presentation.pick.selectbackground.SelectBackgroundScreen
import com.pickpick.pickpick.presentation.pick.selectframe.FrameResultScreen
import com.pickpick.pickpick.presentation.pick.selectframe.SelectFrameScreen
import com.pickpick.pickpick.presentation.pick.selectframe.viewmodel.FrameViewModel
import com.pickpick.pickpick.presentation.pick.selectslot.SelectSlotScreen
import com.pickpick.pickpick.presentation.pick.takepicture.TakePictureScreen


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
                        navController.navigate(AuthRoute.StartRoute) {
                            popUpTo(SplashRoute) {
                                inclusive = true
                            }
                        }
                    })
            }

            // 인증 관련 그래프 (로그인, 회원가입 등)
            authGraph(navController, onAuthComplete = {
                navController.navigate(MainRoute.StartRoute) {
                    popUpTo(AuthRoute.StartRoute) {
                        inclusive = true
                    }
                }
            })

            // 픽픽 그래프
            pickGraph(navController, onPickComplete = {})

            // 정보 입력 그래프
            infoGraph(navController, onInfoComplete = {})

            // 메인 그래프
            mainGraph(navController)

            // 앨범 그래프
            albumGraph(navController)
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
                onNavigateToComplete = { navHostController.navigate(InfoRoute.ProfileRoute) })
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
                })
        }
    }
}


fun NavGraphBuilder.pickGraph(
    navHostController: NavHostController,
    onPickComplete: () -> Unit,
) {
    navigation<PickGraph>(
        startDestination = PickRoute.CreateRoomRoute
    ) {
        composable<PickRoute.CreateRoomRoute> { backStackEntry ->
            CreateRoomScreen(
                onNavigateToComplete = {
                    navHostController.navigate(PickRoute.ReadyRoute)
                },
                onBackClick = {
                    navHostController.popBackStack()
                }
            )
        }

        composable<PickRoute.ReadyRoute> { backStackEntry ->
            ReadyScreen(
                onNavigatePickStart = {
                    navHostController.navigate(PickRoute.SelectFrameRoute)
                },
                onBackClick = {
                    navHostController.popBackStack()
                }
            )
        }

        composable<PickRoute.SelectFrameRoute> { backStackEntry ->
            val pickGraphEntry = remember(backStackEntry) {
                navHostController.getBackStackEntry<PickRoute.SelectFrameRoute>()
            }

            val viewModel = hiltViewModel<FrameViewModel>(pickGraphEntry)

            SelectFrameScreen(
                viewModel = viewModel,
                onNavigateToComplete = {
                    navHostController.navigate(PickRoute.FrameResultRoute)
                }
            )
        }

        composable<PickRoute.FrameResultRoute> { backStackEntry ->
            val pickGraphEntry = remember(backStackEntry) {
                navHostController.getBackStackEntry<PickRoute.SelectFrameRoute>()
            }

            val viewModel = hiltViewModel<FrameViewModel>(pickGraphEntry)

            FrameResultScreen(
                viewModel = viewModel,
                onNavigateToNext = {
                    navHostController.navigate(PickRoute.SelectBackgroundRoute)
                }
            )
        }

        composable<PickRoute.SelectBackgroundRoute> { backStackEntry ->
            SelectBackgroundScreen(
                onNavigateToResult = {
                    navHostController.navigate(PickRoute.BackgroundResultRoute)
                })
        }
        composable<PickRoute.BackgroundResultRoute> { backStackEntry ->
            BackgroundResultScreen(
                onNavigateToSlot = {
                    navHostController.navigate(PickRoute.SelectSlotRoute)
                })
        }
        composable<PickRoute.SelectSlotRoute> { backStackEntry ->
            SelectSlotScreen(
                onNavigateToPicture = {
                    navHostController.navigate(PickRoute.TakePictureRoute)
                })
        }
        composable<PickRoute.TakePictureRoute> { backStackEntry ->
            TakePictureScreen(
                onNavigateToResult = {
                    navHostController.navigate(PickRoute.PictureResultRoute)
                })
        }

        composable<PickRoute.PictureResultRoute> { backStackEntry ->
            CaptureResultScreen(
                onNavigateToDecorate = {
                    navHostController.navigate(PickRoute.PictureDecorateRoute)
                })
        }

        composable<PickRoute.PictureDecorateRoute> { backStackEntry ->
            DrawingScreen()
//            TestScreen()
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
            SignupCompleteScreen(
                onNavigateToMain = {
                    navHostController.navigate(MainRoute.StartRoute)
                }
            )
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
                onCameraClick = {
                    navHostController.navigate(PickGraph)
                },
                onGalleryClick = {
                    navHostController.navigate(AlbumRoute.AlbumListRoute)
                }
            )
        }
    }

}

fun NavGraphBuilder.albumGraph(
    navHostController: NavHostController,
) {
    navigation<AlbumGraph>(
        startDestination = AlbumRoute.AlbumListRoute
    ) {
        composable<AlbumRoute.AlbumListRoute> {
            AlbumScreen(
                onBackClick = {
                    navHostController.popBackStack()
                }
            )
        }
    }
}
