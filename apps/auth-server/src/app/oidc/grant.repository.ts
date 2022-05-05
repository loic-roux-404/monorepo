import models from "./grant.model";
import { getRepoKey } from '../utils/db.utils'

export const grantsRepositories = [
  ...models.map(md => ({ provide: getRepoKey(md.name), useValue: md }))
];
