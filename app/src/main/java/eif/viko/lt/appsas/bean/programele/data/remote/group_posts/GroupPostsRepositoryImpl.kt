package eif.viko.lt.appsas.bean.programele.data.remote.group_posts

import eif.viko.lt.appsas.bean.programele.domain.repositories.GroupPostsRepository
import eif.viko.lt.appsas.bean.programele.domain.utils.AuthResult
import eif.viko.lt.faculty.app.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GroupPostsRepositoryImpl @Inject constructor(
    private val groupPostsApi: GroupPostsApi
) : GroupPostsRepository {
    override suspend fun createNewGroupPost(group: GroupPostDto): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun commentGroupPost(text: String): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun joinToGroup(): AuthResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun leaveGroup(groupId: Int, userId: Int): AuthResult<Unit> {
        TODO("Not yet implemented")
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

    override fun getGroupPostsByGroupID(id: Int): Flow<Resource<List<GroupPostDto>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCommentsByPostID(id: Int): Flow<Resource<List<GroupCommentDto>>> {
        TODO("Not yet implemented")
    }

}