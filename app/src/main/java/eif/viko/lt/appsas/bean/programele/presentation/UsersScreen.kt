package eif.viko.lt.appsas.bean.programele.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UsersScreen (viewModel: UserViewModel = hiltViewModel()){

    val state = viewModel.state.users
    Column {
        state.forEach { user ->
            Text(text = user.email)
        }
    }
}