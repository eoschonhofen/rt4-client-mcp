# MCP-11 — `get_screenshot`

**Goal:** Optional visual fallback for the agent, for puzzles, unknown interfaces and sanity checks.

**Depends on:** MCP-04

## Files
- `rt4/mcp/tools/ScreenshotTool.java`

## Tool: `get_screenshot(scale=0.5, region?)`
- `scale` ranges 0.25–1.0, default 0.5, to keep image tokens down.
- `region` is `{x, y, w, h}` in canvas px, for cropping (e.g. just the chatbox).
- Returns MCP `image` content (PNG, base64) plus a text line `{ width, height, scale }`, so the agent can map pixels back for `mouse_click`.

## Capture (game thread, after the frame is drawn)
- **Software mode:** copy `SoftwareRaster.pixels` (ARGB int[], `SoftwareRaster.java:22`) into a `BufferedImage.TYPE_INT_RGB` using the frame buffer width and height.
- **HD/GL mode** (`GlRenderer.enabled`): `GlRenderer.readPixels()` (`GlRenderer.java:221`) already does `glReadPixels(… GL_BGRA …)`; the TakeScreenshot plugin uses it.
  It must run while the GL context is current, which is inside the render pass. Take the request through a flag the render path checks (`GlRenderer` end of frame, around `:209`), and complete the future there.
  GL rows come out bottom-up, so flip vertically.
- Scale with `Graphics2D.drawImage` and bilinear interpolation, then encode with `ImageIO.write(img, "png", baos)`.
- Size guard: if the base64 exceeds 1.5 MB, retry once at half scale.

## Acceptance criteria
- Works in both software and HD mode, and the image matches the window.
- A default call returns under 300 KB for a 765×503 fixed-mode client.

## Tests
- Pure helpers: crop/scale math, ARGB→RGB copy on a synthetic int[], GL vertical flip.
