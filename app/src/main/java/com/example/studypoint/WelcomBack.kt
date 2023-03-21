package com.example.studypoint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.studypoint.databinding.ActivityWelcomBackBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WelcomBack : AppCompatActivity() {
    private lateinit var binding : ActivityWelcomBackBinding
    private lateinit var firebaseAuth : FirebaseAuth
    private var fireDatabase = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        binding = ActivityWelcomBackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dropDownMenuContent()


        binding.btnLogin.setOnClickListener{
            userVerification()

        }


    }

    private fun userVerification() {

            val email =  binding.editText.text.toString()
            val password = binding.editText02.text.toString()
            val role12 =  binding .autoCompleteTextView.text.toString()



             if(email.isNotEmpty() && password.isNotEmpty() && role12.isNotEmpty()){

                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if(it.isSuccessful){
                            val userId  = FirebaseAuth.getInstance().currentUser!!.uid
                            val docRef =  fireDatabase.collection(role12).document(userId!!)

                            docRef.get()
                            .addOnSuccessListener {
                                document ->
                                if(document != null ) {
                                    if (document.exists()) {
                                        if(role12 == "Student") {
                                            val intentStudentDash =
                                                Intent(this, student_dash::class.java)
                                            startActivity(intentStudentDash)
                                        }

                                        if(role12 == "Teacher") {
                                            val intentTeacherDash =
                                                Intent(this, TeacherActivity::class.java)
                                            startActivity(intentTeacherDash)
                                        }

                                        if(role12 == "Administrator"){
                                            val intentAdminDash =
                                                Intent(this, AdminDash::class.java)
                                            startActivity(intentAdminDash)
                                        }


                                        Toast.makeText(
                                            this,
                                            "Hey Welcome Student",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    else{
                                        Toast.makeText(this, "Bad Credentials", Toast.LENGTH_SHORT ).show()


                                    }
                                }

                            }
                            .addOnFailureListener { exception ->
                                Toast.makeText(this, "Some Error Occured", Toast.LENGTH_SHORT ).show()


                            }



                        }

                        else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_LONG).show()

                        }
                }
            }




            else{
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT ).show()

            }



        }


    private fun dropDownMenuContent() {
        val roles = resources.getStringArray(R.array.Role)

        // create an array adapter and pass the required parameter
        // in our case pass the context, drop down layout , and array.
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item,roles) //  The Adapter acts as a bridge between the UI Component and the Data Source.
        // It converts data from the data sources into view items that can be displayed into the UI Component.
        // Data Source can be Arrays, HashMap, Database, etc. and UI Components can be ListView, GridView, Spinner, etc

        //ArrayAdapter  -> ArrayAdapter is the most commonly used adapter in android. When you have a list of single type items which are stored in an array you can use ArrayAdapter.
        //   Likewise, if you have a list of phone numbers, names, or cities. ArrayAdapter has a layout with a single TextView.


        // get reference to the autocomplete text view
        val autocompleteTV = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        // set adapter to the autocomplete tv to the arrayAdapter
        autocompleteTV.setAdapter(arrayAdapter) // to associate an adapter with the data items we use adapter.
    }


}
