package com.example.proyecto_inti_electrodomesticos

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
            else -> Toast.makeText(this,"Seleccione una opcion valida",Toast.LENGTH_LONG).show()
        }
        drawer.closeDrawer(GravityCompat.START)

        return true
    }
}




