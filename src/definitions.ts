import type { PermissionState } from '@capacitor/core';

export declare type AndroidRestrictedInfoPermissionType = 'phoneState';
export interface AndroidRestrictedInfoPermissions {
  permissions: AndroidRestrictedInfoPermissionType[];
}

export interface RestrictedDeviceInfoResult {
  imei: string;
  serial: string;
}

export interface PermissionStatus {
  info: PermissionState;
}

export interface AndroidRestrictedInfoPlugin {
  getInfo(): Promise<RestrictedDeviceInfoResult>;
  checkPermissions(): Promise<PermissionStatus>;
  requestPermissions(permissions?: AndroidRestrictedInfoPermissions): Promise<PermissionStatus>;
}