package eif.viko.lt.appsas.bean.programele.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import eif.viko.lt.appsas.bean.programele.domain.utils.AuthResult
import eif.viko.lt.appsas.bean.programele.domain.utils.ServiceLocator
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun SignInGoogleScreen(viewModel: UserViewModel = hiltViewModel()) {


    val stateViewModel = viewModel.state

    val context = LocalContext.current

    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    Log.d("LOG", "Authorized")
                }

                is AuthResult.Unauthorized -> {
                    Log.d("LOG", "Unauthorized")
                }

                is AuthResult.UnknownError -> {
                    Log.d("LOG", "UnknownError")
                }
            }
        }
    }

    val state = rememberOneTapSignInState()
    val authenticated = remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    OneTapSignInWithGoogle(
        state = state,
        clientId = "119150766898-2uftt0ne9k8frviilbfd8asvmgok79rn.apps.googleusercontent.com",
        onTokenIdReceived = { tokenId ->
            authenticated.value = true

            Log.d("LOG", tokenId)
            coroutineScope.launch {
                viewModel.onEvent(UserAuthUiEvent.OneTapSignInToken(tokenId))
                //AuthRepositoryImpl().getAccessToken(GoogleTokenId(token = tokenId))
            }

        },
        onDialogDismissed = { message ->
            Log.d("LOG", message)
        }
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { state.open() },
            enabled = !state.opened
        ) {
            Text(text = "Sign in with Google")
        }
        Spacer(modifier = Modifier.height(20.dp))
        if (authenticated.value) {
            Text(text = "Authenticated successfully")
        }
        Spacer(modifier = Modifier.height(20.dp))


        Text(text = "Hello ${ServiceLocator.preferencesManager.getData("access_token", "unknown")}")

//        Spacer(modifier = Modifier.height(20.dp))

    }
}