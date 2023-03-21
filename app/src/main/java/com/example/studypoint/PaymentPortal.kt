package com.example.studypoint

import android.graphics.BitmapFactory.Options
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject

class PaymentPortal : AppCompatActivity(), PaymentResultListener {
    lateinit var paymentStatus : TextView
    //private lateinit var firestoreReference: FirebaseFirestore
    private lateinit var amount : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_portal)


        val pay : Button = findViewById(R.id.btn_pay)
        paymentStatus = findViewById(R.id.tvStatus)


        val firestoreReference = Firebase.firestore
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = firestoreReference.collection("Student").document(userId)

        ref.get().addOnSuccessListener {
            // In success listener we'll get all of our data in document snapshot format
            if(it!=null){

                 amount  = it.data?.get("Fees To Be Paid").toString()
                //amount = "5000"
                val tvAmount : TextView = findViewById(R.id.txt_ass)
                tvAmount.text = "Rs $amount"
            }


        }.addOnFailureListener{
            Toast.makeText(this,"Failed!!", Toast.LENGTH_SHORT).show()

        }



        pay.setOnClickListener {
            payment(amount.trim().toInt())
        }
        Checkout.preload(this)
    }

    private fun payment(amount:Int) {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_8VkiLtVhFIQvqC")

        try{
            val options = JSONObject()
            options.put("name", "Vidhya")
            options.put("description", "We at Vidhya Supports Digital India")
            options.put("theme.color", "#000000")
            options.put("currency", "INR")
            options.put("amount", amount*100)
            options.put("partial_payment","false")


            val retryObj = JSONObject()

            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            options.put("retry", retryObj)

            checkout.open(this, options)
        }
        catch(e: Exception) {

            Toast.makeText(this, "Error in Payment : " + e.message, Toast.LENGTH_LONG)

                .show()

            e.printStackTrace()

        }

    }

    override fun onPaymentSuccess(p0: String?) {

        paymentStatus.text = p0 +" Payment Sucess!!"

        paymentStatus.setTextColor(Color.GREEN)

    }



    override fun onPaymentError(p0: Int, p1: String?) {

        paymentStatus.text = p1

        paymentStatus.setTextColor(Color.RED)

    }
}