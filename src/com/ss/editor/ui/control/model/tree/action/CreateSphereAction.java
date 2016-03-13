package com.ss.editor.ui.control.model.tree.action;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Sphere;
import com.ss.editor.Messages;
import com.ss.editor.ui.control.model.tree.ModelNodeTree;
import com.ss.editor.ui.control.model.tree.node.ModelNode;
import com.ss.editor.ui.control.model.tree.node.ModelNodeFactory;

/**
 * Действие по созданию нового примитива "сфера".
 *
 * @author Ronn
 */
public class CreateSphereAction extends AbstractNodeAction {

    public CreateSphereAction(final ModelNodeTree nodeTree, final ModelNode<?> node) {
        super(nodeTree, node);
    }

    @Override
    protected String getName() {
        return Messages.MODEL_NODE_TREE_ACTION_CREATE_PRIMITIVE_SPHERE;
    }

    @Override
    protected void process() {
        EXECUTOR_MANAGER.addEditorThreadTask(() -> {

            final AssetManager assetManager = EDITOR.getAssetManager();

            final Geometry geometry = new Geometry("Sphere", new Sphere(30, 30, 1));
            geometry.setMaterial(new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md"));

            final ModelNode<Geometry> newNode = ModelNodeFactory.createFor(geometry);
            final ModelNode<?> modelNode = getNode();
            modelNode.add(newNode);

            EXECUTOR_MANAGER.addFXTask(() -> {
                final ModelNodeTree nodeTree = getNodeTree();
                nodeTree.notifyAdded(modelNode, newNode);
            });
        });
    }
}