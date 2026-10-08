# MCP-30 — 🐛 A slot target on a component that is not an inventory reported thousands of slots

**Type:** bug · **Severity:** medium · **Depends on:** MCP-09

**Goal:** A slot target that names no real inventory slot is refused with a message that says what is
wrong, so an agent stops trying indexes that cannot exist.

## Problem

`MenuSynth.populateComponent` (`client/src/main/java/rt4/mcp/MenuSynth.java`) accepts a slot target with

```java
int slotCount = Math.max(0, component.baseWidth * component.baseHeight);
if (component.type != 2 || target.slot >= slotCount) {
    throw new ToolException("no slot " + target.slot + " in " + target.format()
            + "; this component has " + slotCount + " slots");
}
```

Two different refusals share one message, and `slotCount` is the component's pixel area. For a component
that is **not** an inventory (`type != 2`) that product is not a slot count at all:

- the emotes tab icon `if:548:25` answered *"this component has 1188 slots"* (33 × 36);
- the trailer panel `if:216:0` answered *"this component has 268664881 slots"*.

Both read as "the slots exist, you picked the wrong index". On 2026-10-08 the `agent012` run spent turns
236–285 doing exactly that: `do_action(op="Emote", target="if:216:N")` and then `if:216:0:N` and
`if:216:1:N`, incrementing `N` against a component that has no slots of any kind.

The emote buttons were never in interface 216 — they are interface 464's `if:464:N` components, and 216 is
a two-label dev interface. The agent reached 216 by guessing a number from another game; the harness's
`get_interfaces` ordering bug that hid 464 is fixed separately (`2009scape-agent`,
`scape_agent/game/panels.py`). This ticket is the second half: the refusal should have stopped the guessing
on the first try.

## Files

- `client/src/main/java/rt4/mcp/MenuSynth.java`
- `client/src/test/java/rt4/mcp/MenuSynthTest.java`

## Implementation

- Move the message into a public static pure `MenuSynth.slotRefusal(ComponentTarget target, int type, int slots)`.
- `type != 2`: name the component, and say what to ask instead —
  `if:548:25 is not an inventory, so if:548:25:0 names no slot; list_actions on if:548:25 shows what it offers`.
- `type == 2`: keep the old shape, which is only now true —
  `no slot 30 in if:149:0:30; this inventory has 28 slots`.

## Acceptance criteria

- On the live client, `do_action(target="if:216:0:0", op="Emote")` returns the not-an-inventory message,
  not a slot count.
- `do_action` on a real out-of-range inventory slot still names the inventory's size.

## Tests

- `MenuSynthTest`: the not-an-inventory message and the out-of-range inventory message. Both are pure, so
  they need no game cache — the plane-guard tests already there have the same shape.
