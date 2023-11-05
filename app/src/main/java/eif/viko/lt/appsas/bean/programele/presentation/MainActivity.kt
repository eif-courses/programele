package eif.viko.lt.appsas.bean.programele.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import eif.viko.lt.appsas.bean.programele.domain.utils.PreferencesManager
import eif.viko.lt.appsas.bean.programele.domain.utils.ServiceLocator
import eif.viko.lt.appsas.bean.programele.ui.theme.ProgrameleTheme

// https://www.youtube.com/watch?v=QpaTcx2nnHM
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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