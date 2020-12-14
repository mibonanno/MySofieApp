package br.app.mvp.presenter

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import br.app.entidade.Registro
import br.app.mvp.MVP
import br.app.mvp.MVPAdd
import br.app.mvp.model.ModelAddImpl
import br.app.mvp.model.ModelListaImpl
import org.json.JSONObject
import java.util.ArrayList

class PresenterAddImpl(var view: MVPAdd.View): MVPAdd.Presenter {
    var model : MVPAdd.Model = ModelAddImpl(this)
    override fun salvar(registro: Registro) {
        var json = JSONObject()
            .put("title", registro.title)
            .put("email", registro.email)
            .put("description", registro.description)
            .put("when", registro.`when`)
        model.call(json)
    }

    override fun sucesso(resposta: String) {
        view.sucesso()
    }

    override fun erro() {
        view.erro()
    }


}