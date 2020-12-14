package br.vieira.miguel.appbasico.mvp.presenter

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import br.app.entidade.Registro
import br.app.mvp.MVP
import br.app.mvp.model.ModelListaImpl
import org.json.JSONObject
import java.util.ArrayList

class PresenterListaImpl(var view: MVP.View, var listaNews: ArrayList<Registro>): MVP.Presenter {
    var model : MVP.Model = ModelListaImpl(this)

    override fun carregarListaInicial() {
        model.call()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun sucesso(jsonString: String) {
        var jo = JSONObject(jsonString)
        var jsonArray = jo.getJSONArray("data")
        //var jsonArray = JSONArray()
        for (jsonIndex in 0..(jsonArray.length() - 1)) {
            var joo = jsonArray.getJSONObject(jsonIndex)
            Log.e("JSON", jsonArray.getJSONObject(jsonIndex).getString("title"))


            listaNews.add(
                Registro(joo.getString("_id"),
                joo.getString("description"),
                joo.getString("email"),
                joo.getString("title"),
                joo.getString("when")
            ))
        }
        view.atualizaUi()
    }

    override fun erro() {
        view.erro()
    }
}