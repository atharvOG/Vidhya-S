package com.example.studypoint

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.studypoint.databinding.ActivityAddstudentBinding
import com.example.studypoint.databinding.ActivityAdminDashBinding
import com.example.studypoint.databinding.ActivityStuProfileBinding
import com.example.studypoint.databinding.ActivityWelcomBackBinding
import com.google.android.material.navigation.NavigationView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class AdminDash : AppCompatActivity() {

    private lateinit var binding: ActivityAdminDashBinding
    private lateinit var drawerLayoutAdmin : DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    lateinit var imgView : ImageView
    lateinit var lineGraphView: GraphView     // variables for our graph view




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminDashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callDrawer()

        boardOfAppriciation()

        adminLineGraph()

        binding.adminBottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.btn_Teachers ->
                    Toast.makeText(this, "Teachers", Toast.LENGTH_SHORT).show()

                else -> {

                }
            }
            true
        }


    }

    private fun bottomNav() {




    }

    private fun adminLineGraph() {
        lineGraphView = binding.idGraphView

        val series: LineGraphSeries<DataPoint> = LineGraphSeries(
            arrayOf(
                // on below line we are adding
                // each point on our x and y axis.
                DataPoint(0.0, 1.0),
                DataPoint(1.0, 8.0),
                DataPoint(2.0, 4.0),
                DataPoint(3.0, 9.0),
                DataPoint(4.0, 6.0),
                DataPoint(5.0, 3.0),
                DataPoint(6.0, 6.0),
                DataPoint(7.0, 1.0),
                DataPoint(8.0, 2.0)
            )
        )

        // on below line adding animation
        lineGraphView.animate()

        // on below line we are setting scrollable
        // for point graph view
        lineGraphView.viewport.isScrollable = true

        // on below line we are setting scalable.
        lineGraphView.viewport.isScalable = true

        // on below line we are setting scalable y
        lineGraphView.viewport.setScalableY(true)

        // on below line we are setting scrollable y
        lineGraphView.viewport.setScrollableY(true)

        // on below line we are setting color for series.
        series.color = com.jpardogo.android.googleprogressbar.library.R.color.red

        // on below line we are adding
        // data series to our graph view.
        lineGraphView.addSeries(series)
    }

    private fun boardOfAppriciation() {
        val imageSlider = binding.adminImageSlider
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
    }

    private fun callDrawer() {
        drawerLayoutAdmin = binding.myDrawerLayoutAdmin
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayoutAdmin, R.string.nav_open, R.string.nav_close)
        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayoutAdmin.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        imgView = binding.btnOPenDrawerAdmin

        // to make the Navigation drawer icon always appear on the action bar
        imgView.setOnClickListener {

            drawerLayoutAdmin.openDrawer(GravityCompat.START)

        }




        val navView : NavigationView = binding.adminNavOpener
        navView.setNavigationItemSelectedListener { // it'll set what to do after clicking on the buttons in the navigation Bar


            when (it.itemId) {
                R.id.add_students -> {

                    val intentAddStudent =
                        Intent(this, RegisterUser::class.java)
                    startActivity(intentAddStudent)

                }
                R.id.addTeachers ->{
                    val intentAddTeacher =
                        Intent(this, PaymentPortal::class.java)
                    startActivity(intentAddTeacher)
                }
                R.id.add_batch -> {
                    Toast.makeText(this, "Add Batch", Toast.LENGTH_SHORT).show()

                }
                R.id.nav_total_Teachers -> {
                    Toast.makeText(this, "Add Teacher", Toast.LENGTH_SHORT).show()
                }
                R.id.search_student -> {
                    Toast.makeText(this, "Search Student", Toast.LENGTH_SHORT).show()
                }

                R.id.total_batches -> {
                    Toast.makeText(this, "Total Batches", Toast.LENGTH_SHORT).show()
                }

                R.id.admin_LogOut -> {
                    Toast.makeText(this, "Log Out", Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
    }



}