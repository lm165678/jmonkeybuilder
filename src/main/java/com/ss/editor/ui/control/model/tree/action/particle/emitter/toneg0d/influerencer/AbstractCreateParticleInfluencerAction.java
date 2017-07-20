package com.ss.editor.ui.control.model.tree.action.particle.emitter.toneg0d.influerencer;

import com.ss.editor.annotation.FXThread;
import com.ss.editor.model.node.Toneg0dParticleInfluencers;
import com.ss.editor.model.undo.editor.ModelChangeConsumer;
import com.ss.editor.ui.Icons;
import com.ss.editor.ui.control.model.tree.action.AbstractNodeAction;
import com.ss.editor.ui.control.model.tree.action.operation.particle.emitter.toneg0d.AddParticleInfluencerOperation;
import com.ss.editor.ui.control.tree.NodeTree;
import com.ss.editor.ui.control.tree.node.ModelNode;
import javafx.scene.image.Image;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tonegod.emitter.ParticleEmitterNode;
import tonegod.emitter.influencers.ParticleInfluencer;

import java.util.Objects;

/**
 * The action to create a {@link ParticleInfluencer} for a {@link ParticleEmitterNode}.
 *
 * @author JavaSaBr
 */
public abstract class AbstractCreateParticleInfluencerAction extends AbstractNodeAction<ModelChangeConsumer> {

    /**
     * Instantiates a new Abstract create particle influencer action.
     *
     * @param nodeTree the node tree
     * @param node     the node
     */
    public AbstractCreateParticleInfluencerAction(@NotNull final NodeTree<ModelChangeConsumer> nodeTree, @NotNull final ModelNode<?> node) {
        super(nodeTree, node);
    }

    @Nullable
    @Override
    protected Image getIcon() {
        return Icons.INFLUENCER_16;
    }

    @FXThread
    @Override
    protected void process() {
        super.process();

        final NodeTree<ModelChangeConsumer> nodeTree = getNodeTree();
        final ModelChangeConsumer changeConsumer = Objects.requireNonNull(nodeTree.getChangeConsumer());

        final ModelNode<?> modelNode = getNode();
        final Toneg0dParticleInfluencers element = (Toneg0dParticleInfluencers) modelNode.getElement();
        final ParticleEmitterNode emitterNode = element.getEmitterNode();
        final ParticleInfluencer influencer = createInfluencer();

        changeConsumer.execute(new AddParticleInfluencerOperation(influencer, emitterNode));
    }

    /**
     * Create influencer particle influencer.
     *
     * @return the particle influencer
     */
    @NotNull
    protected abstract ParticleInfluencer createInfluencer();
}