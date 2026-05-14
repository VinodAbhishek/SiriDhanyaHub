package com.vinodabhishek.siridhanyahub

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.sin
import kotlin.random.Random

class ParticleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val particles = mutableListOf<Particle>()
    private var frameCount = 0

    data class Particle(
        var x: Float,
        var y: Float,
        val size: Float,
        val speed: Float,
        var alpha: Float,
        val delay: Int
    )

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        particles.clear()
        repeat(20) {
            particles.add(
                Particle(
                    x = Random.nextFloat() * w,
                    y = Random.nextFloat() * h,
                    size = Random.nextFloat() * 8f + 3f,
                    speed = Random.nextFloat() * 2f + 1f,
                    alpha = 0f,
                    delay = Random.nextInt(100)
                )
            )
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        frameCount++

        val w = width.toFloat()
        val h = height.toFloat()

        // Draw pulse rings
        val ringProgress = (frameCount % 80) / 80f
        repeat(3) { i ->
            val progress = ((ringProgress + i * 0.33f) % 1f)
            val radius = progress * 200f + 40f
            val ringAlpha = ((1f - progress) * 100).toInt()
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 2f
            paint.color = android.graphics.Color.argb(ringAlpha, 255, 255, 255)
            canvas.drawCircle(w / 2, h / 2, radius, paint)
        }

        // Draw floating particles
        paint.style = Paint.Style.FILL
        for (p in particles) {
            if (frameCount < p.delay) continue
            p.y -= p.speed
            p.alpha = minOf(p.alpha + 0.02f, 0.8f)

            if (p.y < -20f) {
                p.y = h + 10f
                p.x = Random.nextFloat() * w
                p.alpha = 0f
            }

            paint.color = android.graphics.Color.argb(
                (p.alpha * 255).toInt(), 255, 255, 255
            )
            canvas.drawCircle(p.x, p.y, p.size, paint)
        }

        // Gradient shift via tint overlay
        val shift = (sin(frameCount * 0.02) + 1) / 2f
        val overlayAlpha = (shift * 40).toInt()
        paint.style = Paint.Style.FILL
        paint.color = android.graphics.Color.argb(overlayAlpha, 74, 20, 140)
        canvas.drawRect(0f, 0f, w, h, paint)

        invalidate()
    }
}