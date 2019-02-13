package lt.petraslabutis.testio

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lt.petraslabutis.testio.fragments.LoginFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigateToLogin()
    }

    private fun navigateToLogin() {
        supportFragmentManager.beginTransaction().add(R.id.mainActivity, LoginFragment()).commit()

    }

}
