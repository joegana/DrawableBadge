package com.joeiot.drawablebadge

import android.graphics.Rect

/**
 * Let Rect R move into this Rect
 * @param r
 */
fun Rect.moveIn(r:Rect):Boolean{
    if(!this.contains(r)){
        val rW = r.width()
        val rH = r.height()
        val tW = this.width()
        val tH = this.height()

        if(rW > tW || rH > tH)
            return false

       if(r.top < top){
           r.top = top
           r.bottom = r.top + rH
       }

       if(r.bottom > bottom){
           r.bottom = bottom
           r.top  = r.bottom - rH
       }

       if(r.left < left){
           r.left = left
           r.right = r.left + rW
       }

      if(r.right > right){
          r.right = right
          r.left = r.right - rW
      }
      return true
    }
    return false
}