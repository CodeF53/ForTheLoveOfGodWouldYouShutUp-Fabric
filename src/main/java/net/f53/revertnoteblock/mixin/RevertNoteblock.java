package net.f53.revertnoteblock.mixin;

import net.f53.revertnoteblock.initRevertNoteblock;
import net.minecraft.block.Block;
import net.minecraft.block.NoteBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(NoteBlock.class)
public class RevertNoteblock {
	@Redirect(method = "playNote(Lnet/minecraft/entity/Entity;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V", at = @At(value = "INVOKE", target = "net/minecraft/world/World.addSyncedBlockEvent (Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;II)V"))
	private void init(World instance, BlockPos pos, Block block, int type, int data) {
		if (instance.getBlockState(pos.up()).isAir()) {
			instance.addSyncedBlockEvent(pos, block, 0, 0);
		}
	}
}
