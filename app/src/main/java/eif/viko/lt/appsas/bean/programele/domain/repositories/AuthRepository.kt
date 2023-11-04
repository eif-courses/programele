package eif.viko.lt.appsas.bean.programele.domain.repositories

import eif.viko.lt.appsas.bean.programele.data.User
import eif.viko.lt.appsas.bean.programele.domain.models.GoogleTokenId
import eif.viko.lt.appsas.bean.programele.domain.utils.AuthResult
import eif.viko.lt.faculty.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun getAccessToken(token: GoogleTokenId): AuthResult<Unit>
    fun authenticate(): Flow<Resource<List<User>>>
}