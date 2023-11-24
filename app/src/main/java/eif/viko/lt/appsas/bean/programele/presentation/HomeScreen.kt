package eif.viko.lt.appsas.bean.programele.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController


@Composable
fun HomeScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {

    val state = viewModel.state

    LazyColumn {
        items(state.products.size) { index ->

            Column(modifier =
            Modifier
                .fillMaxWidth()
                .background(color = Color.Cyan)) {
                Text(text = state.products[index].name)
                Spacer(modifier = Modifier.height(10.dp))
            }

        }
    }


    println("DUOMENYS IS INTIKO: " + state.products)


}