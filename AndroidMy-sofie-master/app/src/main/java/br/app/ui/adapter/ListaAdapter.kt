package br.com.vuxx.appnoticias.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.app.R
import br.app.entidade.Registro

class ListaAdapter   (
    var c: Context,
    var listaNoticias: List<Registro>,
    var onClicked: OnClicked
) :
    RecyclerView.Adapter<ListaAdapter.RecyclerVH>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerVH {
        val v: View =
            LayoutInflater.from(c).inflate(R.layout.item, parent, false)
        return RecyclerVH(v)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(
        holder: RecyclerVH,
        posicao: Int
    ) {
        val item = listaNoticias[posicao]
        holder.txtCampo1.text = item.title
        holder.txtCampo2.text = item.email
        /*
        var aux : String = ""
        if (item.content!!.length > LIMITE)
            aux = item.content!!.substring(0,LIMITE) + "..."
        else
            aux = item.content!!

        holder.txtCampo2.text = aux
        holder.txtCampo3.text = item.publishedAt;
        if(item.status == News.LIDA)
            holder.btnMarcar.setBackgroundColor(R.color.amarelo)
        Picasso.get().load(item.urlToImage).resize(50, 50)
            .centerCrop().into(holder.imageView)
        */

    }

    override fun getItemCount(): Int {
        return listaNoticias.size
    }

    inner class RecyclerVH(itemVw: View) : RecyclerView.ViewHolder(itemVw),
        View.OnClickListener {
        var txtCampo1: TextView
        var txtCampo2: TextView
        /*
        var txtCampo3: TextView
        var imageView: ImageView
        var btnMarcar : Button
        */
        override fun onClick(v: View) {
            onClicked.getItemListaClicked(layoutPosition, Any())
        }

        init {
            txtCampo1 = itemVw.findViewById<View>(R.id.txt1) as TextView
            txtCampo2 = itemVw.findViewById<View>(R.id.txt2) as TextView
            /*txtCampo3 = itemVw.findViewById<View>(R.id.campo3) as TextView
            btnMarcar = itemVw.findViewById<View>(R.id.btnMarca) as Button
            imageView =
                itemVw.findViewById<View>(R.id.person_photo) as ImageView

            btnMarcar.setOnClickListener( View.OnClickListener { onClicked.getItemBotaoClicked(layoutPosition, Any()) })
            */
            itemView.setOnClickListener(this)
        }
    }

    interface OnClicked {
        fun getItemBotaoClicked(posicao: Int, item: Any?)
        fun getItemListaClicked(posicao: Int, item: Any?)
    }
}