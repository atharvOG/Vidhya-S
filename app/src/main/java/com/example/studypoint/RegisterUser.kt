package com.example.studypoint

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studypoint.databinding.ActivityRegisterUserBinding
import com.example.studypoint.databinding.ActivityStuProfileBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterUser : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterUserBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth =  FirebaseAuth.getInstance()

        binding.btnAuthenticateUser.setOnClickListener {
            val email = binding.editRegisterEmail.text.toString()
            val pass = binding.CreatePassword.text.toString()
            val ConfirmPass = binding.edit12ConfirmPassword.text.toString()
            if(email.isNotEmpty() && pass.isNotEmpty() && ConfirmPass.isNotEmpty()){
                if(pass == ConfirmPass){
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        val userId  = FirebaseAuth.getInstance().currentUser!!.uid
                        Toast.makeText(this, "Student Created Sucessfully", Toast.LENGTH_SHORT ).show()
                        val intent = Intent(this, AddStudent :: class.java)
                       // val userId0 =
                        intent.putExtra("Uid",userId)
                        startActivity(intent)

                        binding.editRegisterEmail.text?.clear()
                        binding.CreatePassword.text?.clear()
                        binding.edit12ConfirmPassword.text?.clear()

                    }
                }
                else{
                    Toast.makeText(this, "Password Don't match", Toast.LENGTH_SHORT ).show()

                }
            }
            else{
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT ).show()

            }
        }
    }
}