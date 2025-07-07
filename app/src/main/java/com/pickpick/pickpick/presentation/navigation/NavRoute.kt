package com.pickpick.pickpick.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object SplashRoute


@Serializable
data object AuthGraph

@Serializable
sealed class AuthRoute {

    @Serializable
    data object StartRoute : AuthRoute()

    @Serializable
    data object LoginRoute : AuthRoute()

    @Serializable
    data object FindEmailRoute : AuthRoute()

    @Serializable
    data object FindPasswordRoute : AuthRoute()

    @Serializable
    data object SignUpRoute : AuthRoute()

    @Serializable
    data object PolicyRoute : AuthRoute()

    @Serializable
    data object ResetPasswordRoute : AuthRoute()

    // 회원가입 완료, 비밀번호 변경 완료
    @Serializable
    data object CompleteRoute : AuthRoute()

}


@Serializable
data object PickGraph

@Serializable
sealed class PickRoute {

    @Serializable
    data object SelectBackgroundRoute : PickRoute()

    @Serializable
    data object BackgroundResultRoute : PickRoute()

    @Serializable
    data object SelectSlotRoute : PickRoute()

    @Serializable
    data object TakePictureRoute : PickRoute()

    @Serializable
    data object PictureResultRoute : PickRoute()

    @Serializable
    data object PictureDecorateRoute : PickRoute()

}
