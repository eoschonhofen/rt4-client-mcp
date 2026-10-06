# AIO-03 — Private RSA key pair for the public world

**Goal:** The token in registration opcode `36` and the password in the login block are RSA
encrypted with a key whose private half only the public server has.

**Repo:** main + client · **Depends on:** AIO-02

## Problem
The client modulus (`client/.../rt4/GlobalConfig.java:14`) equals the server modulus
(`Server/src/main/core/ServerConstants.kt:268`). The matching **private exponent is hardcoded in the
public upstream source** (`ServerConstants.kt:264`). Anyone who sniffs a registration or login can
decrypt the token. Generating a new key pair fixes this.

## Files
- main: `Server/src/main/core/ServerConstants.kt`, `ServerConfigParser.kt`, `core/tools/RSAKeyGen.java` (reuse)
- main: `Server/worldprops/public.conf.example` (key path)
- main: `.gitignore` (`Server/data/rsa/`)
- client: `client/src/main/java/rt4/GlobalConfig.java`, `GlobalJsonConfig.java`, `client.java`

## Implementation
### Server
1. Generate a 1024-bit pair with the existing `RSAKeyGen`. It writes `rsapriv`/`rsapub`; check their format and
   adapt if needed. 1024 bits matches the client's block size (128-byte buffer in
   `CreateManager.createAccount`). Don't change the size.
2. Store the pair in `Server/data/rsa/` (gitignored, mode 600).
3. New config key `server.rsa_key_path`. When set, `ServerConfigParser` loads `MODULUS` and `EXPONENT` from it.
   When unset, keep today's hardcoded defaults, so `default.conf` dev keeps working with stock clients.
4. Never log the private exponent.

### Client
1. Make `GlobalConfig.RSA_MODULUS` non-final, and add an optional `rsa_modulus` key to `GlobalJsonConfig` (decimal string).
2. Apply it right after `GlobalJsonConfig.load(configPath)` (`client.java:254`), before any login or create can run.
3. AIO-15 bakes the public modulus into the release jar's bundled default config.
4. Exponent stays 65537.

## Acceptance criteria
- With the new key on both sides, register and login both work.
- A client without `rsa_modulus` against the public server fails registration with
  "Decryption failed during registration" (server log) and fails login. It does not crash.
- `default.conf` + a client without the key still work (dev).
- The private key file is not in git.

## Tests
- Server: round-trip test. Encrypt with the public half the way `Buffer.rsaenc` does, then decrypt with
  `Login.decryptRSABuffer` using the loaded private half, and check the header byte `10`.
- Client: `GlobalJsonConfig` parse test where `rsa_modulus` overrides the default and a missing key keeps the default.
