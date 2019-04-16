<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/ExtJsStudy/Demo/ExtJs4/resources/css/ext-all.css">
<link rel="stylesheet" type="text/css" href="/ExtJsStudy/image.css">
<script type="text/javascript" src="/ExtJsStudy/Demo/ExtJs4/ext-all.js"></script>
<title>复选树</title>
<script type="text/javascript">
	Ext.onReady(function(){
		
        var treeStore= Ext.create('Ext.data.TreeStore', {
           
            proxy: {
            	type:'ajax',
                url:'resource.do?reqCode=getResourceTree',
                reader:'json',
            },
            root: {
                text: '根',
                id: '0',
                expanded: true,
                autoLoad:true,
               
                
           },
           folderSort: true,
           sorters: [{
        	      property: 'id',
        	      direction: 'ASC'
        	}]
        
        });
	
       
	  
	   //树面板
       var treePanel = Ext.create('Ext.tree.Panel',{	
	   id:'tree',
	   border:0,
	   rootVisible: false,	
	   store:treeStore,
	   listeners:{
		     checkchange:function(node,checked){
		    	 childChecked(node,checked);

	             parentChecked(node,checked);
		     }
	   },
	   buttons : [ {
			text : '保存',
			id : 'modify',
			handler : function() {
				saveNode();

			}
		}]
	 
	   
	   
        });
       treePanel.expandAll();
       //半选
       function parentChecked(node, checked, opts){
    	     
    	       var upNode = node.parentNode;//获取子节点的父节点
    	       if(upNode != null){
    	           var opts = {};
    	           opts["isPassive"] = true;
    	          
    	           var upChecked = upNode.data.checked;

    	           //选中状态，遍历父节点，判断有父节点下的子节点是否都全选
    	           if(checked){
    	              

    	               upNode.set('checked', true);//updateInfo()有看到这个方法,但在这不行
    	              
    	           }else{
    		          var allNoChecked = true;
    		             upNode.eachChild(function (child) {
    	    	                   if(child.data.checked){//如果有一个子节点被选，父节点选中
    	    	                     allNoChecked=fasle;
    	    	                     return false ;
    	    	                   }
    	    	               });
    		             
    		             
    	                 upNode.set('checked', !allNoChecked);
    	           }
    	       }
    	   }
    	   //全选
    	   function childChecked(node,checked){
    	     
    	       checked?node.expand():node.collapse();
    	       if(node.hasChildNodes()){
    	           node.eachChild(function(n) {
    	               
    	            
    	               n.set('checked', checked)
    	              
    	               if(n.hasChildNodes()){
    	                   childChecked(n,checked);
    	               }
    	           });
    	       }
    	   }
    	   function saveNode(){
    		
    		   var id='';
    		   var i=0;
    		   var a = Ext.getCmp('tree').getChecked();
    		   Ext.each(a, function (node) {
               id=id+node.data.id+' ';
             
                });
    			Ext.Ajax.request({
					url :'role.do?reqCode=editRoleResource',
					params :{
						'nodeids' : id
						
					},
					method : 'post',
					success : function() {
						Ext.MessageBox.show({
							title : "提示",
							msg : "授权成功!"
						// icon: Ext.MessageBox.INFO
						});
						
					}
				});
    		alert(id)  ; 
    	   }
    	  
	    //显示Window
	    Ext.create('Ext.window.Window', {
	        title: '可选择树展示',
	        height: 400,
	        width: 300,
	        layout: 'fit',
	        items: [
	        	treePanel
	        ]  
	    }).show();
	});
</script>
</head>
<body>

</body>
</html>