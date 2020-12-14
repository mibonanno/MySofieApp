package br.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.vuxx.appnoticias.adapter.ListaAdapter
import br.app.R
import br.app.entidade.Registro
import br.app.mvp.MVP
import br.app.mvp.presenter.PresenterListaImpl
import java.lang.Thread.sleep
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), MVP.View, ListaAdapter.OnClicked{

    private var rv: RecyclerView? = null
    private var adapter: ListaAdapter? = null
    var listaNews: ArrayList<Registro>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_first, container, false)


        rv = rootView?.findViewById<View>(R.id.rv) as RecyclerView
        listaNews = ArrayList()
        //rv!!.setHasFixedSize(true)

        val linearLyaoutManager = LinearLayoutManager(activity)
        rv!!.layoutManager = linearLyaoutManager

        adapter = context?.let { ListaAdapter(it, listaNews!!, this) }
        rv?.adapter = adapter



        Thread(Runnable {
            sleep(2000)
            var presenter : MVP.Presenter = PresenterListaImpl(this, listaNews!!)
            presenter.carregarListaInicial()
        }).start()



        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        */


    }

    override fun atualizaUi() {
        Thread(Runnable {
            adapter!!.notifyDataSetChanged()
        }).start()
       // adapter?.notifyDataSetChanged()
        /*
        adapter.let { x ->
            if (x != null) {
                x.notifyDataSetChanged()
            }
        }
        */
    }

    override fun erro() {

    }

    override fun getItemBotaoClicked(posicao: Int, item: Any?) {

    }

    override fun getItemListaClicked(posicao: Int, item: Any?) {

    }

}
