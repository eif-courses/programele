package eif.viko.lt.appsas.bean.programele.data.remote.group_posts

import eif.viko.lt.appsas.bean.programele.domain.models.group_posts.GroupPost
import eif.viko.lt.appsas.bean.programele.domain.models.group_posts.GroupPostJoin
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface GroupPostsApi {
    // TODO Pabaigti pildyti sias funkcijas

    @GET("api/groups")
    suspend fun getGroups(): List<GroupDto>

    @POST("api/groups/posts")
    suspend fun createNewGroupPost(@Body group: GroupPost)

    @GET("api/groups/{group_id}/posts")
    suspend fun getGroupPostsByGroupID(@Path("group_id") groupId: Int): List<GroupPost>

    @POST("api/groups/members")
    suspend fun joinToGroup(@Body join: GroupPostJoin)
    @DELETE("api/groups/{group_id}/users/{user_id}")
    suspend fun leaveGroup(@Path("group_id") group_id: Int,
                           @Path("user_id") user_id: Int)
    @POST("api/groups/likes")
    suspend fun likeGroupPost(): GroupPost
    @DELETE("api/groups/likes")
    suspend fun unlikeGroupPost(): GroupPost
    @GET("api/groups/posts/{post_id}/comments")
    suspend fun getCommentsByPostID(): List<GroupCommentDto>
    @POST("api/groups/comments")
    suspend fun commentGroupPost(): GroupCommentDto
    @DELETE("api/groups/comments")
    suspend fun deleteGroupComment(): GroupCommentDto

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