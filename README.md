# Mega Bucket

**Created by UltraOP**

Fabric mod project for Minecraft Java Edition 1.21.11.

## Planned features
- Quantity-bearing custom iron item, from 1 to 1,000,000,000.
- Capacity-bearing bucket, capped at 1,000,000,000.
- Recipe integration using three ingredient slots.
- `/mb help` and `/mb info` with grouped, colored output.
- Dropped bucket visual scale based on capacity, capped at 1–2 blocks; held item remains normal-sized.
- Water collection and world-draining mechanics.

## Build
Java 21 is required.

```bash
./gradlew build
```

The mod JAR is generated in `build/libs/`.

## Commands
- `/mb help` — command guide.
- `/mb info` — mod, Minecraft, Java, Fabric and creator information.
- `/mb give iron <amount>` — give custom-quantity iron (operator only).
- `/mb give bucket <amount>` — give a capacity bucket (operator only).
- `/mb reload` — reload configuration (operator only).

## Implementation status
This repository starts with the Fabric project scaffold and command UI. Custom inventory/recipe integration, water source removal and dropped-entity scaling still need implementation and in-game validation. Vanilla stack limits are not globally changed; custom quantity data is required to avoid breaking inventory assumptions in Minecraft and other mods.
