# MCP-02 — JUnit 5 test setup for `:client`

**Goal:** `./gradlew :client:test` runs JUnit 5 tests. The client has no tests today.

**Depends on:** —

## Files
- `client/build.gradle`
- `client/src/test/java/rt4/mcp/SmokeTest.java` (placeholder, delete once real tests land)

## Implementation
1. `settings.gradle` already declares `mavenCentral()`, so no new repositories are needed.
2. Add the following to `client/build.gradle`. JUnit 5.10 still supports Java 8, and Gradle 7.4.2 supports `useJUnitPlatform()`:
   ```groovy
   dependencies {
       testImplementation 'org.junit.jupiter:junit-jupiter:5.10.3'
       testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
   }
   test {
       useJUnitPlatform()
       testLogging { events 'failed'; exceptionFormat 'full' }
   }
   ```
   If the BOM-less launcher line fails to resolve, pin it to `1.10.3`.
3. Run with the repo toolchain:
   `JAVA_HOME=../.toolchain/jdk-11.0.32.1+1 GRADLE_USER_HOME=../.gradlehome ./gradlew :client:test`.
4. **Testability rule for all later tickets:** keep the logic that's worth testing free of `rt4.*` statics.
   That covers JSON-RPC, A*, target parsing, auth checks and condition evaluation.
   Static game state goes behind small interfaces (e.g. `CollisionSource`, `Clock`) so tests can pass fakes.
   Most `rt4` classes run static initializers that touch the cache and AWT, so never load them in tests.

## Acceptance criteria
- `./gradlew :client:test` passes with the placeholder test.
- `./gradlew :client:run` is unaffected.
- The fat `jar` doesn't include test classes.
