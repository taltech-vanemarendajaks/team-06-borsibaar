import { dirname } from "path";
import { fileURLToPath } from "url";
import { FlatCompat } from "@eslint/eslintrc";

const __filename = fileURLToPath(import.meta.url);
const __dirname = dirname(__filename);

const compat = new FlatCompat({
  baseDirectory: __dirname,
});

const eslintConfig = [
  ...compat.extends("next/core-web-vitals", "next/typescript"),
  {
    ignores: [
      "node_modules/**",
      ".next/**",
      "out/**",
      "build/**",
      "next-env.d.ts",
    ],
    "rules": {
      "@typescript-eslint/explicit-module-boundary-types": "off",
      "@typescript-eslint/no-explicit-any": ["off"],
      "@typescript-eslint/no-empty-function": "off",
      "@typescript-eslint/no-unused-vars": "off",
      "@typescript-eslint/no-inferrable-types": "off",
      "prettier/prettier": ["off"],
      "no-prototype-builtins": "off",
      "no-empty": "off",
      "no-case-declarations": "off",
      "no-async-promise-executor": "off",
      "no-empty-pattern": "off",
  },
  },
];

export default eslintConfig;
