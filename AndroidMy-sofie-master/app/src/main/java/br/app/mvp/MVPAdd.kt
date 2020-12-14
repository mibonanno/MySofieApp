package br.appb.mvp

import br.app.entidade.Registro
import org.json.JSONObject

interface MVPAdd {
    interface Presenter{
        fun salvar(registro: Registro)
        fun sucesso(resposta: String)
        fun erro()
    }
    interface Model{
        fun call(jsonObject: JSONObject)
        fun sucesso(resposta: String)
        fun erro()
    }

    interface View{
        fun sucesso()
        fun erro()
    }
}