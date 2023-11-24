package eif.viko.lt.appsas.bean.programele.presentation

import eif.viko.lt.appsas.bean.programele.data.ProductDto
import eif.viko.lt.appsas.bean.programele.data.UserDto

data class UserState(
    val users: List<UserDto> = emptyList(),
    val products: List<ProductDto> = emptyList(),
    val isLoading: Boolean = false,
    var tokenas: String = "",
    val error: String = ""
)