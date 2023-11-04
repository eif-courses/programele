package eif.viko.lt.appsas.bean.programele.data

import eif.viko.lt.appsas.bean.programele.domain.models.AccessTokenFromServer
import eif.viko.lt.appsas.bean.programele.domain.models.GoogleTokenId
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST







interface GoogleSignInService {
    @POST("api/verify")
    suspend fun getAccessToken(@Body token: GoogleTokenId): AccessTokenFromServer

    @GET("api/users")
    suspend fun authenticate(@Header("Authorization") token: String): List<User>

    companion object {
        const val BASE_URL = "https://restapijwttemplate-production.up.railway.app/"
    }
}