package com.ss.editor.model.undo.impl;

import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import com.ss.editor.model.undo.editor.ModelChangeConsumer;
import com.ss.editor.model.undo.impl.AbstractEditorOperation;

import org.jetbrains.annotations.NotNull;

/**
 * The implementation of the {@link AbstractEditorOperation} to add a control to a node.
 *
 * @author JavaSaBr
 */
public class AddControlOperation extends AbstractEditorOperation<ModelChangeConsumer> {

    /**
     * The new control.
     */
    @NotNull
    private final Control newControl;

    /**
     * The parent.
     */
    @NotNull
    private final Spatial spatial;

    /**
     * Instantiates a new Add control operation.
     *
     * @param newControl the new control
     * @param spatial    the spatial
     */
    public AddControlOperation(@NotNull final Control newControl, @NotNull final Spatial spatial) {
        this.newControl = newControl;
        this.spatial = spatial;
    }

    @Override
    protected void redoImpl(@NotNull final ModelChangeConsumer editor) {
        EXECUTOR_MANAGER.addJmeTask(() -> {
            spatial.addControl(newControl);
            EXECUTOR_MANAGER.addFxTask(() -> editor.notifyFxAddedChild(spatial, newControl, -1, true));
        });
    }

    @Override
    protected void undoImpl(@NotNull final ModelChangeConsumer editor) {
        EXECUTOR_MANAGER.addJmeTask(() -> {
            spatial.removeControl(newControl);
            EXECUTOR_MANAGER.addFxTask(() -> editor.notifyFxRemovedChild(spatial, newControl));
        });
    }
}
