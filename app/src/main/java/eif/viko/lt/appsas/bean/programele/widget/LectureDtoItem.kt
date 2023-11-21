package eif.viko.lt.appsas.bean.programele.widget

data class LectureDtoItem(
    val cellOrder: Int = 0,
    val cellSlices: String = "",
    val classids: List<String> = emptyList(),
    val classroomids: List<String> = emptyList(),
    val colors: List<String> = emptyList(),
    val date: String = "",
    val endtime: String = "",
    val groupnames: List<String> = emptyList(),
    val igroupid: String = "",
    val starttime: String = "",
    val subjectid: List<String> = emptyList(),
    val teacherids: List<String> = emptyList(),
    val type: String = "",
    val uniperiod: String = "",
)