import { requireNativeViewManager } from "expo-modules-core";
import * as React from "react";

import { ViewProps } from "react-native";

export type Props = {
  isPresented: boolean;
  detents?: ("medium" | "large" | { fraction: number } | { height: number })[];
  hideDragIndicator?: boolean;
  cornerRadius?: number;
  onDismiss?: () => void;
  children?: React.ReactNode;
} & ViewProps;

const NativeView: React.ComponentType<Props> =
  requireNativeViewManager("ExpoJcBottomSheet");

export default function ExpoJcBottomSheetView({ children, ...props }: Props) {
  return <NativeView {...props}>{children}</NativeView>;
}
