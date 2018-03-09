package com.newx.staging.util;

/**
 @author: Newx
 @date: 2018/2/23
 Description: 
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class TreeUtil {

    /**
     * 将一个Node列表转换成一个多叉树
     * @param rootNodes
     */
    public static List getTree(List<Node> rootNodes) {
        List<Node> NodeList = new ArrayList<Node>();
        // 先找到所有的一级菜单
        for (int i = 0; i < rootNodes.size(); i++) {
            // 一级菜单没有parentId
            if (null==rootNodes.get(i).getParentId()) {
                NodeList.add(rootNodes.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (Node node : NodeList) {
            node.setChildNodes(getChild(node.getId(), rootNodes));
            node.setHasChild(hasChild(node));
        }
        return JSONArray.parseArray(JSON.toJSONString(NodeList));

    }

    public static List getLayerTree(String id, List<Node> rootNodes){
        List<Node> childList = new ArrayList<Node>();
        for (Node node : rootNodes) {
            if (!(null==node.getParentId())) {
                if (node.getParentId().equals(id)) {
                    childList.add(node);
                }
            }
        }
        for (Node node : childList) {
            node.setChildNodes(getLayerChild(node.getId(), rootNodes));
            node.setHasChild(hasChild(node));
        }
        return JSONArray.parseArray(JSON.toJSONString(childList));
    }

    public static List getLayerChild(String id, List<Node> rootNodes){
        List<Node> childList = new ArrayList<Node>();
        for (Node node : rootNodes) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (!(null==node.getParentId())) {
                if (node.getParentId().equals(id)) {
                    childList.add(node);
                }
            }
        }
        return childList;
    }

    /**
     * 递归查找子菜单
     *
     * @param id
     *            当前菜单id
     * @param rootNodes
     *            要查找的列表
     * @return
     */
    public static List<Node> getChild(String id, List<Node> rootNodes) {
        // 子菜单
        List<Node> childList = getLayerChild(id , rootNodes);
        // 把子菜单的子菜单再循环一遍
        for (Node node : childList) {
            node.setChildNodes(getChild(node.getId(), rootNodes));
            node.setHasChild(hasChild(node));
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    private static boolean hasChild(Node node){
        if (!(node.getChildNodes()==null)) {
            if (node.getChildNodes().size() > 0) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
