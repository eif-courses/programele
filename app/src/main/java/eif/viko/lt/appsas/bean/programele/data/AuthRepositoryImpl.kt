package eif.viko.lt.appsas.bean.programele.data

import eif.viko.lt.appsas.bean.programele.domain.models.GoogleTokenId
import eif.viko.lt.appsas.bean.programele.domain.repositories.AuthRepository
import eif.viko.lt.appsas.bean.programele.domain.utils.AuthResult
import eif.viko.lt.appsas.bean.programele.domain.utils.ServiceLocator
import eif.viko.lt.faculty.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val googleSignInService: GoogleSignInService
) : AuthRepository {
    override suspend fun getAccessToken(token: GoogleTokenId): AuthResult<Unit> {


        return try {

            val response = googleSignInService.getAccessToken(token)
            ServiceLocator.preferencesManager.saveData("access_token", response.access_token)
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

    override fun authenticate(): Flow<Resource<List<User>>> = flow {


        emit(Resource.Loading())
        val token = ServiceLocator.preferencesManager.getData("access_token", "")
        val remoteData = googleSignInService.authenticate("Bearer $token")
        emit(Resource.Success(data = remoteData))

    }

//        return try {
//            // TODO IMPLEMENT CHECKING IF TOKEN IS VALID
//
//            val token = ServiceLocator.preferencesManager.getData("access_token", "")
//            if (token == "") {
//                return AuthResult.Unauthorized()
//            }
//            RetrofitInstance.api.authenticate("Bearer $token")
//
//
////            val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
////            api.authenticate("Bearer $token")
//            AuthResult.Authorized()
//        } catch (e: HttpException) {
//            if (e.code() == 401) {
//                AuthResult.Unauthorized()
//            } else {
//                e.printStackTrace()
//                AuthResult.UnknownError()
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            AuthResult.UnknownError()
//        }
//    }


}
