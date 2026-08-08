package com.bay4lly.secretrooms.server.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class SecretGateTileEntity extends SecretTileEntity {

    public SecretGateTileEntity(BlockPos pos, BlockState state) {
        super(SecretTileEntities.SECRET_GATE_TILE_ENTITY.get(), pos, state);
    }
}
