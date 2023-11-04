package eif.viko.lt.faculty.app.domain.util

//import android.content.SharedPreferences
//import okhttp3.Interceptor
//import javax.inject.Inject
//
//class OAuthInterceptor @Inject constructor(private val prefs: SharedPreferences):
//    Interceptor {
//    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
//        var request = chain.request()
//
//        val acceessToken = prefs.getString("jwt", null) ?: ""
//        val tokenType = "Bearer"
//
//        request = request.newBuilder().header("Authorization", "$tokenType $acceessToken").build()
//        return chain.proceed(request)
//    }
//}