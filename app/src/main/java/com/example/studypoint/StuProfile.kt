package com.example.studypoint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.studypoint.databinding.ActivityStuProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class StuProfile : AppCompatActivity() {

    private lateinit var binding : ActivityStuProfileBinding
    private lateinit var firestoreReference: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStuProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestoreReference = Firebase.firestore
        getUserData()

    }

    private fun getUserData() {
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = firestoreReference.collection("Student").document(userId)


        ref.get().addOnSuccessListener {
            // In success listener we'll get all of our data in document snapshot format
            if(it!=null){

                val name  = it.data?.get("name").toString()
                val standard  = it.data?.get("Student Standard").toString()
                val fatherName  = it.data?.get("Father Name").toString()
                val MotherName  = it.data?.get("MotherName").toString()
                val phoneNumber  = it.data?.get("Student WhatsApp Phone  Number").toString()
                val InstituteName  = it.data?.get("Student School Name ").toString()
                val batch  = it.data?.get("Student Aadhar").toString()

                binding.tvName.text = name
                binding.tvClass.text = standard
                binding.tvFatherName.text = fatherName
                binding.tvMotherName.text = MotherName
                binding.tvInstituteName.text = InstituteName
                binding.tvPhone.text = phoneNumber
                binding.tvBatch.text  = batch

            }


        }.addOnFailureListener{
            Toast.makeText(this,"Failed!!", Toast.LENGTH_SHORT).show()

        }
    }
    }

