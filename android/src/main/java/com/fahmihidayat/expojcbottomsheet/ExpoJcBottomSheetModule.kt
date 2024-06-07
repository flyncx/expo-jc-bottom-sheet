package com.fahmihidayat.expojcbottomsheet

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class ExpoJcBottomSheetModule : Module() {
  // Each module class must implement the definition function. The definition consists of components
  // that describes the module's functionality and behavior.
  // See https://docs.expo.dev/modules/module-api for more details about available components.
  override fun definition() = ModuleDefinition {
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('ExpoJcBottomSheet')` in JavaScript.
    Name("ExpoJcBottomSheet")

    // Enables the module to be used as a native view. Definition components that are accepted as part of
    // the view definition: Prop, Events.
    View(ExpoJcBottomSheetView::class) { 
      Events("onDismiss")
      Prop("isPresented") { view: ExpoJcBottomSheetView, isPresented: Boolean? ->
        view.updateIsPresented(isPresented ?: false)
      }
      Prop("hideDragIndicator") { view: ExpoJcBottomSheetView, hideDragIndicator: Boolean? ->
        view.updateHideDragIndicator(hideDragIndicator ?: false)
      }
      Prop("cornerRadius") { view: ExpoJcBottomSheetView, cornerRadius: Int? ->
        view.updateCornerRadius(cornerRadius ?: null)
      }}
  }
}
