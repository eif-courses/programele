package eif.viko.lt.appsas.bean.programele.data

import eif.viko.lt.appsas.bean.programele.domain.models.GoogleTokenId
import eif.viko.lt.appsas.bean.programele.domain.repositories.AuthRepository
import eif.viko.lt.appsas.bean.programele.domain.utils.AuthResult
import eif.viko.lt.appsas.bean.programele.domain.utils.ServiceLocator.preferencesManager
import eif.viko.lt.faculty.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: PersonalBackendApi
) : AuthRepository {

    override suspend fun signIn(token: GoogleTokenId): AuthResult<Unit> {
        return try {
            val response = api.getAccessToken(token)
            preferencesManager.saveData("jwt", response.access_token)
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }



    override suspend fun me(): AuthResult<Unit> {
        return try {
            val token = preferencesManager.getData("jwt", "") ?: return AuthResult.Unauthorized()
            api.me("Bearer $token")
            AuthResult.Authorized()
        } catch(e: HttpException) {
            if(e.code() == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
    override fun getProducts(): Flow<Resource<List<ProductDto>>> = flow {
        emit(Resource.Loading())
        val token = preferencesManager.getData("jwt", "") ?: return@flow emit(Resource.Error("Unauthorized"))
        val remoteData = api.getProducts("Bearer $token")
        emit(Resource.Success(data = remoteData))
    }

    override fun getAllUsers(): Flow<Resource<List<UserDto>>> = flow {
        emit(Resource.Loading())
        val token = preferencesManager.getData("jwt", "") ?: return@flow emit(Resource.Error("Unauthorized"))
        val remoteData = api.getAllUsers("Bearer $token")
        emit(Resource.Success(data = remoteData))
    }
}
