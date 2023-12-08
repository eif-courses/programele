package eif.viko.lt.appsas.bean.programele.data.remote.group_posts

import eif.viko.lt.appsas.bean.programele.domain.models.group_posts.GroupPost
import eif.viko.lt.appsas.bean.programele.domain.models.group_posts.GroupPostJoin
import eif.viko.lt.appsas.bean.programele.domain.repositories.GroupPostsRepository
import eif.viko.lt.appsas.bean.programele.domain.utils.AuthResult
import eif.viko.lt.faculty.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GroupPostsRepositoryImpl @Inject constructor(
    private val groupPostsApi: GroupPostsApi
) : GroupPostsRepository {
    override suspend fun createNewGroupPost(group: GroupPost): AuthResult<Unit> {
        return try {
            groupPostsApi.createNewGroupPost(group)
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

    override suspend fun commentGroupPost(text: String): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun joinToGroup(join: GroupPostJoin): AuthResult<Unit> {
        return try {
            groupPostsApi.joinToGroup(join)
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

    override suspend fun leaveGroup(groupId: Int, userId: Int): AuthResult<Unit> {
        return try {
            groupPostsApi.leaveGroup(groupId, userId)
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

    override suspend fun likeGroupPost(): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun unlikeGroupPost(): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteGroupComment(): AuthResult<Unit> {
        TODO("Not yet implemented")
    }



    override fun getGroups(): Flow<Resource<List<GroupDto>>> = flow {
        emit(Resource.Loading())
        val remoteData = groupPostsApi.getGroups()
        emit(Resource.Success(data = remoteData))
    }

    override fun getGroupPostsByGroupID(id: Int): Flow<Resource<List<GroupPost>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCommentsByPostID(id: Int): Flow<Resource<List<GroupCommentDto>>> {
        TODO("Not yet implemented")
    }

}