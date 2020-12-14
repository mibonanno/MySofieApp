package br.app.mvp.model

import br.app.api.Repository
import br.app.mvp.MVP

class ModelListaImpl(var presenter : MVP.Presenter) : MVP.Model{
    var repository  = Repository(this)
    override fun call() {
        repository.run()
    }

    override fun sucesso(resposta: String) {
        presenter.sucesso(resposta)
    }

    override fun erro() {
        presenter.erro()
    }
}