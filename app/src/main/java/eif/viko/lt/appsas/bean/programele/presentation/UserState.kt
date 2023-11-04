package eif.viko.lt.appsas.bean.programele.presentation

import eif.viko.lt.appsas.bean.programele.data.User

data class UserState(
    val users: List<User> = emptyList(),
    val isLoading: Boolean = false,
    val token: String = "",
    val error: String = ""
)