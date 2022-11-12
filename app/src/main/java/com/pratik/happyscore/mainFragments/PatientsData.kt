package com.pratik.happyscore.mainFragments

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.pratik.happyscore.R

class PatientsData : AppCompatActivity() {

    lateinit var auth:FirebaseAuth
    lateinit var database: FirebaseDatabase
    lateinit var name6: TextView
    lateinit var age6: TextView
    lateinit var email6: TextView
    lateinit var phone6: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patients_data)

        val uri = intent.data
        if (uri != null) {
            val parameters: List<String> = uri.getPathSegments()
            val param = parameters[parameters.size - 1]
            //Toast.makeText(this@PatientsData,param.toString(),Toast.LENGTH_SHORT).show()
            auth = FirebaseAuth.getInstance()
            database = FirebaseDatabase.getInstance()
            name6 = findViewById(R.id.name6)
            email6 = findViewById(R.id.email6)

            age6 = findViewById(R.id.age6)
            phone6 = findViewById(R.id.phone6)



            val ref = database.getReference("Users").child(param.toString())
            ref.addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    name6.text = snapshot.child("name").value.toString()
                    email6.text = snapshot.child("email").value.toString()
                    age6.text = snapshot.child("age").value.toString()
                    phone6.text = snapshot.child("phone").value.toString()

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })
        }
    }
}