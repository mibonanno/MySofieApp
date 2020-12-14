package br.app.mvp.model

import br.app.mvp.MVPAdd
import okhttp3.*
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class ModelAddImpl(var presenter : MVPAdd.Presenter) : MVPAdd.Model{
    private val client = OkHttpClient()

    override fun call(jsonObject: JSONObject) {
       runPost(jsonObject)
    }

    override fun sucesso(resposta: String) {

    }

    override fun erro() {
        presenter.erro()
    }



    fun runPost(jsonObject: JSONObject) {

        val requestBody = jsonObject.toString().toRequestBody()
        val request = Request.Builder()
            .url("https://9g1borgfz0.execute-api.sa-east-1.amazonaws.com/beta/task")
            .method("POST", requestBody)
            .addHeader("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                presenter.erro()
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val body = response.body!!.string()
                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }
                    print(body)
                    presenter.sucesso(body)
                }
            }
        })
    }
}