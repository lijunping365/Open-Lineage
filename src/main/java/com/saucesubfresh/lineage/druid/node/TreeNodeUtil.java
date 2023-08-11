package com.saucesubfresh.lineage.druid.node;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 树结构相关操作工具类
 * </p>
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:46
 */
public class TreeNodeUtil {
    private TreeNodeUtil() {
    }

    /**
     * 返货所有叶子节点
     *
     * @param root Tree
     * @param <T>  节点类型
     * @return List<T>
     */
    public static <T> List<T> searchTreeLeafNodeList1(TreeNode<T> root) {
        List<T> list = new ArrayList<>();
        traverseNodeLineageTree1(root, list);
        return list;
    }

    /**
     * 返回叶子列表
     *
     * @param root           TreeNode<TableNode>
     * @param sourceNodeList List<TableNode>
     */
    private static <T> void traverseNodeLineageTree1(TreeNode<T> root, List<T> sourceNodeList) {
        if (root.isLeaf()) {
            sourceNodeList.add(root.getValue());
        } else {
            root.getChildList().forEach(node -> traverseNodeLineageTree1(node, sourceNodeList));
        }
    }

    /**
     * 返货所有叶子节点
     *
     * @param root Tree
     * @param <T>  节点类型
     * @return List<T>
     */
    public static <T> List<TreeNode<T>> searchTreeLeafNodeList(TreeNode<T> root) {
        List<TreeNode<T>> list = new ArrayList<>();
        traverseNodeLineageTree(root, list);
        return list;
    }

    /**
     * 返回叶子列表
     *
     * @param root           TreeNode<TableNode>
     * @param sourceNodeList List<TableNode>
     */
    private static <T> void traverseNodeLineageTree(TreeNode<T> root, List<TreeNode<T>> sourceNodeList) {
        if (root.isLeaf()) {
            sourceNodeList.add(root);
        } else {
            root.getChildList().forEach(node -> traverseNodeLineageTree(node, sourceNodeList));
        }
    }
}
