package com.darkaxce.medayork


import android.content.Context
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class VolleyPoi private constructor(context: Context) {
    private val mRequestQueue: RequestQueue
    val requestQueue: RequestQueue
        get() = mRequestQueue

    companion object {
        private var mVolleyRP: VolleyPoi? = null
        fun getInstance(context: Context): VolleyPoi? {
            if (mVolleyRP == null) {
                mVolleyRP = VolleyPoi(context)
            }
            return mVolleyRP
        }

        fun addToQueue(
            request: Request<Volley>?,
            fRequestQueue: RequestQueue?,
            context: Context?,
            volley: VolleyPoi
        ) {
            var fRequestQueue: RequestQueue? = fRequestQueue
            if (request != null) {
                request.setTag(context)
                if (fRequestQueue == null) {
                    fRequestQueue = volley.requestQueue
                }
                request.setRetryPolicy(DefaultRetryPolicy(60000, 3, 1.0f))
                fRequestQueue.add(request)
            }
        }
    }

    init {
        mRequestQueue = Volley.newRequestQueue(context)
    }
}


