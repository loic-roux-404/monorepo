export const getRepoKey: (a: string) => string =
  (modelClassName: string) => `${modelClassName}Repository`
