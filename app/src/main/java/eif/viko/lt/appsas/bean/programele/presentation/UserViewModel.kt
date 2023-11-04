package eif.viko.lt.appsas.bean.programele.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eif.viko.lt.appsas.bean.programele.domain.models.GoogleTokenId
import eif.viko.lt.appsas.bean.programele.domain.repositories.AuthRepository
import eif.viko.lt.appsas.bean.programele.domain.utils.AuthResult
import eif.viko.lt.appsas.bean.programele.domain.utils.ServiceLocator
import eif.viko.lt.faculty.app.domain.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn

import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    var state by mutableStateOf(UserState())
        private set


    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()

    init {
        getAllUsers()
    }


    fun onEvent(event: UserAuthUiEvent) {
        when (event) {
            is UserAuthUiEvent.OneTapSignInToken -> {
                state = state.copy(token = event.value)
            }

            is UserAuthUiEvent.OneTapSignIn -> {
                oneTapSignIn()
            }
        }
    }


    private fun oneTapSignIn() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = repository.getAccessToken(
                token = GoogleTokenId(state.token)
            )
            resultChannel.send(result)

            ServiceLocator.preferencesManager.saveData("token","labas")

            state = state.copy(isLoading = false)
        }
    }


    private fun getAllUsers() {

        repository.authenticate().onEach { result ->
            state = when (result) {
                is Resource.Success -> {
                    state.copy(
                        users = result.data ?: emptyList(),
                        // groups = result.data ?: emptyList(),
                        isLoading = false
                    )
                }

                is Resource.Loading -> {
                    state.copy(
                        users = result.data ?: emptyList(),
                        isLoading = true
                    )
                }

                is Resource.Error -> {
                    state.copy(
                        users = result.data ?: emptyList(),
                        isLoading = false,
                        error = "Error loading data"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

}