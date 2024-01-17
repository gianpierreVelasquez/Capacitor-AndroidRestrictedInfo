import { WebPlugin } from '@capacitor/core';

import type { AmdroidRestrictedInfoPlugin } from './definitions';

export class AmdroidRestrictedInfoWeb extends WebPlugin implements AmdroidRestrictedInfoPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
