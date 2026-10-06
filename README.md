[Fork of Pazaz/RT4-Client](https://github.com/pazaz/rt4-client)

## Goals

* Create new static classes by grouping related members
* Fix poor decompiler behavior (fernflower)
* Replace magic numbers and bitmasks with named final fields
* Refactor code to improve behavior/readability
* Modernize code/libraries (High DPI support, modern refresh rates, ...)
* Support existing servers via global config flags that adjust packet behaviors
* Organize classes into packages

OpenRS2 annotations are left in the source to build a deob map from, in case some of my changes aren't desirable.  
That mapping can be used to generate a new deob with everything renamed for you.

## Instructions

Build requirements:
* Java 8+

Runtime requirements:
* SD: Java 8+
* HD on Windows, use Java 15 or lower. There is a JOGL issue on 16+ related to how they grab the WGL context from the window.
* HD on Linux: Java 8+
* HD on macOS: Not possible yet on latest macOS. Might work for earlier OS versions.

```
git clone https://github.com/Pazaz/RT4-Client.git
cd RT4-Client
./gradlew run
```

You will be connected to a test server automatically.  
This server is provided by 2009scape for their own internal developments.

### Development vs AI-only builds

A default build is an **AI-only locked** client (AIO-11): real mouse and keyboard input is
dropped in game, plugins do not load, the native login form is refused, and the only live
human interactions are creating an account and opening the world list. Use it only for the
public AI-only world. For local development:

```
./gradlew :client:run -PaiOnly=false
```

That flag is the only switch, and it is compile-time: `java -Drt4.lockdown=false -jar ...`
stays locked.

The release jar bakes in the public address, world and RSA modulus, and is always locked:

```
./gradlew :client:releaseJar -PpublicHost=play.example.org -PrsaModulus=<decimal>
```

Output: `client/build/libs/rt4-aionly-<version>.jar`. Java 11+.

## Deviations

Configurable:
- Packet behaviors to make it compatible with existing servers
- View distance in HD
- Bilinear map filtering in HD/SD
- Tweening enabled by default (existed in client)
- Shift-click behavior on inventory items enabled by default (existed in client)
- Login screen music uses the player's saved Music Volume setting instead of defaulting to max
- Compatibility patch for HD point-light rendering in Diango's Workshop (legacy 2005 Christmas event)

Unconfigurable:
- JOGL was updated to 2.4.0
- Update/render loop was decoupled to tick indepedently from each other
- Camera panning input rewritten to use render loop timing
- Varp array size was extended to 3500 instead of 2500
- Mouse wheel camera movement (click middle button and move mouse)
- Render FPS is set to your monitor's refresh rate


## Policy on Use of AI Tools

You, the contributor, agree to submit quality code to the best of your ability which you have tested yourself and confirmed that it works as intended, and if it doesn’t work, you agree to take constructive feedback and fix it. 

You should be able to explain or defend how any part of your code works, without the use of AI or any other assistive tools. 

2009scape reserves the right to reject your MR on grounds of poor quality, poor functionality, poor code style, poor attitudes or unethical over-reliance on assistive tools.  

2009scape reserves the right to bar an individual from contributing due to patterns of aforementioned behavior, and close or delete all future MRs or issues or suggestions from said person without given explanation.

AI may not be used to generate your MR description, your issue description, or any responses to any discourse present on the Gitlab at any time or in any fashion. If you use AI in any of these places, it will be assumed that your entire contribution is AI and that you have no clue how it functions, and the remainder of the policy outlined above will be applied to you immediately.

## Libraries Used

- JOGL/Gluegen 2.4.0rc
- Google Gson 2.9.0
