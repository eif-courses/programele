package eif.viko.lt.appsas.bean.programele.presentation.group_posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eif.viko.lt.appsas.bean.programele.domain.models.group_posts.GroupPost
import eif.viko.lt.appsas.bean.programele.domain.models.group_posts.GroupPostJoin
import eif.viko.lt.appsas.bean.programele.domain.repositories.GroupPostsRepository
import eif.viko.lt.appsas.bean.programele.domain.utils.ServiceLocator
import eif.viko.lt.appsas.bean.programele.presentation.auth.UserAuthUiEvent
import eif.viko.lt.faculty.app.domain.util.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupPostsViewModel @Inject constructor(
    private val repository: GroupPostsRepository
): ViewModel(){

    var state by mutableStateOf(GroupPostState())
        private set

    init {
        if(ServiceLocator.preferencesManager.getData("jwt", "") != "") {
            getGroups()
            //createPost()
            joinToGroup()
            //leaveGroup()
        }
    }


    private fun createPost() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.createNewGroupPost(GroupPost(2, 1,"Labas as naujas postas"))
            //resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }

    private fun joinToGroup(groupId: Int = 2, userId: Int = 1) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.joinToGroup(GroupPostJoin(groupId, userId))
            state = state.copy(isLoading = false)
        }
    }
    private fun leaveGroup(groupId: Int = 2, userId: Int = 1) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            repository.leaveGroup(groupId, userId)
            state = state.copy(isLoading = false)
        }
    }



    fun onEvent(event: GroupPostsUiEvent) {
        when (event) {
            is GroupPostsUiEvent.JoinToGroup -> {
                joinToGroup(event.groupId, event.userId)
            }
            is GroupPostsUiEvent.LeaveGroup -> {
                leaveGroup()
            }
        }
    }







    private fun getGroups() {

        repository.getGroups().onEach { result ->
            state = when (result) {
                is Resource.Success -> {
                    state.copy(
                        groups = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    state.copy(
                        groups = result.data ?: emptyList(),
                        isLoading = true
                    )
                }
                is Resource.Error -> {
                    state.copy(
                        groups = result.data ?: emptyList(),
                        isLoading = false,
                        error = "Error loading data"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }






}