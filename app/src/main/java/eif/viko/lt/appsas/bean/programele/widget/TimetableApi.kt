package eif.viko.lt.appsas.bean.programele.widget

import retrofit2.http.GET
import retrofit2.http.Query

interface TimetableApi {
    @GET("api/v1/timetable/group")
    suspend fun getLectures(@Query("group_id") id: String): List<LectureDtoItem>

    @GET("api/v1/timetable/groups")
    suspend fun getGroups(): List<GroupDtoItem>


    companion object{
        const val BASE_URL = "https://eifapistimetable-production.up.railway.app/"
    }
}