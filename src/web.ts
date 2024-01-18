import { WebPlugin } from '@capacitor/core';

import type {
  AndroidRestrictedInfoPlugin,
  PermissionStatus,
  RestrictedDeviceInfoResult,
} from './definitions';

export class AndroidRestrictedInfoWeb
  extends WebPlugin
  implements AndroidRestrictedInfoPlugin
{
  getInfo(): Promise<RestrictedDeviceInfoResult> {
    throw new Error('Method not implemented.');
  }
  checkPermissions(): Promise<PermissionStatus> {
    throw new Error('Method not implemented.');
  }
  requestPermissions(): Promise<PermissionStatus> {
    throw new Error('Method not implemented.');
  }
}
