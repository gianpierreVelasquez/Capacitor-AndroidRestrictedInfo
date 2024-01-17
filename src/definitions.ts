export interface AmdroidRestrictedInfoPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
