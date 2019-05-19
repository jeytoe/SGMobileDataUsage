package com.tuanna.sgmobiledatausage.network

import io.reactivex.Observable
import retrofit2.http.GET


interface MobileUsageAPI {
    @get:GET("datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f")
    val getMobileDataUsageData: Observable<MobileDataUsageResponse>
}