# AIO-15 — Release jar: baked address and key, first-run config

**Goal:** An operator downloads one jar, runs `java -jar`, and lands on the public world's title
screen with lockdown on and a working MCP endpoint.

**Repo:** client · **Depends on:** AIO-03, AIO-11

## Files
- `client/build.gradle` (`releaseJar` task, properties)
- `client/src/main/resources/rt4/aionly/default-config.json` (generated at build time)
- `client/src/main/java/rt4/client.java` (first-run config bootstrap near line 227)

## Implementation
1. Gradle properties:
   - `-PpublicHost=<host>` (required for `releaseJar`)
   - `-PpublicWorld=1`
   - `-PrsaModulus=<decimal>` (from AIO-03)
2. `releaseJar` depends on a generated `default-config.json`:
   - `ip_address`/`ip_management` = host
   - world and ports as in today's `config.json`
   - `rsa_modulus`
   - `mcp_enabled=true`, `mcp_port=43600`, `mcp_token=""`
   It forces `aiOnly=true` (AIO-11) and fails the build if `-PaiOnly=false` is passed.
3. First run: if no `config.json` exists at the resolved path, copy the bundled `default-config.json` there, then
   load it as usual. This keeps `McpConfig.writeToken` working, since it needs a real file. Without this, every
   start would get a new in-memory MCP token.
4. Keep the existing fat-jar setup (`jar { from configurations.compileClasspath … }`). Name the output
   `rt4-aionly-<version>.jar`.
5. Don't bundle any plugins (lockdown skips them anyway).
6. JDK: document Java 11+. HD on Linux works on 8+, per the README.

## Acceptance criteria
- `./gradlew :client:releaseJar -PpublicHost=play.example.org -PrsaModulus=…` produces a jar.
- Running the jar in an empty directory:
  - creates `config.json` with the public host;
  - generates an MCP token on first start and reuses it on the second;
  - starts with lockdown on;
  - connects to the public server.
- `./gradlew :client:releaseJar -PaiOnly=false` fails with a clear message.

## Tests
- A Gradle-level check that the jar contains `rt4/aionly/build.properties` with `lockdown=true` and the expected
  `default-config.json` host. This can be a small verification task.
- `ConfigBootstrapTest` (pure helper, temp dir): copies when missing and never overwrites an existing file.
