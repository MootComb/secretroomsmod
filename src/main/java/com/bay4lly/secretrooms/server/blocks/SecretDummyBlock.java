package com.bay4lly.secretrooms.server.blocks;

import com.bay4lly.secretrooms.server.tileentity.SecretDummyTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class SecretDummyBlock extends SecretBaseBlock {

    public SecretDummyBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new SecretDummyTileEntity(pos, state);
    }

    @Override
    public Boolean getSolidValue() {
        return false;
    }
}
