import { registerPlugin } from '@capacitor/core';

import type { AndroidRestrictedInfoPlugin } from './definitions';

const AndroidRestrictedInfo = registerPlugin<AndroidRestrictedInfoPlugin>('AndroidRestrictedInfo', {
  web: () => import('./web').then(m => new m.AndroidRestrictedInfoWeb()),
});

export * from './definitions';
export { AndroidRestrictedInfo };
