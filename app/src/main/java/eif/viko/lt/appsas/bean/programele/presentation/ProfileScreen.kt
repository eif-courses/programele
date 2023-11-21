package eif.viko.lt.appsas.bean.programele.presentation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import eif.viko.lt.appsas.bean.programele.domain.utils.ServiceLocator

@Composable
fun ProfileScreen() {
    Text(text = "Profile screen")
//    Text(text = "Hello ${ServiceLocator.preferencesManager.getData("access_token", "unknown")}")

}