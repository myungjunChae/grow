package com.softdough.grow.presentation

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.view.Menu
import com.softdough.grow.R
import com.softdough.grow.databinding.ActivityMainBinding
import com.softdough.grow.presentation.Base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val resourceId: Int = R.layout.activity_main
    override val statusBarColor: Int by bindColor(R.color.colorWhite)

    private val navController by lazy { findNavController(R.id.nav_host_fragment) }

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.appBarMain.toolbar)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_recommend,
                R.id.nav_custom,
                R.id.nav_share,
                R.id.nav_send
            ), binding.drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
