package br.vieira.miguel.appbasico.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import br.app.R
import br.app.entidade.Registro
import br.app.mvp.MVPAdd
import br.app.mvp.presenter.PresenterAddImpl

class AddActivity : AppCompatActivity(), MVPAdd.View {

    private var presenter: MVPAdd.Presenter? = null
    private var edtEmail:EditText? = null
    private var edtTarefa:EditText? = null
    private var edtDesc:EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        presenter  = PresenterAddImpl(this)
        edtEmail = findViewById(R.id.edtEmail)
        edtTarefa = findViewById(R.id.edtTarefa)
        edtDesc = findViewById(R.id.edtDes)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.limpar -> {
            // User chose the "Settings" item, show the app settings UI...
            true
        }

        R.id.salvar -> {
            // User chose the "Favorite" action, mark the current item
            // as a favorite...
            presenter?.salvar(enviarDados())
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }

    fun enviarDados(): Registro {

        var email = edtEmail?.text.toString()
        var titulo = edtTarefa?.text.toString()
        var descr = edtDesc?.text.toString()
        return Registro("", descr, email, titulo, "")
    }

    override fun sucesso() {

    }
    override fun erro() {

    }
}
