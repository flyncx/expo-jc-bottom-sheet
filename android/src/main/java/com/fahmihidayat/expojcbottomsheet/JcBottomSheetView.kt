package com.fahmihidayat.expojcbottomsheet

import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import expo.modules.kotlin.viewevent.ViewEventCallback


data class JcBottomSheetState(
    var isPresented: Boolean = false,
    var hideDragIndicator: Boolean = false,
    var cornerRadius: Int? = null,
    var child: View? = null,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JcBottomSheetView(
    state: MutableState<JcBottomSheetState>,
    onDismiss: ViewEventCallback<Map<String, Any>>?
) {
    val sheetState = rememberModalBottomSheetState()
    if (state.value.isPresented) {
        ModalBottomSheet(
            onDismissRequest = {
                if (onDismiss != null) {
                    onDismiss(mapOf("dismissed" to true))
                }
            },
            sheetState = sheetState,
            shape = if (state.value.cornerRadius == null) {
                BottomSheetDefaults.ExpandedShape
            } else {
                RoundedCornerShape(
                    topStart = state.value.cornerRadius?.dp ?: 0.dp,
                    topEnd = state.value.cornerRadius?.dp ?: 0.dp,
                )
            },
            dragHandle = {
                if (!state.value.hideDragIndicator) {
                    BottomSheetDefaults.DragHandle()
                } else null
            }
        ) {
            Column {
                state.value.child?.let { child ->
                    AndroidView(
                        modifier = Modifier.fillMaxHeight(),
                        factory = { child },
                    )
                }
            }
        }
    }
}