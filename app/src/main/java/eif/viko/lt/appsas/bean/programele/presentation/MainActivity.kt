package eif.viko.lt.appsas.bean.programele.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import dagger.hilt.android.AndroidEntryPoint
import eif.viko.lt.appsas.bean.programele.domain.utils.PreferencesManager
import eif.viko.lt.appsas.bean.programele.domain.utils.ServiceLocator
import eif.viko.lt.appsas.bean.programele.ui.theme.ProgrameleTheme
import kotlinx.coroutines.launch
import javax.inject.Inject

// https://www.youtube.com/watch?v=QpaTcx2nnHM
@AndroidEntryPoint
class MainActivity @Inject constructor(
) : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ServiceLocator.preferencesManager = PreferencesManager(applicationContext)

        setContent {
            ProgrameleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignInGoogleScreen()
                }
            }
        }
    }
}


@Composable
fun Greeting(){


    //UsersScreen()

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

        Spacer(modifier = Modifier.height(20.dp))

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProgrameleTheme {
        //Greeting()
    }
}