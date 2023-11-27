package eif.viko.lt.appsas.bean.programele.data

import eif.viko.lt.appsas.bean.programele.domain.models.AccessTokenFromServer
import eif.viko.lt.appsas.bean.programele.domain.models.AuthenticationStatus
import eif.viko.lt.appsas.bean.programele.domain.models.GoogleTokenId
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface PersonalBackendApi {
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

    // TODO Pabaigti pildyti sias funkcijas

    @GET("api/groups")
    suspend fun getGroups(@Header("Authorization") token: String): List<GroupDto>

    @POST("api/groups")
    suspend fun createNewGroupPost(@Header("Authorization") token: String): GroupPostDto

    @GET("api/groups")
    suspend fun getGroupPostsByGroupID(@Header("Authorization") token: String): List<GroupPostDto>


    @POST("api/groups")
    suspend fun joinToGroup(@Header("Authorization") token: String): GroupDto
    @DELETE("api/groups")
    suspend fun leaveGroup(@Header("Authorization") token: String): GroupDto
    @POST("api/groups")
    suspend fun likeGroupPost(@Header("Authorization") token: String): GroupPostDto
    @DELETE("api/groups")
    suspend fun unlikeGroupPost(@Header("Authorization") token: String): GroupPostDto
    @GET("api/groups")
    suspend fun getCommentsByPostID(@Header("Authorization") token: String): List<GroupCommentDto>
    @POST("api/groups")
    suspend fun commentGroupPost(@Header("Authorization") token: String): GroupCommentDto
    @DELETE("api/groups")
    suspend fun deleteGroupComment(@Header("Authorization") token: String): GroupCommentDto









//    groups := router.PathPrefix("/api/groups").Subrouter()
//
//    groups.Use(AuthMiddleware)
//    groups.HandleFunc("", GetGroups).Methods("GET")
//    groups.HandleFunc("/{group_id}/posts", GetGroupPostsByGroupID).Methods("GET")
//    groups.HandleFunc("/posts", CreateNewGroupPost).Methods("POST")
//    groups.HandleFunc("/members", JoinToGroup).Methods("POST")
//    groups.HandleFunc("/likes", LikeGroupPost).Methods("POST")
//    groups.HandleFunc("/posts/{post_id}/comments", GetCommentsByPostID).Methods("GET")
//    groups.HandleFunc("/comments", CommentGroupPost).Methods("POST")
//    groups.HandleFunc("/comments", DeleteGroupComment).Methods("DELETE")
//    groups.HandleFunc("/{group_id}/users/{user_id}", LeaveGroup).Methods("DELETE")
//    groups.HandleFunc("/likes", UnlikeGroupPost).Methods("DELETE")

    companion object {
        const val BASE_URL = "https://restapijwttemplate-production.up.railway.app/"
    }
}