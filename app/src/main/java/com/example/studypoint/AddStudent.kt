package com.example.studypoint

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import com.example.studypoint.databinding.ActivityAddstudentBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar


class AddStudent : AppCompatActivity() {
    private lateinit var binding : ActivityAddstudentBinding
    private  var database = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addstudent)

        binding = ActivityAddstudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSaveData.setOnClickListener {
            saveData()
        }

        binding.editTextDob.setOnClickListener {
            popUpCalendar()
        }

        binding.autoCompleteGender.setOnClickListener {
            val genders = resources.getStringArray(R.array.Genders)
            dropDownMenuAdapter(genders, binding.autoCompleteGender)

        }

        binding.autoCompleteReligion.setOnClickListener {
            val religion = resources.getStringArray(R.array.Relegion)
            dropDownMenuAdapter(religion, binding.autoCompleteReligion)

        }

        binding.autoCompleteCaste.setOnClickListener {
            val caste = resources.getStringArray(R.array.Caste)
            dropDownMenuAdapter(caste, binding.autoCompleteCaste)

        }
    }


    private fun dropDownMenuAdapter(dropDown_itemName: Array<String>, autoComplete : AutoCompleteTextView){
        val arrayAdapter = ArrayAdapter(
            this,
            R.layout.dropdown_item,
            dropDown_itemName)
        autoComplete.setAdapter(arrayAdapter)
    }

    private fun popUpCalendar() {
        val calendar_inst = Calendar.getInstance()

        val year = calendar_inst.get(Calendar.YEAR)
        val month = calendar_inst.get(Calendar.MONTH)
        val day = calendar_inst.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year, monthOfYear, dayOfMonth ->
                val dat = (dayOfMonth.toString() + "/" + (monthOfYear+1) + "/" + year)
                binding.editTextDob.setText(dat)
            },
            year,month,day
        )

        datePickerDialog.show()

    }


    @SuppressLint("SuspiciousIndentation")
    private fun saveData() {

        val firstName = binding.edit12.text.toString()
        val motherName = binding.editTextMother.text.toString()
        val fatherName = binding.editTextFather.text.toString()
        val dateOfBirth = binding.editTextDob.text.toString()
        val genders    = binding.autoCompleteGender.text.toString()
        val aadharNo  = binding.editTextAadhar.text.toString()
        val relegion  = binding.autoCompleteReligion.text.toString()
        val caste    = binding.autoCompleteCaste.text.toString()
        val studentWhatsaAppNumber = binding.editTextStuWtspNmbr.text.toString()
        val studentPhoneNumber = binding.editTextStuSecondNmbr.text.toString()
        val stuMotherPhoneNumber = binding.editTextStuMthrNmbr.text.toString()
        val stuFatherPhoneNumber = binding.editTextStuFthrNmbr.text.toString()
        val stuAddress = binding.editTextStuAddress.text.toString()
        val stuSchoolName = binding.editTextStuSchlNm.text.toString()
        val stuRollNo = binding.editTextStuRollNo.text.toString()
        val stuStandard = binding.editTextStandard.text.toString()
        val stuFees = binding.editTextFeeToBePaid.text.toString()

            val intent1 = intent
            val userId1 = intent1.getStringExtra("Uid")

            val userMap = hashMapOf(
                        "name" to firstName,
                        "MotherName" to motherName,
                        "Father Name" to fatherName,
                        "Dtae Of Birth" to dateOfBirth,
                        "Gender" to genders,
                        "Student Aadhar" to aadharNo,
                        "Relegion" to relegion,
                        "Caste " to caste,
                        "Student WhatsApp Phone  Number" to  studentWhatsaAppNumber,
                        "Student Second Phome Number" to  studentPhoneNumber,
                        "Student Mother Phone Number " to stuMotherPhoneNumber,
                        "Student Father Phone Number" to stuFatherPhoneNumber,
                        "Student Full Address" to stuAddress,
                        "Student School Name " to stuSchoolName,
                        "Student Roll No." to stuRollNo,
                       "Student Standard" to stuStandard,
                        "Fees To Be Paid" to stuFees)
                    database.collection("Student").document(userId1!!).set(userMap)
                            
        .addOnSuccessListener {

            binding.edit12.text?.clear()
            binding.editTextMother.text?.clear()
            binding.editTextFather.text?.clear()
            binding.editTextDob.text?.clear()
            binding.autoCompleteGender.text?.clear()
            binding.editTextAadhar.text?.clear()
            binding.autoCompleteReligion.text?.clear()
            binding.autoCompleteCaste.text?.clear()
            binding.editTextStuWtspNmbr.text?.clear()
            binding.editTextStuSecondNmbr.text?.clear()
            binding.editTextStuMthrNmbr.text?.clear()
            binding.editTextStuAddress.text?.clear()
            binding.editTextStuSchlNm.text?.clear()
            binding.editTextStuRollNo.text?.clear()
            binding.editTextStandard.text?.clear()
            binding.editTextFeeToBePaid.text?.clear()

            Toast.makeText(this,"Successfully Saved", Toast.LENGTH_SHORT).show()



        }.addOnFailureListener{

            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }
    }
}