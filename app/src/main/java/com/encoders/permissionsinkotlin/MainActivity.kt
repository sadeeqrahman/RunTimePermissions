package com.encoders.permissionsinkotlin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    private val REQUEST_CODE_ASK_PERMISSIONS = 123
    private lateinit var view_layout: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view_layout= findViewById(R.id.view_layout)

        permissionProcess(view_layout)
    }

    fun myMethod(){
        Toast.makeText(this,"Access Granted",Toast.LENGTH_SHORT).show()
    }

    fun permissionProcess(v: View) {
        if (Build.VERSION.SDK_INT < 23) {
            // do your stuff
            myMethod()
        } else {
            val hasReadContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            if (hasReadContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_CODE_ASK_PERMISSIONS)
                return
            }
            // do your stuff
            myMethod()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_CODE_ASK_PERMISSIONS -> if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted, do your stuff
                myMethod()
            } else {
                // Permission Denied
                Toast.makeText(this@MainActivity, "READ_CALL_LOG Denied", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}