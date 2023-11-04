package eif.viko.lt.appsas.bean.programele

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stevdzasan.onetap.OneTapSignInWithGoogle
import com.stevdzasan.onetap.rememberOneTapSignInState
import eif.viko.lt.appsas.bean.programele.ui.theme.ProgrameleTheme

// https://www.youtube.com/watch?v=QpaTcx2nnHM
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProgrameleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val state = rememberOneTapSignInState()
    val authenticated = remember { mutableStateOf(false) }

    OneTapSignInWithGoogle(
        state = state,
        clientId = "119150766898-2uftt0ne9k8frviilbfd8asvmgok79rn.apps.googleusercontent.com",
        onTokenIdReceived = { tokenId ->
            authenticated.value = true
            Log.d("LOG", tokenId)
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
    }


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ProgrameleTheme {
        Greeting()
    }
}