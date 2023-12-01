package eif.viko.lt.appsas.bean.programele.domain.repositories

import eif.viko.lt.appsas.bean.programele.data.remote.group_posts.GroupCommentDto
import eif.viko.lt.appsas.bean.programele.data.remote.group_posts.GroupDto
import eif.viko.lt.appsas.bean.programele.data.remote.group_posts.GroupPostDto
import eif.viko.lt.appsas.bean.programele.domain.utils.AuthResult
import eif.viko.lt.faculty.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
interface GroupPostsRepository {

    suspend fun createNewGroupPost(group: GroupPostDto): AuthResult<Unit>
    suspend fun commentGroupPost(text: String): AuthResult<Unit>
    suspend fun joinToGroup(): AuthResult<Unit>
    suspend fun leaveGroup(groupId: Int, userId: Int): AuthResult<Unit>
    suspend fun likeGroupPost(): AuthResult<Unit>
    suspend fun unlikeGroupPost(): AuthResult<Unit>
    suspend fun deleteGroupComment(): AuthResult<Unit>

    fun getGroups(): Flow<Resource<List<GroupDto>>>
    fun getGroupPostsByGroupID(id: Int): Flow<Resource<List<GroupPostDto>>>
    suspend fun getCommentsByPostID(id: Int): Flow<Resource<List<GroupCommentDto>>>

}