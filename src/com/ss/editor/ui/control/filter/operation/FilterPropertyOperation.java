package com.ss.editor.ui.control.filter.operation;

import com.ss.editor.model.undo.editor.SceneChangeConsumer;
import com.ss.editor.model.undo.impl.AbstractEditorOperation;
import com.ss.editor.ui.component.editor.impl.scene.SceneFileEditor;
import com.ss.editor.ui.control.property.operation.AbstractPropertyOperation;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The implementation of the {@link AbstractEditorOperation} to edit properties of filters in the {@link
 * SceneFileEditor}.
 *
 * @author JavaSaBr
 */
public class FilterPropertyOperation<D, T> extends AbstractPropertyOperation<SceneChangeConsumer, D, T> {

    public FilterPropertyOperation(@NotNull final D target, @NotNull final String propertyName, @Nullable final T newValue,
                                   @Nullable final T oldValue) {
        super(target, propertyName, newValue, oldValue);
    }

    @Override
    protected void redoImpl(@NotNull final SceneChangeConsumer editor) {
        EXECUTOR_MANAGER.addEditorThreadTask(() -> {
            apply(target, newValue);
            EXECUTOR_MANAGER.addFXTask(() -> editor.notifyChangeProperty(null, target, propertyName));
        });
    }

    @Override
    protected void undoImpl(@NotNull final SceneChangeConsumer editor) {
        EXECUTOR_MANAGER.addEditorThreadTask(() -> {
            apply(target, oldValue);
            EXECUTOR_MANAGER.addFXTask(() -> editor.notifyChangeProperty(null, target, propertyName));
        });
    }
}