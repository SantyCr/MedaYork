package com.darkaxce.medayork

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


/**
 * Created by rcarrascal .
 */
class VolleySingleton private constructor(context: Context) {
    private var requestQueue: RequestQueue?

    /**
     * Obtiene la instancia de la cola de peticiones
     * @return cola de peticiones
     */
    fun getRequestQueue(): RequestQueue {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context!!.applicationContext)
        }
        return requestQueue!!
    }

    /**
     * Añade la petición a la cola
     * @param req petición
     * @param <T> Resultado final de tipo T
    </T> */
    fun <T> addToRequestQueue(req: Request<T>?) {
        getRequestQueue().add(req)
    }

    companion object {
        // Atributos
        private var singleton: VolleySingleton? = null
        private var context: Context? = null

        /**
         * Retorna la instancia unica del singleton
         * @param context contexto donde se ejecutarán las peticiones
         * @return Instancia
         */
        @Synchronized
        fun getInstance(context: Context): VolleySingleton? {
            if (singleton == null) {
                singleton = VolleySingleton(context.applicationContext)
            }
            return singleton
        }
    }

    init {
        Companion.context = context
        requestQueue = getRequestQueue()
    }
}
