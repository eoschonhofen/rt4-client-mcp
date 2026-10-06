# MCP-23 — 🐛 Screenshot reports the wrong scale after downscaling, and encodes on the game thread

**Type:** bug + perf · **Severity:** medium · **Depends on:** MCP-11

**Goal:** The coordinates an agent derives from a screenshot are correct, and taking one doesn't stall a frame.

## Problem
1. **Stale scale.** When the PNG is over `MAX_BASE64`, `ScreenshotTool` rescales to `scale / 2.0`, but the
   info block (`tools/ScreenshotTool.java:70`) still reports the requested `scale`. The description tells the
   agent "canvas coordinate = image pixel / scale", so the clicks land 2× off.
2. **Encoding on the game thread.** The tool is a `gameTool`, so cropping, bilinear scaling and PNG encoding
   all run on the game thread. That is tens of milliseconds at 765×503, longer in HD at large canvas sizes,
   and it drops frames.

## Files
- `client/src/main/java/rt4/mcp/tools/ScreenshotTool.java`
- `client/src/main/java/rt4/mcp/Screenshot.java`
- `client/src/test/java/rt4/mcp/ScreenshotTest.java`

## Implementation
- Turn it into `Tools.tool(...)`. Only `Screenshot.capture()` (an array copy) goes through
  `GameThread.call`. Crop, scale and encode run on the HTTP thread.
- Track `effectiveScale` and report it. Loop the halving until the PNG fits or the scale reaches a floor
  (0.125), instead of halving once.
- Report the region origin too (`region_x`, `region_y`), so the agent can map a cropped image back:
  `canvas = region_origin + pixel / scale`.

## Acceptance criteria
- A full-size HD screenshot that needs downscaling reports the scale it was actually encoded at.
- `get_screenshot` adds less than 2 ms to the game-thread time of the frame it runs in (measure with
  `-Dmcp.debug`).

## Tests
- `ScreenshotTest`: an oversized synthetic frame produces `scale` equal to `image.width / region.width`
  (within rounding).
- Encoding runs on a non-owner thread (assert against `GameThread.owner()` with a fake).
