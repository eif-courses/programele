package eif.viko.lt.appsas.bean.programele.data.remote.auth

data class UserDto(
    val CreatedAt: String,
    val DeletedAt: String?,
    val ID: Int,
    val UpdatedAt: String,
    val email: String,
    val name: String,
    val password: String
)