package com.example.proyecto_inti_electrodomesticos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView

class AppBarActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_bar)

        initialize()

    }

    private fun initialize(){
        val toolbar: MaterialToolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.main_drawer)
        val drawerToggle = ActionBarDrawerToggle(this,drawer,toolbar,R.string.open_drawer,R.string.close_drawer)

        drawer.addDrawerListener(drawerToggle)

        drawerToggle.syncState()

        val navView: NavigationView = findViewById(R.id.navigationView)
        navView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item_mi_perfil -> openUserActivity()
            R.id.item_disponible_productos -> openProductosActivity()
            R.id.item_mis_pedidos -> openMisPedidosActivity()
            else -> Toast.makeText(this,"Seleccione una opcion valida",Toast.LENGTH_LONG).show()
        }
        drawer.closeDrawer(GravityCompat.START)

        return true
    }
    private fun openProductosActivity(){

        val intent= Intent(this,MisProductosActivity::class.java)
        startActivity(intent)
    }

    private fun openUserActivity() {
        val usuarioId = intent.getIntExtra("usuarioId", -1)
        if (usuarioId != -1) {
            val intent = Intent(this, ActivityUser::class.java).apply {
                putExtra("usuarioId", usuarioId)
            }
            startActivity(intent)
        } else {
            Toast.makeText(this, "ID de usuario no válido", Toast.LENGTH_LONG).show()
        }
    }

    private fun openMisPedidosActivity(){
        val intent = Intent(this,MisPedidos::class.java)
        startActivity(intent)
    }
}




