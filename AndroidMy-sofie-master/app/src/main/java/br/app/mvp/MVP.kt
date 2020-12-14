package br.app.mvp

interface MVP {
    interface Presenter{
        fun carregarListaInicial()
        fun sucesso(resposta: String)
        fun erro()
    }
    interface Model{
        fun call()
        fun sucesso(resposta: String)
        fun erro()
    }

    interface View{
        fun atualizaUi()
        fun erro()
    }
}