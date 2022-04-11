![icon](https://user-images.githubusercontent.com/37855219/162797793-e483ce7e-91aa-4037-aa6d-5de7fee3b3fe.png)
[![icon](https://images-ext-1.discordapp.net/external/pYzZHZYp1-Y_B4rfTKgrJgD-qsjkHEEkMpegvuphQ_E/https/raw.githubusercontent.com/Prospector/badges/master/modrinth-badge-72h-padded.png)](https://modrinth.com/mod/ftlogwysu)
# For the Love of God Would You Shut Up
Reverts the change made in 22w13a where noteblocks make sound even when there is a block ontop of them.

This mod seeks to make redstone less annoying, it disables sounds from noteblocks when there is something ontop of them.

Note Blocks will still notify allay/sculk despite not making a sound.

## Video
[![Youtube Video Trailer of Mod](https://user-images.githubusercontent.com/37855219/162813520-fa8d40c2-8c3f-4b66-a8d1-37c28eec525a.png)](http://www.youtube.com/watch?v=5xwHkLPgvtQ "Video Title")

## Code Change explained
Originally, the code for playing a note looked like this:
```java
private void playNote(World world, BlockPos pos) {
    if (world.getBlockState(pos.up()).isAir()) {
        world.addSyncedBlockEvent(pos, this, 0, 0); // plays sound
    }
}
```
22w13a added a GameEvent for noteblocks being played and made it so noteblocks are only disabled if they have wool or carpet ontop of them
```java
private void playNote(@Nullable Entity entity, World world, BlockPos pos) {
    BlockState blockState = world.getBlockState(pos.up());
    if (blockState.isIn(BlockTags.WOOL) || blockState.isIn(BlockTags.WOOL_CARPETS)) {
        return;
    }
    world.addSyncedBlockEvent(pos, this, 0, 0); // plays sound
    world.emitGameEvent(entity, GameEvent.NOTE_BLOCK_PLAY, pos); // notifies allay, warden, and skulk sensors
}
```
This mod makes the following changes to that code*\
<sub><sup>*it actually turns `world.addSyncedBlockEvent` into a method that has the if statement</sup></sub>
```diff
private void playNote(@Nullable Entity entity, World world, BlockPos pos) {
    BlockState blockState = world.getBlockState(pos.up());
    if (blockState.isIn(BlockTags.WOOL) || blockState.isIn(BlockTags.WOOL_CARPETS)) {
        return;
    }
+   if (world.getBlockState(pos.up()).isAir()) {
+       world.addSyncedBlockEvent(pos, this, 0, 0); // plays sound
+   }
-   world.addSyncedBlockEvent(pos, this, 0, 0);
    world.addSyncedBlockEvent(pos, this, 0, 0);
    world.emitGameEvent(entity, GameEvent.NOTE_BLOCK_PLAY, pos); // notifies allay, warden, and skulk sensors
}
```
Because this mod only seeks to make redstone less annoying, it just disables sounds from noteblocks when there is something ontop of them.

The functionality for notifying allay/sculk remains intact.

## License
This project is under the [Unlicense](https://unlicense.org/)
