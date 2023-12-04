package eif.viko.lt.appsas.bean.programele.presentation.auth

sealed class UserAuthUiEvent {

//    data class OneTapSignInToken(val value: String): UserAuthUiEvent()
    object OneTapSignIn: UserAuthUiEvent()
}
