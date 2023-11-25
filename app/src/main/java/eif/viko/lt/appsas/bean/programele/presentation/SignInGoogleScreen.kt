package eif.viko.lt.appsas.bean.programele.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.getUserFromTokenId
import com.stevdzasan.onetap.rememberOneTapSignInState
import eif.viko.lt.appsas.bean.programele.domain.utils.AuthResult
import eif.viko.lt.appsas.bean.programele.domain.utils.ServiceLocator
import eif.viko.lt.appsas.bean.programele.domain.utils.Route
import kotlinx.coroutines.launch

@Composable
fun SignInGoogleScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {

    val context = LocalContext.current

    val stateViewModel = viewModel.state

    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is AuthResult.Authorized -> {
                    navController.navigate(Route.HOME_SCREEN)
                }

                is AuthResult.Unauthorized -> {
                    Toast.makeText(
                        context,
                        "You're not authorized",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is AuthResult.UnknownError -> {
                    Toast.makeText(
                        context,
                        "An unknown error occurred",
                        Toast.LENGTH_LONG
                    ).show()
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
        rememberAccount = true,
        onTokenIdReceived = { tokenId ->
            Log.d("LOG", getUserFromTokenId(tokenId).toString())
            authenticated.value = true
            viewModel.state.tokenas = tokenId
            coroutineScope.launch {
                viewModel.onEvent(UserAuthUiEvent.OneTapSignIn)
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


        Text(text = ServiceLocator.preferencesManager.getData("jwt", "unknown"))

        if (stateViewModel.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}