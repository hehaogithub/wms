package com.hh.utils;

/**
 * @Auther: HeJD
 * @Date: 2018/7/6 14:44
 * @Description:
 */
 
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.hh.domain.ComboxTree;
 
/**
 * Created by Massive on 2016/12/26.
 */
public class TreeBuilder {
 
    /**
     * 两层循环实现建树
     * @param ComboxTrees 传入的树节点列表
     * @return
     */
    public static List<ComboxTree> bulid(List<ComboxTree> ComboxTrees) {
 
        List<ComboxTree> trees = new ArrayList<ComboxTree>();
 
        for (ComboxTree ComboxTree : ComboxTrees) {
 
            if (0==ComboxTree.getPid()) {
                trees.add(ComboxTree);
            }
 
            for (ComboxTree it : ComboxTrees) {
                if (it.getPid() == ComboxTree.getId()) {
                    if (ComboxTree.getChildren() == null) {
                        ComboxTree.setChildren(new ArrayList<ComboxTree>());
                    }
                    ComboxTree.getChildren().add(it);
                }
            }
        }
        return trees;
    }
 
    /**
     * 使用递归方法建树
     * @param ComboxTrees
     * @return
     */
    public static List<ComboxTree> buildByRecursive(List<ComboxTree> ComboxTrees) {
        List<ComboxTree> trees = new ArrayList<ComboxTree>();
        for (ComboxTree ComboxTree : ComboxTrees) {
            if (0==ComboxTree.getPid()){
            trees.add(findChildren(ComboxTree,ComboxTrees));
        }
    }
        return trees;
    }
 
    /**
     * 递归查找子节点
     * @param ComboxTrees
     * @return
     */
    public static ComboxTree findChildren(ComboxTree ComboxTree,List<ComboxTree> ComboxTrees) {
        for (ComboxTree it : ComboxTrees) {
            if(ComboxTree.getId()==it.getPid()) {
            if (ComboxTree.getChildren() == null) {
                ComboxTree.setChildren(new ArrayList<ComboxTree>());
            }
            //是否还有子节点，如果有的话继续往下遍历，如果没有则直接返回
            ComboxTree.getChildren().add(findChildren(it,ComboxTrees));
 
        }
    }
        return ComboxTree;
    }
 
 
 
    public static void main(String[] args) {
 
        ComboxTree ComboxTree1 = new ComboxTree(1,"广州",0);
        ComboxTree ComboxTree2 = new ComboxTree(2,"深圳",0);
 
        ComboxTree ComboxTree3 = new ComboxTree(3,"天河区",1);
        ComboxTree ComboxTree4 = new ComboxTree(4,"越秀区",1);
        ComboxTree ComboxTree5 = new ComboxTree(5,"黄埔区",1);
        ComboxTree ComboxTree6 = new ComboxTree(6,"石牌",3);
        ComboxTree ComboxTree7 = new ComboxTree(7,"百脑汇",6);
 
 
       
 
 
        List<ComboxTree> list = new ArrayList<ComboxTree>();
 
        list.add(ComboxTree1);
        list.add(ComboxTree2);
        list.add(ComboxTree3);
        list.add(ComboxTree4);
        list.add(ComboxTree5);
        list.add(ComboxTree6);
        list.add(ComboxTree7);
       
 
        //第一种方式
        List<ComboxTree> trees = TreeBuilder.bulid(list);
        System.out.println(JSONObject.toJSONString(trees));
 
        //第二种方式
        List<ComboxTree> trees_ = TreeBuilder.buildByRecursive(list);
       
        
 
    }
 
}
 