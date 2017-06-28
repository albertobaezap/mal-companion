package com.example.alberto.malcompanion.ui.custom

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.example.alberto.malcompanion.R
import kotlinx.android.synthetic.main.button_waifu.view.*

/**
 * Custom view to handle the waifu button to display information about the series
 */
class WaifuButton @JvmOverloads constructor(
   context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

   val animatorSet: AnimatorSet = AnimatorSet()

   companion object {
      val ANIMATION_ALPHA_MIN: Float = 0f
      val ANIMATION_ALPHA_MAX: Float = 1f
      val ANIMATION_DURATION: Long = 300 //ms
      val ANIMATION_DELAY: Long = 2000 //ms
   }

   init {

      val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
      inflater.inflate(R.layout.button_waifu, this, true)

      createSpeechBubbleAnimation()
      waifu_button.setOnClickListener {
         displayText(resources.getString(R.string.ouch))
      }
   }

   /**
    * Display the bubble text animation with a text.
    */
   fun displayText(text: String) {

      if (animatorSet.isRunning) animatorSet.cancel()
      animatorSet.removeAllListeners()
      animatorSet.addListener(object : AnimatorListenerAdapter() {
         override fun onAnimationStart(animation: Animator) {
            bubble_text.text = text
            bubble_text.pivotX = bubble_text.x + bubble_text.width
         }
      })
      animatorSet.start()
   }

   fun cancelAnimation() {
      animatorSet.cancel()
      bubble_text.alpha = 0f
   }

   /**
    * Create animation for the speech bubble.
    */
   fun createSpeechBubbleAnimation() {
      val showDialogScaleXAnim = ObjectAnimator.ofFloat(bubble_text, View.SCALE_X, 0f, 1f)
      val showDialogScaleYAnim = ObjectAnimator.ofFloat(bubble_text, View.SCALE_Y, 0f, 1f)
      val showDialogAlphaAnim = ObjectAnimator.ofFloat(bubble_text, View.ALPHA, ANIMATION_ALPHA_MIN, ANIMATION_ALPHA_MAX)
      val hideDialogScaleXAnim = ObjectAnimator.ofFloat(bubble_text, View.SCALE_X, 1f, 0f)
      val hideDialogScaleYAnim = ObjectAnimator.ofFloat(bubble_text, View.SCALE_Y, 1f, 0f)
      val hideDialogAlphaAnim = ObjectAnimator.ofFloat(bubble_text, View.ALPHA, ANIMATION_ALPHA_MAX, ANIMATION_ALPHA_MIN)

      hideDialogScaleXAnim.startDelay = ANIMATION_DELAY + ANIMATION_DURATION
      hideDialogScaleYAnim.startDelay = ANIMATION_DELAY + ANIMATION_DURATION
      hideDialogAlphaAnim.startDelay = ANIMATION_DELAY + ANIMATION_DURATION

      animatorSet.playTogether(showDialogScaleXAnim, showDialogScaleYAnim, hideDialogScaleXAnim, hideDialogScaleYAnim,
         showDialogAlphaAnim, hideDialogAlphaAnim)
      animatorSet.duration = ANIMATION_DURATION
//      animatorSet.interpolator = OvershootInterpolator()

   }

}