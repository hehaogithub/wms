Ext.define('AM.controller.demoController',
 {

    extend:'Ext.app.Controller',

        //将Viewport.js添加到控制器
     views: ['Viewport','menuTree','contextMenu'],
     stores: ['menuStore'],
     model: ['menuModel'],
     //通过init函数来监听视图事件，控制视图与控制器的交互
     init:function() {
    	 //init函数通过this.control来负责监听
    	 var tree = null;
         this.control({
             //被监听的组件的别名
             'menutree': {
                 //监听鼠标点击事件，点击后调用changePage方法
                 itemclick:this.changePage,
               //监听鼠标右键事件，点击后调用contextMenu方法
                 itemcontextmenu: this.contextMenu,
                 
                 render:function(t, eOpts){
                     tree = t;
                 },
                 beforeitemexpand:function(node, eOpts ){
                     Ext.apply(tree.getStore().proxy.extraParams,{
                         id:node.data.id
                     });
                     //tree.getStore().load();
                 }
             }
         });
     },
     changePage:function(view, rec, item, index, e){
    	 var title = rec.get('text');
         var leaf = rec.get('leaf');
         var url=rec.raw.url;
        
         var tabPanel = Ext.getCmp('mainContent');
         //子节点才能打开，父节点不设置响应
         if(leaf==false){
             return;
         }
         //以title值设置为tab的id,打开时，有就使tab active，无则新建tab
         var newTab = tabPanel.getChildByElement(title);
         if (newTab == null) {
             tabPanel.add({
                 id: title,
                 title: title,
                 html: '<iframe frameborder="0" width="100%" height="100%" src="'+url+'"  scrolling="no" id="myiframe"></iframe>',
                
                 closable: true
             });
         }
         tabPanel.setActiveTab(title);
     },
   //显示右键菜单方法
     contextMenu:function(tree, record, item, index, e, eOpts){
         //阻止浏览器默认右键事件
         e.preventDefault();
         e.stopEvent();
         //显示右键菜单
         var view = Ext.widget('contextmenu');
         view.showAt(e.getXY());
     }

});