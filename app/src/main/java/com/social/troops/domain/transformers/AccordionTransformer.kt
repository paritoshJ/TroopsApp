package com.social.troops.newWork.domain.transformers

import android.view.View


class AccordionTransformer : BaseTransformer() {
    override fun onTransform(view: View, position: Float) {
        view.setPivotX(if (position < 0f) 0f else view.getWidth().toFloat())
        view.setScaleX(if (position < 0) 1f + position else 1f - position)
    }

//    protected fun onTransform(view: View, position: Float) {
//
//    }

}