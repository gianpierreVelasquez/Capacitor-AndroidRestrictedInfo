import { registerPlugin } from '@capacitor/core';

import type { AmdroidRestrictedInfoPlugin } from './definitions';

const AmdroidRestrictedInfo = registerPlugin<AmdroidRestrictedInfoPlugin>('AmdroidRestrictedInfo', {
  web: () => import('./web').then(m => new m.AmdroidRestrictedInfoWeb()),
});

export * from './definitions';
export { AmdroidRestrictedInfo };
