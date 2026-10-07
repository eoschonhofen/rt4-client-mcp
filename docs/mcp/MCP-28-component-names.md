# MCP-28 — Components say what they are, not just where they are

**Goal:** Every component an agent reads carries the name of the thing it is, so a spellbook is a
list of spells and not a list of nameless sprites.

**Depends on:** MCP-09

## Why

`get_interfaces` reported `text`, `ops`, `button`, `obj` and `slots`. A spell, a prayer and a
sidebar tab have none of those except an op, and the thing that names them — `optionBase` — was
never read. `MiniMenu.addComponentEntries` passes `optionBase` as the subject of every entry it
adds (`MiniMenu.java:349`, `:354`, `:359`), so the line the player sees is `op` + `optionBase`:
"Cast" + "Wind Strike". Only the first half reached the agent.

Worse, a component with nothing but a name failed the "is this worth reporting" test and was
dropped, so a read of the spellbook came back with its spells missing altogether. An agent told
"cast a spell on it" could see the panel open and nothing in it, which is exactly what one
measured run did for thirty steps.

Two smaller omissions came out of the same read:

- **The use verb.** `optionCircumfix` is the verb for using a component on something else
  ("Cast", "Use"), read by `MiniMap.getTargetVerb` and added to the menu as an op. It was in no
  field of the dump, so the one op that makes a spell castable was invisible.
- **The button's op.** `buttonType` was reported as a kind (`ok`, `continue`), but not as
  something to call: the op is `option` for button types 1, 4, 5 and 6, the localized `Close`
  for type 3, and the localized `Continue` for a resume-pause component. Agents guessed from
  the words instead, which is how "Click here to continue" gets pressed with `Click`.

## Implementation

`rt4/mcp/InterfaceWalker.java`:

- `name(Component)` → `optionBase`, trimmed, null when empty. Emitted as `name`.
- `useVerb(Component)` → `optionCircumfix` when the component has a target mask, appended to
  `ops`. Read the way `getTargetVerb` reads it, minus its `Cheat.qaOpTest` branch: that branch
  exists to show QA a hidden option, and an agent is not QA.
- `buttonOp(Component)` → the op `addComponentEntries` would name: `option` for button types
  1, 4, 5 and 6 (the ones that decode it), the localized `Close` for type 3, the localized
  `Continue` for a resume-pause component. Appended to `ops`, since that is what it is. Any
  other component carries `option`'s default, so naming it would invent an "Ok" that nothing
  offers.
- `name` is `optionSuffix` for a select button (type 2) and `optionBase` for everything else,
  which is the pairing `addComponentEntries` uses for each.
- `name` counts towards a component being worth reporting, in both the live walk and the pure
  `isActionable` filter.

Nothing is asked of the client that was not asked before: these are three fields of a component
already in hand.

`rt4/mcp/InterfaceNames.java` plus `client/src/main/resources/rt4/mcp/interface-names.tsv`:

- An interface reached the agent as a bare number. The cache's own interface names live in
  `org.rs09.consts.Components`, the constant table the server project already depends on; the
  client cannot depend on that jar, so `scripts/gen-interface-names.py` extracts the 508 names
  once and the table is committed as a resource. Regenerate it with the script, never by hand.
- `describeInterface` adds `name` when the table has one. An id it does not have is reported
  without a name: nothing is invented, and a missing or broken resource leaves the table empty
  rather than failing the client.

## Acceptance criteria

- `get_interfaces(interface_id=192)` comes back as `{ id: 192, name: "MAGIC", … }` and lists
  every spell by name, each with `Cast` among its ops.
- A prayer in interface 271 reads with its name.
- A dialogue's continue button reads `{ button: "continue", ops: ["Continue"] }`, so the op is
  read rather than guessed from the words beside it.
- A component with no name, text, ops, button, obj or slots is still left out.

## Tests

- `InterfaceWalkerTest` — a component carrying only a name survives the actionable filter.
- `InterfaceNamesTest` — the generated table answers for the panels an agent meets, invents no
  name for an id it does not have, and skips comments, blanks and malformed rows.
- The live traversal stays as untestable as the rest of `describe`: it reads static client state.
  Driven through the real client instead, per the workspace rule that the game is the oracle.
