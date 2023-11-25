package eif.viko.lt.appsas.bean.programele.widget

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.action.ActionParameters
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.lazy.LazyColumn
import androidx.glance.appwidget.lazy.itemsIndexed
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.layout.wrapContentWidth
import androidx.glance.text.Text
import androidx.glance.text.TextAlign
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object TimetableWidget : GlanceAppWidget() {

    val countKey = stringPreferencesKey("count")

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun Content(context: Context) {
        val count = currentState(key = countKey) ?: "nera paskaitų"

        var studentName by remember {
            mutableStateOf("Pasirinkite studentą")
        }
        val gson = Gson()

        Column(
            modifier = GlanceModifier.fillMaxSize()
                .background(Color(0, 81, 255, 0xBB)).padding(top = 10.dp),
        ) {


            Row {

                Spacer(modifier = GlanceModifier.width(10.dp)) // Add some spacing between the button and LazyColumn

                Button(
                    text = "Naujinti",
                    onClick = actionRunCallback(IncrementActionCallback::class.java),
                    modifier = GlanceModifier.padding(7.dp).wrapContentWidth()
                )
                Spacer(modifier = GlanceModifier.width(5.dp)) // Add some spacing between the button and LazyColumn

                Text(
                    text = studentName,
                    GlanceModifier.padding(start = 30.dp, top = 10.dp).clickable {
                        openMainActivity(context = context)
                    },
                    style = TextStyle(
                        textAlign = TextAlign.Center,
                        color = ColorProvider(Color(236, 247, 255, 0xFF))
                    )
                )
            }


            // ATKREIPTI DĖMESĮ NAUDOTI TIK GLANCE KOMPONENTUS nes kitu atveju neveiks.
            if (count != "nera paskaitų") {
                val lecturesDtos = gson.fromJson(count, Array<LectureDtoItem>::class.java)
                val lectures = lecturesDtos.toList()

                LazyColumn{
                    itemsIndexed(lectures) { index, item ->
                        Row {
                            Text(
                                text = item.subjectid[0] + " " + item.date,
                                style = TextStyle(color = ColorProvider(Color(236, 247, 255, 0xFF)))
                            )
                            Spacer(modifier = GlanceModifier.height(10.dp))
                            Button(text = item.uniperiod, onClick = {})
                        }
                    }
                }
            }

        }
    }

    fun openMainActivity(context: Context) {
        val intent = Intent(context, StudentPickerActivity::class.java)
        intent.flags = FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            Content(context)
        }
    }
}


class TimetableMyReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = TimetableWidget
}


class IncrementActionCallback : ActionCallback {

    override suspend fun onAction(
        context: Context,
        glanceId: GlanceId,
        parameters: ActionParameters
    ) {

        val api = Retrofit.Builder()
            .baseUrl(TimetableApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = api.create(TimetableApi::class.java)

        val id = "-788"
        val request = service.getLectures(id.toString())


        val gson = Gson()
        val json = gson.toJson(request)

        updateAppWidgetState(context, glanceId) { prefs ->
            val currentCount = prefs[TimetableWidget.countKey]

            if (currentCount != null) {
                prefs[TimetableWidget.countKey] = json
            } else {
                prefs[TimetableWidget.countKey] = json
            }
        }
        TimetableWidget.update(context, glanceId)
    }
}





