package com.fahmihidayat.expojcbottomsheet

import android.content.Context
import android.graphics.Canvas
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.ComposeView
import expo.modules.kotlin.AppContext
import expo.modules.kotlin.viewevent.EventDispatcher
import expo.modules.kotlin.views.ExpoView

class ExpoJcBottomSheetView(context: Context, appContext: AppContext) : ExpoView(context, appContext){
    private val onDismiss by EventDispatcher()
    private val uiState = mutableStateOf(JcBottomSheetState())
    private var isViewInvalidated = false

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        if (changed) { isViewInvalidated = true }
        return
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (isViewInvalidated) {
            val child = getChildAt(0)
            removeView(child)
            uiState.value = uiState.value.copy(child = child)
            isViewInvalidated = false;
        }
    }

    init {
        ComposeView(context).also {
            addView(it)
            it.layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            it.setContent {
                JcBottomSheetView(
                    state = uiState,
                    onDismiss = onDismiss
                )
            }
        }
    }

    fun updateIsPresented(isPresented: Boolean) {
        uiState.value = uiState.value.copy(isPresented = isPresented)
    }
    fun updateHideDragIndicator(hideDragIndicator: Boolean) {
        uiState.value = uiState.value.copy(hideDragIndicator = hideDragIndicator)
    }
    fun updateCornerRadius(cornerRadius: Int?) {
        uiState.value = uiState.value.copy(cornerRadius = cornerRadius)
    }
}


