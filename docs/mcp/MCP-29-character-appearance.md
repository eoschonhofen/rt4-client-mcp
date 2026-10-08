# MCP-29 — The character says what it looks like

**Goal:** An agent can read back the style and colour on every part of its character, so a choice
made on a design panel is something it can see rather than guess at.

**Depends on:** MCP-06

## Why

The character designer (`CharacterDesign.java`, interface 771) hands an agent a page of choices and
says what each button *does* — `Select next hair style`, `Select colour` — and never what the
character now *is*. `PlayerAppearance` has always held the answer, and no tool read it. `get_status`
carried position, health, prayer, run, weight, what is open and what it is doing; `get_equipment`
carried what is worn. Neither said what the character looks like.

A measured run of the designer shows the cost. The head page reads:

```
Head page.
- if:771:62 Select hair colour
- if:771:92 Select previous hair style
- if:771:93 Select next hair style
- Select colour (hair): if:771:100 dark maroon, if:771:101 red, ...
```

The colours are named, so an agent picks a hair colour. The *style* is one arrow and a count of
presses nobody can see. The designer opens on the first style of the list, and the first style is
the bare head (`Appearance.flagHatClipping`: the hair look is bald at 0 for a man and 45 for a
woman), so every character an agent designed came out bald.

Two halves are needed, one on each side of the wire:

- **Server** — the designer kept the pending look in an attribute (`look-val:i`, `color-val:i`) and
  applied it only on the confirm button, so nothing the client held was current while the panel was
  open — not the model the panel draws, and not the appearance anybody could read.
  `CharacterDesign.changeLook`, `changeColor` and `changeGender` now put each choice on the
  character as it is made and sync it.
- **Client** — nothing exposed it. That is this ticket.

## Implementation

`rt4/mcp/Looks.java`:

- `of(PlayerAppearance)` → `{ female, hair, jaw, torso, arms, wrists, legs, feet, hair_colour,
  torso_colour, legs_colour, feet_colour, skin_colour }`.
- The seven slots are the ones the designer changes, named by the order the server writes them in
  (`PlayerFlags530.Appearance.writeTo`, filled by `Appearance.prepareBodyData`): hair 8, jaw 11,
  torso 4, arms 6, wrists 9, legs 7, feet 10.
- The five colours are in payload order, `Appearance.hair` first.
- `-1` means the slot holds nothing. That is not id 0: id 0 is a real head, the bare one, and
  reading it as "nothing" is the bug being fixed.

`rt4/PlayerAppearance.java` gains three reads of what it already holds — `identikitId(slot)`,
`colour(slot)` and `isFemale()` — with the flag bits stripped the way the client's own model code
strips them.

`get_status` carries it as `appearance`, present only while logged in.

No name is invented for any id. The ids are the game's own, the same numbers the server's designer
counts its styles by, and naming them belongs to whoever knows the content.

## Acceptance criteria

- `get_status.appearance.hair` is the hair identikit id, `0` for the bare head and `-1` for no head
  at all.
- On the head page, changing the hair style with `do_action(if:771:93, "Select next hair style")`
  changes `get_status.appearance.hair` by the next step of that gender's `MALE_HEAD_IDS` /
  `FEMALE_HEAD_IDS`.
- Picking a swatch changes the matching colour index.
- `get_status` stays valid logged out.

## Tests

- `LooksTest` — every part round-trips as its identikit id, an empty slot is `-1` and not the first
  style, the bare head (`0x80000000|0`) is still a style, an item's flag is not mistaken for a body
  part, the colours keep payload order and the gender is the female flag.
- The live read stays as untestable as the rest of the client's static state, driven through the
  real client instead, per the workspace rule that the game is the oracle.
