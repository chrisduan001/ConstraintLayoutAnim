package com.example.chris.constraintlayoutanim

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var rootLayout: ConstraintLayout
    var selectedView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val topImage = findViewById<ImageView>(R.id.top_image)
        val bottomImage = findViewById<ImageView>(R.id.bottom_image)

        rootLayout = findViewById(R.id.root)

        topImage.setOnClickListener {
            if (selectedView == it) {
                toDefault()

                return@setOnClickListener
            }

            selectedView = it
            updateConstraints(R.layout.activity_main_top)
        }

        bottomImage.setOnClickListener {
            if (selectedView == it) {
                toDefault()

                return@setOnClickListener
            }

            selectedView = it
            updateConstraints(R.layout.activity_main_bottom)
        }

    }

    fun updateConstraints(@LayoutRes id: Int) {
        val newConstraintSet = ConstraintSet()
        newConstraintSet.clone(this, id)
        newConstraintSet.applyTo(rootLayout)
        val transition = ChangeBounds()
        transition.interpolator = OvershootInterpolator()
        TransitionManager.beginDelayedTransition(rootLayout, transition)
    }

    private fun toDefault() {
        updateConstraints(R.layout.activity_main)
        selectedView = null
    }
}
