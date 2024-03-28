package com.admin.medease

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import com.admin.medease.activities.LoginActivity
import com.admin.medease.databinding.ActivityDoctorLoginBinding
import com.admin.medease.databinding.ActivityPanelBinding
import com.admin.medease.fragment.DoctorLogin

class PanelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPanelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPanelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdminLogin.setOnClickListener {
            var intent=Intent(this,LoginActivity::class.java)
          startActivity(intent)
        }
        binding.btnDoctorLogin.setOnClickListener {
            var intent=Intent(this,DoctorLogin::class.java)
            startActivity(intent)

        }
    }

}