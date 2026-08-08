package com.bay4lly.secretrooms.client.model;

import com.bay4lly.secretrooms.client.model.quads.TrueVisionBakedQuad;
import com.bay4lly.secretrooms.server.items.TrueVisionGogglesClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class SecretInvisibleBlockModel extends SecretBlockModel {

    public SecretInvisibleBlockModel(BakedModel model) {
        super(model);
    }

    @Override
    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData extraData, @Nullable RenderType renderType) {
        var data = this.getModel(extraData);
        if (data.isEmpty()) {
            return Collections.emptyList();
        }

        BlockState mirrorState = data.get().getFirst();
        BakedModel mirrorModel = data.get().getSecond();

        Function<RenderType, List<BakedQuad>> quads = type -> this.render(mirrorState, state, mirrorModel, side, rand, extraData, type);

        if (trueVision()) {
            if (renderType == RenderType.translucent()) {
                List<BakedQuad> quadList = quads.apply(renderType);
                this.getHelmetQuads(this.gatherAllQuads(quads), quadList);
                return quadList;
            }
            return quads.apply(renderType);
        }

        return Collections.emptyList();
    }

    private boolean trueVision() {
        return TrueVisionGogglesClientHandler.isWearingGoggles(Minecraft.getInstance().player);
    }

    private void getHelmetQuads(List<BakedQuad> allQuads, List<BakedQuad> quads) {
        for (BakedQuad quad : allQuads) {
            quads.add(TrueVisionBakedQuad.generateQuad(quad));
        }
    }

    protected List<BakedQuad> gatherAllQuads(Function<RenderType, List<BakedQuad>> superQuads) {
        return new ArrayList<>(superQuads.apply(null));
    }

    protected List<BakedQuad> render(@Nullable BlockState mirrorState, @Nullable BlockState baseState, @NotNull BakedModel model, @Nullable Direction side, @NotNull RandomSource rand, @NotNull ModelData extraData, @Nullable RenderType renderType) {
        return new ArrayList<>(model.getQuads(mirrorState, side, rand, extraData, renderType));
    }
}
