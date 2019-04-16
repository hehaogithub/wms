Ext.onReady(function() {

	Ext.define('Role', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'role_id',
			type : 'int'
		}, {
			name : 'role_name',
			type : 'string'
		}],

	});

	var roleStore = Ext.create('Ext.data.Store', {
		model : 'Role',
		proxy : {
			type : 'ajax',
			url : 'role.do?reqCode=getRoleList',
			reader : {
				type : 'json',
				root : 'role',
				totalProperty : 'total',
				
			}
		},
		autoLoad : false,
		pageSize : 3
	});
	roleStore.load({
		params : {
			start : 0,
			limit : 3,
			page : 1
		}
	});
	// 修改信息面板
	var editpanel = Ext.create('Ext.form.Panel', {
		id : 'addRoleFormPanel',
		name : 'addRoleFormPanel',
		bodyStyle:'padding:5px 5px 0',
        fieldDefaults: {
            msgTarget: 'side',
            labelWidth: 75
        },
        defaultType: 'textfield',
        defaults: {
            anchor: '100%'
        },
		//cls:'addr-panel',
		frame :false,
        items : [ {
        	xtype : 'textfield',
			id : 'repertory_name',
			fieldLabel : '角色名',
			
			name : 'repertory_name',
			emptyText : '请输入账户',
			allowBlank : false,
			blankText : '仓库名不能为空',
			anchor : '99%'
		}, {
			xtype : 'textareafield',
			fieldLabel : '备注',
			id : 'remark',
			name : 'remark',
			allowBlank : false,
			emptyText : '请输入仓库描述信息',
			blankText : '描述信息不能为空',
			anchor : '99%'
		} ],

		buttons : [ {
			text : '修改',
			id : 'modify',
			handler : function() {
				var form = this.up('form').getForm();
				if (Ext.getCmp('modify').getText() == '保存') {
					add(form);
				} else {
					modify(form);
				}

			}
		}, {
			text : '关闭',
			handler : function() {
				this.up('form').getForm().reset();
				editwindow.hide();
			}
		} ]
	});

	var editwindow = Ext.create('widget.window', {
		id : 'editW',
		border:false,
		width: 400,
    	bodyStyle:'padding:0px',
        height: 200,
		title : '<span class="commoncss">新增角色</span>',
		closable : true,
		closeAction : 'hide',
		plain:true,
		modal:true,
		layout: 'fit',
	    items : [ editpanel ]
	});

	var selModel = Ext.create('Ext.selection.CheckboxModel',{
		handleMouseDown : Ext.emptyFn
	});

	var grid = Ext.create('Ext.grid.Panel', {
		id : 'grid',
		title : '角色信息表',
		frame : true,
		region : 'center',
		margins : '3 3 3 3',
		store : roleStore,
		selModel : selModel,
		columns : [ {
			header : '授权',
			dataIndex : 'role_id',
			width : 70,
			renderer: function(value, cellmeta, record, rowIndex, columnIndex, store) { 
				 var roleid=record.data.role_id;
	             return '<a href="javascript:void(0);" onclick="authorized('+roleid+')"><img src="images/edit1.png"/></a>';
	            
	          }
		},{
			header : '角色编号',
			dataIndex : 'role_id',
			width : 300,
		}, {
			header : '角色名',
			dataIndex : 'role_name',
			width : 300
		}],
		dockedItems : [ {
			xtype : 'toolbar',
			items : [
					{
						text : '添加',
						iconCls : 'add',
						handler : function() {
							Ext.getCmp('editW').setTitle('添加角色信息');
							Ext.getCmp('modify').setText('保存');
							editwindow.show();

						}
					},
					'-',
					{
						text : '修改',
						iconCls : 'modify',
						handler : function() {
							var record = grid.getSelectionModel()
									.getSelection();
							if (record.length == 0) {
								Ext.MessageBox.show({
									title : "提示",
									msg : "请先选择您要操作的行!"
								// icon: Ext.MessageBox.INFO
								})
								return;
							} else {

								Ext.getCmp('editW').setTitle('修改角色信息');
								Ext.getCmp('modify').setText('修改');
								Ext.getCmp("role_name").setValue(
										record[0].data.repertory_name);
								
								editwindow.show();

							}

						}
					},
					'-',
					{
						text : '删除',
						iconCls : 'delete',
						handler : function() {
							deleteRepertory();
							

						}
					},
					'->',
					{
						xtype : 'textfield',
						id : 'searchname',
						fieldLabel : '角色名',
						labelWidth : 50,
						name : 'role_name',
						width : 150
					},
					{
						text : '查询',
						iconCls : 'search',
						handler : function() {
							var name = Ext.getCmp('searchname').value;
						
							if (name == null) {
								Ext.Msg.alert('提示信息', '请输入查询条件');
								return;

							} else {

								roleStore.on('beforeload', function(
										repertoryStore, options) {
									var new_params = {
									
										role_name : name
									};
									Ext.apply(roleStore.proxy.extraParams,
											new_params);
								});
								grid.getStore().getProxy().url='role.do?reqCode=getRepertoryListByKey'
								

							}
							Ext.getCmp("toolbar").moveFirst();
						}

					},
					'-',
					{
						text : '刷新',
						iconCls : 'refresh',
						handler : function() {
							
							grid.getStore().getProxy().extraParames = null;
							grid.getStore().getProxy().url ='role.do?reqCode=getRoleList'; 
						    Ext.getCmp("toolbar").moveFirst();

						}
					} ],

		} ],
		
		bbar : Ext.create('Ext.PagingToolbar', {
			id : 'toolbar',
			store : roleStore,
			displayInfo : true,
			displayMsg : '显示数据 {0} - {1} of {2}',
			emptyMsg : "没有任何数据",

		}),
		

	});
	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [grid]
	});
	// 添加记录
	function add(form) {
		if (form.isValid()) {
			form.submit({
				url : 'role.do?reqCode=addRole',
				method : 'POST',
				waitMsg : '正在添加，请稍后',
				success : function(form, action) {
					editwindow.hide();
					form.reset();
					Ext.getCmp("toolbar").doRefresh();

				},
				failure : function(form, action) {
					var data = action.response.responseText;
					Ext.Msg.alert('保存失败', data);
				}
			});
		}

	};
	// 修改记录
	function modify(form) {
		if (form.isValid()) {
			form
					.submit({
						url : 'role.do?reqCode=updateRepertory',
						params : {
							'role_id' : grid.getSelectionModel()
									.getSelection()[0].data.repertory_id
						},
						method : 'POST',
						waitMsg : '正在修改，请稍后',
						success : function(form, action) {
							editwindow.hide();
							form.reset();
							Ext.getCmp("toolbar").doRefresh();

						},
						failure : function(form, action) {
							var data = action.response.responseText;
							Ext.Msg.alert('修改失败', data);
						}
					});
		}

	};
	//删除记录
    function deleteRole(){
    	var record = grid.getSelectionModel()
		.getSelection();
    if (record.length == 0) {
	 Ext.MessageBox.show({
		title : "提示",
		msg : "请先选择您要删除的行!"
	// icon: Ext.MessageBox.INFO
	})
	return;
   }else{
	   Ext.MessageBox.confirm('信息确认', '你确定要删除该条记录吗', function(btn){
		    if (btn == 'yes'){
		    	Ext.Ajax.request({
					url :'repertory.do?reqCode=deleteRepertory',
					params :{
						'role_id' : grid.getSelectionModel()
						.getSelection()[0].data.role_id
					},
					method : 'post',
					success : function() {
						Ext.MessageBox.show({
							title : "提示",
							msg : "删除成功!"
						// icon: Ext.MessageBox.INFO
						});
						Ext.getCmp("toolbar").doRefresh();
					}
				});


		    	
		    }
	   
	   
   });
   }
   };
   authorized =function(roleid){
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
    	}],
    	listeners : {
			beforeload : function(store, operation) {
				
				var new_params = {//参数
					rid : roleid
				};
				Ext.apply(store.proxy.extraParams, new_params);// 通过extraParams传递
			}
		}

    
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
				saveNode(roleid);

			}
		}]
	 
	   
	   
     });
    treePanel.expandAll();
  //显示Window
	var win=Ext.create('widget.window', {
	        title: '可选择树展示',
	        id:'treeWin',
	        height: 400,
	        width: 300,
	        layout: 'fit',
			plain:true,
			closeAction:'close',
			closable:true,
			modal:true,
	        items: [
	        	treePanel
	        ]  
	    });
	   
	   win.show();
    
	   
}
     


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
   }//保存
   function saveNode(roleid){
		
	   var id='';
	   var i=0;
	   var a = Ext.getCmp('tree').getChecked();
	   Ext.each(a, function (node) {
       id=id+node.data.id+' ';
     
        });
		Ext.Ajax.request({
			url :'role.do?reqCode=editRoleResource',
			params :{
				'nodeids' : id,
				'roleid':roleid
				
			},
			method : 'post',
			success : function() {
				 Ext.getCmp('treeWin').close();
				
				 Ext.Msg.alert('提示信息','授权成功');
				
			}
		});
	
   }
  

});
