package com.ss.editor.ui.control.model.tree.action.emitter;

import com.ss.editor.ui.control.model.tree.ModelNodeTree;
import com.ss.editor.ui.control.model.tree.node.ModelNode;

import org.jetbrains.annotations.NotNull;

import tonegod.emitter.ParticleEmitterNode;
import tonegod.emitter.influencers.GravityInfluencer;
import tonegod.emitter.influencers.ParticleInfluencer;

/**
 * The action for creating the {@link GravityInfluencer} for the {@link ParticleEmitterNode}.
 *
 * @author JavaSaBr
 */
public class CreateGravityParticleInfluencerAction extends AbstractCreateParticleInfluencerAction {

    public CreateGravityParticleInfluencerAction(@NotNull final ModelNodeTree nodeTree, @NotNull final ModelNode<?> node) {
        super(nodeTree, node);
    }

    @NotNull
    @Override
    protected String getName() {
        return "Gravity influencer";
    }

    @NotNull
    @Override
    protected ParticleInfluencer createInfluencer() {
        return new GravityInfluencer();
    }
}