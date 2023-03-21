package com.example.studypoint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class student_dash : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var imgView : ImageView
   // private lateinit var firestoreReference: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_dash)

        val firestoreReference = Firebase.firestore
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        val ref = firestoreReference.collection("Student").document(userId)


        ref.get().addOnSuccessListener {
            // In success listener we'll get all of our data in document snapshot format
            if(it!=null){

                val name  = it.data?.get("name").toString()
                    var tvName : TextView = findViewById(R.id.main_name)
                   tvName.text = "Hey, $name"

                  val fees = it.data?.get("Fees To Be Paid").toString()
                  var tvFees : TextView = findViewById(R.id.txt_ass1)
                //Toast.makeText(this, fees, Toast.LENGTH_SHORT ).show()

                tvFees.text = "Rs $fees"


            }


        }.addOnFailureListener{
            Toast.makeText(this,"Failed!!", Toast.LENGTH_SHORT).show()

        }



        //  Toast.makeText(this,"Display ",Toast.LENGTH_LONG).show()
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        imgView = findViewById(R.id.btn)

        // to make the Navigation drawer icon always appear on the action bar
    imgView.setOnClickListener {

        drawerLayout.openDrawer(GravityCompat.START)

    }




        val navView : NavigationView = findViewById(R.id.navOpener)
        navView.setNavigationItemSelectedListener { // it'll set what to do after clicking on the buttons in the navigation Bar


            when (it.itemId) {
                R.id.nav_account -> {

                    val intentStudentProfie =
                        Intent(this, StuProfile::class.java)
                    startActivity(intentStudentProfie)
                    Toast.makeText(this, "HELLO", Toast.LENGTH_SHORT ).show()

                }
                R.id.nav_payments ->{
                    val intentPaymentPortal =
                        Intent(this, PaymentPortal::class.java)
                    startActivity(intentPaymentPortal)
                }
            }
            true
        }

     // Code for Image Slider starts here
        val imageSlider = findViewById<ImageSlider>(R.id.imageSlider)
        val imageList = ArrayList<SlideModel>()

        val link = "https://assets.telegraphindia.com/telegraph/2022/Sep/1662376760_murmu-teacher.jpg"
        val link1 = "https://indiabookofrecords.in/wp-content/uploads/2019/10/maximum-students-presenting-thanksgiving-cards-to-teachers-in-a-day-4.jpg"
        val link2 = "https://www.dailyexcelsior.com/wp-content/uploads/2022/08/page10-14.jpg"
        val link3 = "https://static.pib.gov.in/WriteReadData/userfiles/PIBBhubaneswar/123.jpeg"
        val link4 = "https://static.pib.gov.in/WriteReadData/userfiles/image/image002OZWH.jpg"
        imageList.add(SlideModel(link,"Vidhya's Student Ashish Won International Olympiad"))
        imageList.add(SlideModel(link1,"One of ours Won SIH 2023"))
        imageList.add(SlideModel(link2,"Vidhya Congratulate Aman and Rohan for Coming First"))
        imageList.add(SlideModel(link3,"Team Ram Congo for coming First in HacakversuMM'22"))
        imageList.add(SlideModel(link4,"Winners Of Innerve'22 "))

        imageSlider.setImageList(imageList, ScaleTypes.FIT)


       // val openStuProfile : Button  = findViewById(R.id.nav_account)

    }
    // Code for ImageSlider ends here.




    // override the onOptionsItemSelected()
    // function to implement
    // the item click listener callback
    // to open and close the navigation
    // drawer when the icon is clicked
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}