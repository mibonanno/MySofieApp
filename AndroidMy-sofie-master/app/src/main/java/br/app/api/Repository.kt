package br.app.api

import android.R.string
import android.util.Log
import br.app.mvp.MVP
import okhttp3.*
import java.io.IOException


class Repository(var model: MVP.Model) {
    val TAG = "Repository"
    private val client = OkHttpClient()

    fun run() {
        val request = Request.Builder()
            .url("https://9g1borgfz0.execute-api.sa-east-1.amazonaws.com/beta/task?email=aureliomiguelgama@gmail.com")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                model.erro()
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val body = response.body!!.string()


                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }

                    Log.e(TAG, body)
                    /*

                    object : Thread() {
                        override fun run() {
                            //handler.post(Runnable { result_view.setText(string) })
                            handler
                            model.sucesso(body)
                        }
                    }.start()
                    */

                    model.sucesso(body)

                }
            }
        })
    }
}