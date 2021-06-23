package com.joeiot.drawablebadge

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.view.Gravity
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.DrawableCompat

/**
 * This is a tool class that can draw some badge number on Drawable
 */
class DrawableBadge private constructor(val context: Context,
                                        @ColorInt val textColor: Int,
										var textFont:  Typeface?,
                                        @ColorInt val badgeColor: Int,
                                        @ColorInt val badgeBorderColor: Int,
                                        val badgeBorderSize: Float,
                                        val badgeSize: Float,
                                        val badgeGravity: Int,
                                        val badgeMargin: Float,
                                        val bitmap: Bitmap,
                                        val isShowBorder: Boolean,
                                        val maximumCounter: Int,
                                        val isShowCounter: Boolean,){

	class Builder(private val context: Context) {

		@ColorInt private var textColor: Int? = null
	    private var textFont: Typeface? = null
		@ColorInt private var badgeColor: Int? = null
		@ColorInt private var badgeBorderColor: Int? = null
		private var badgeBorderSize: Float? = null
		private var badgeSize: Float? = null
		private var badgeGravity: Int? = null
		private var badgeMargin: Float? = null
		private var bitmap: Bitmap? = null
		private var isShowBorder: Boolean? = null
		private var maximumCounter: Int? = null
		private var isShowCounter: Boolean? = null

		private fun createBitmapFromDrawable(drawable: Drawable): Bitmap {
			val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
			val canvas = Canvas(bitmap)
			drawable.setBounds(0, 0, canvas.width, canvas.height)
			drawable.draw(canvas)
			return bitmap
		}

		/**
		 * set Drawable resource id
		 */
		fun drawableResId(@DrawableRes drawableRes: Int) = apply {
			val res = context.resources
			bitmap = BitmapFactory.decodeResource(res, drawableRes)

			if (bitmap == null) {
				ResourcesCompat.getDrawable(res, drawableRes, null)
					?.current
					?.let {
						drawable(it)
					}
			}
		}

		/**
		 * set Drawable
		 */
		fun drawable(drawable: Drawable): Builder = apply {
			val drawableCompat = DrawableCompat.wrap(drawable)
			bitmap = when (drawableCompat) {
				is BitmapDrawable -> drawableCompat.bitmap
				else              -> createBitmapFromDrawable(drawableCompat)
			}
		}

		/**
		 * set Bitmap
		 */
		fun bitmap(bitmap: Bitmap) = apply { this.bitmap = bitmap }

		/**
		 *  set badge text color
		 */
		fun textColor(@ColorRes textColorRes: Int) = apply { this.textColor = ContextCompat.getColor(context, textColorRes) }

		/**
		 * set badge test font
		 */
		fun textFont(@FontRes fontRes: Int) = apply { this.textFont = ResourcesCompat.getFont(context,fontRes) }

		/**
		 * set badge background color
		 */
		fun badgeColor(@ColorRes badgeColorRes: Int) = apply { this.badgeColor = ContextCompat.getColor(context, badgeColorRes) }

		/**
		 * set badge border color
		 */
		fun badgeBorderColor(@ColorRes badgeBorderColorRes: Int) = apply { this.badgeBorderColor = ContextCompat.getColor(context, badgeBorderColorRes) }

		/**
		 *  set badge border size using dimen resource
		 */
		fun badgeBorderSize(@DimenRes badgeBorderSize: Int) = apply {
			this.badgeBorderSize = context.resources.getDimensionPixelOffset(badgeBorderSize)
				.toFloat()
		}

		/**
		 * set badge border size
		 */
		fun badgeBorderSize(@Px badgeBorderSize: Float) = apply { this.badgeBorderSize = badgeBorderSize }

		/**
		 * set badge size
		 */
		fun badgeSize(@DimenRes badgeSize: Int) = apply {
			this.badgeSize = context.resources.getDimensionPixelOffset(badgeSize)
				.toFloat()
		}

		/**
		 * set badge size
		 */
		fun badgeSize(@Px badgeSize: Float) = apply { this.badgeSize = badgeSize }


		/**
		 * set badge gravity relative to drawable
		 */
		fun badgeGravity(badgeGravity: Int) = apply { this.badgeGravity = badgeGravity }

		fun badgeMargin(@DimenRes badgeMargin: Int) = apply {
			this.badgeMargin = context.resources.getDimensionPixelOffset(badgeMargin)
				.toFloat()
		}

		/**
		 * set badge margin relative to drawable
		 */
		fun badgeMargin(@Px badgeMargin: Float) = apply { this.badgeMargin = badgeMargin }

		/**
		 * set whether show badge border
		 */
		fun showBorder(isShowBorder: Boolean) = apply { this.isShowBorder = isShowBorder }

		/**
		 * set max badge number showing on
		 */
		fun maximumCounter(maximumCounter: Int) = apply { this.maximumCounter = maximumCounter }

		/**
		 *  set badge showing color
		 */
		fun showCounter(isShowCounter: Boolean) = apply { this.isShowCounter = isShowCounter }

		fun build(): DrawableBadge {
			if (bitmap == null) throw IllegalArgumentException("Badge drawable/bitmap can not be null.")
			if (badgeSize == null) badgeSize(R.dimen.default_badge_size)
			if (textColor == null) textColor(R.color.default_badge_text_color)
			if (badgeColor == null) badgeColor(R.color.default_badge_color)
			if (badgeBorderColor == null) badgeBorderColor(R.color.default_badge_border_color)
			if (badgeBorderSize == null) badgeBorderSize(R.dimen.default_badge_border_size)
			if (badgeGravity == null) badgeGravity(Gravity.TOP or Gravity.END)
			if (isShowBorder == null) showBorder(true)
			if (maximumCounter == null) maximumCounter(MAXIMUM_COUNT)
			if (isShowCounter == null) showCounter(true)

			return DrawableBadge(
					context = context,
					bitmap = bitmap!!,
					textColor = textColor!!,
				    textFont = textFont,
					badgeColor = badgeColor!!,
					badgeBorderColor = badgeBorderColor!!,
					badgeBorderSize = badgeBorderSize!!,
					badgeSize = badgeSize!!,
					badgeGravity = badgeGravity!!,
					badgeMargin = badgeMargin ?: 0.0f,
					isShowBorder = isShowBorder!!,
					maximumCounter = maximumCounter!!,
					isShowCounter = isShowCounter!!)
		}
	}

	fun get(counter: Int): Drawable {
		val resources = context.resources
		if (counter == 0) return BitmapDrawable(resources, bitmap)

		val sourceBitmap = bitmap
		val width = sourceBitmap.width
		val height = sourceBitmap.height

		val dRect = Rect(0, 0, width, height)
		var cReact = Rect(dRect)

		var badgeRect = getBadgeRect(dRect)
		cReact.union(badgeRect)

		val cWidth  = cReact.width()
		val cHeight = cReact.height()

		cReact.set(0,0,cWidth, cHeight)

		val output = Bitmap.createBitmap(cWidth,cHeight, Bitmap.Config.ARGB_8888)

		val canvas = Canvas(output)

		val paint = Paint().apply {
			isAntiAlias = true
			isFilterBitmap = true
			isDither = true
			textAlign = Paint.Align.CENTER
			color = badgeColor
		}
		canvas.drawBitmap(sourceBitmap, dRect, dRect, paint)

		cReact.moveIn(badgeRect)

		canvas.drawOval(RectF(badgeRect), paint)

		if (isShowBorder) {
			val paintBorder = Paint().apply {
				isAntiAlias = true
				isFilterBitmap = true
				isDither = true
				textAlign = Paint.Align.CENTER
				style = Paint.Style.STROKE
				color = badgeBorderColor
				strokeWidth = badgeBorderSize
			}
			canvas.drawOval(RectF(badgeRect), paintBorder)
		}

		if(isShowCounter) {
			val textSize: Float
			val text: String
			val max = if (maximumCounter > MAXIMUM_COUNT) MAXIMUM_COUNT else maximumCounter
			if (counter > max) {
				textSize = badgeRect.height() * 0.45f
				text = "$max+"
			} else {
				textSize = badgeRect.height() * 0.55f
				text = counter.toString()
			}

			val textPaint = TextPaint().apply {
				this.isAntiAlias = true
				this.color = textColor
				this.textSize = textSize
				textFont?.also {
					this.typeface = it
				}
			}

			val x = badgeRect.centerX() - (textPaint.measureText(text) / 2f)
			val y = badgeRect.centerY() - (textPaint.ascent() + textPaint.descent()) * 0.5f
			canvas.drawText(text, x, y, textPaint)
		}

		return BitmapDrawable(resources, output)
	}

	private fun getBadgeRect(bound: Rect): Rect {
		val borderSize = if (isShowBorder) badgeBorderSize else 0f
		val adjustSpace = borderSize + badgeMargin

		val dest = Rect()
		val size = badgeSize.toInt()
		Gravity.apply(badgeGravity, size, size, bound, adjustSpace.toInt(), adjustSpace.toInt(), dest)
		return Rect(dest)
	}

	companion object {
		private const val MAXIMUM_COUNT = 99
	}
}
