import type { PermissionState } from '@capacitor/core';

export interface AndroidRestrictedInfoPlugin {
  getInfo(): Promise<RestrictedDeviceInfoResult>;
  checkPermissions(): Promise<PermissionStatus>;
  requestPermissions(): Promise<PermissionStatus>;
}

export interface RestrictedDeviceInfoResult {
  imei: string;
  serial: string;
}

export interface PermissionStatus {
  info: PermissionState;
}
