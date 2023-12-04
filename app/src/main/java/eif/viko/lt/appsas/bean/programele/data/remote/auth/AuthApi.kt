package eif.viko.lt.appsas.bean.programele.data.remote.auth

import eif.viko.lt.appsas.bean.programele.domain.models.AccessTokenFromServer
import eif.viko.lt.appsas.bean.programele.domain.models.AuthenticationStatus
import eif.viko.lt.appsas.bean.programele.domain.models.GoogleTokenId
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface AuthApi {
    // Siunčiame google tokenid, kad serveris išduotu JWT tokeną skirtą duomenų gavimui iš autorizuotų rest api endpointų
    @POST("api/verify")
    suspend fun getAccessToken(@Body token: GoogleTokenId): AccessTokenFromServer



    // PRIEŽĄSTIS KODĖL REIKIA OAuth2 Interceptor klasės, kad nekartoti kiekvienoje funkcijoje @Header("Authorization") token: String parametro
    // Ir tai numatyti su kiekvienos funkcijos kvietimu jeigu naudojame retrofit2 biblioteką


    // Tikriname ar esame autorizuoti prie rest api endpointų jeigu ne tada prašome prisijungti iš naujo su google paskyra
    @GET("api/me")
    suspend fun me(@Header("Authorization") token: String): AuthenticationStatus

    // Duomenų gavimas iš rest api endpointų apie vartotojus
    @GET("api/users")
    suspend fun getAllUsers(@Header("Authorization") token: String): List<UserDto>

    // Duomenų gavimas iš rest api endpointų apie produktus
    @GET("api/products")
    suspend fun getProducts(@Header("Authorization") token: String): List<ProductDto>

    // Post ir Grupės kūrimas, komentavimas ir like'inimas

    companion object {
        const val BASE_URL = "https://restapijwttemplate-production.up.railway.app/"
    }
}