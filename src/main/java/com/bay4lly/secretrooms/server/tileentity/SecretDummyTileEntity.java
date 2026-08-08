package com.bay4lly.secretrooms.server.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SecretDummyTileEntity extends SecretTileEntity {

    public SecretDummyTileEntity(BlockPos pos, BlockState state) {
        super(SecretTileEntities.SECRET_DUMMY_TILE_ENTITY.get(), pos, state);
    }
}
