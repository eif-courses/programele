package eif.viko.lt.appsas.bean.programele.domain.repositories


import eif.viko.lt.appsas.bean.programele.data.ProductDto
import eif.viko.lt.appsas.bean.programele.data.UserDto
import eif.viko.lt.appsas.bean.programele.domain.models.GoogleTokenId
import eif.viko.lt.appsas.bean.programele.domain.utils.AuthResult
import eif.viko.lt.faculty.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    suspend fun signIn(token: GoogleTokenId): AuthResult<Unit>
    suspend fun me(): AuthResult<Unit>
    fun getAllUsers(): Flow<Resource<List<UserDto>>>
    fun getProducts() : Flow<Resource<List<ProductDto>>>

}