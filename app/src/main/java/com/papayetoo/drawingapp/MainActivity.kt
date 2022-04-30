package com.papayetoo.drawingapp

import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.iterator

class MainActivity : AppCompatActivity() {

    private var drawingView: DrawingView? = null
    private var mImageButtonCurrentPaint: ImageButton? = null
    private var linearLayout: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView = findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.toFloat())

        linearLayout = findViewById(R.id.ll_paint_colors)
        linearLayout?.let { layout ->
            Unit
            mImageButtonCurrentPaint = layout[0] as ImageButton
            mImageButtonCurrentPaint?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
            )
        }

        setBrushColor()

        val ibBrush: ImageButton = findViewById(R.id.ib_brush)
        ibBrush.setOnClickListener {
            showBrushSizeChooserDialog()
        }
    }

    private fun showBrushSizeChooserDialog() {
        val brushDialog = Dialog(this)
        brushDialog.setContentView(R.layout.dialog_brush_size)
        brushDialog.setTitle("Brush size: ")
        val smallBtn: ImageButton = brushDialog.findViewById(R.id.ib_small_brush)
        smallBtn.setOnClickListener {
            drawingView?.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        val mediumButton: ImageButton = brushDialog.findViewById(R.id.ib_medium_brush)
        mediumButton.setOnClickListener {
            drawingView?.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
        val largeButton: ImageButton = brushDialog.findViewById(R.id.ib_large_brush)
        largeButton.setOnClickListener {
            drawingView?.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }
        brushDialog.show()
    }

    private fun setBrushColor() {
        linearLayout?.let {
            for (i in 0 until it.childCount) {
                it[i].setOnClickListener { button -> Unit
                    mImageButtonCurrentPaint?.setImageDrawable(
                        ContextCompat.getDrawable(this, R.drawable.pallet_normal)
                    )
                    mImageButtonCurrentPaint = button as ImageButton
                    val colorTag = button.tag.toString()
                    drawingView?.setColor(colorTag)
                    mImageButtonCurrentPaint?.setImageDrawable(
                        ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
                    )
                }
            }
        }
    }
}