package eif.viko.lt.appsas.bean.programele.presentation.auth

import eif.viko.lt.appsas.bean.programele.data.remote.auth.ProductDto
import eif.viko.lt.appsas.bean.programele.data.remote.auth.UserDto

data class UserState(
    val users: List<UserDto> = emptyList(),
    val products: List<ProductDto> = emptyList(),
    val isLoading: Boolean = false,
    var tokenas: String = "",
    val error: String = ""
)