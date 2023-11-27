package eif.viko.lt.appsas.bean.programele.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import eif.viko.lt.appsas.bean.programele.data.ProductDto




@Composable
fun MyCard(
    data: ProductDto,
) {

    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {

                Toast
                    .makeText(context, "pav: ${data.name}", Toast.LENGTH_SHORT)
                    .show()

            }
    ) {
        Text(
            text = data.name,
            modifier = Modifier.padding(all = 8.dp)
        )
    }
}

@Composable
fun HomeScreen(navController: NavController, viewModel: AuthViewModel = hiltViewModel()) {

    val state = viewModel.state

    LazyColumn {
        items(state.products.size) { index ->

                Text(text = " Quantity: ${state.products[index].quantity}")
                MyCard(data = state.products[index])

//            Column(modifier =
//            Modifier
//                .fillMaxWidth()
//                .background(color = Color.Cyan)) {
//                Text(text = state.products[index].name)
//                Spacer(modifier = Modifier.height(10.dp))
//            }
        }
    }









    println("DUOMENYS IS INTIKO: " + state.products)


}