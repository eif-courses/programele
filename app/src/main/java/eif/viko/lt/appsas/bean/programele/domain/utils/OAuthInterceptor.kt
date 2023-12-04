package eif.viko.lt.appsas.bean.programele.domain.utils

import okhttp3.Interceptor
import javax.inject.Inject


class OAuthInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()

        val acceessToken = ServiceLocator.preferencesManager.getData("jwt", "")
        val tokenType = "Bearer"

        request = request.newBuilder().header("Authorization", "$tokenType $acceessToken").build()
        return chain.proceed(request)
    }
}