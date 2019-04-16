Ext.onReady(function() {

	Ext.define('User', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'user_id',
			type : 'int'
		}, {
			name : 'user_name',
			type : 'string'
		}, {
			name : 'user_sex',
			type : 'string'
		},{
			name : 'user_loginaccount',
			type : 'string'
		},{
			name : 'user_password',
			type : 'string'
		},{
			name : 'user_phone',
			type : 'int'
		},{
			name : 'repertory_name',
			type : 'string'
		},{
			name : 'role_name',
			type : 'string'
		}, ],

	});

	var userStore = Ext.create('Ext.data.Store', {
		model : 'User',
		proxy : {
			type : 'ajax',
			url : 'user.do?reqCode=getUserList',
			reader : {
				type : 'json',
				root : 'user',
				totalProperty :'total',
			},
			extraParams: {
				 repertoryId:0,
				 username:0
				  }
		},
		
		autoLoad : false,
		pageSize : 5
	});
	userStore.load({
		params : {
			start : 0,
			limit : 5,
			page : 1
		}
	});
	//性别store,静态
	var sexStore = Ext.create("Ext.data.Store", {
	    fields: ["Name", "Value"],
	    data: [
	        { Name: "男", Value: 1 },
	        { Name: "女", Value: 2 }
	    ]
	});
	//用户类型store,动态的
	var ustStore = Ext.create("Ext.data.Store", {
		fields: ["role_id", "role_name"],
		autoLoad : false,
        proxy: {
                 type: "ajax",
                 url: 'role.do?reqCode=getRole',
                 reader: {
                  type: "json",
                   root: "data"
                            }
                        }           });
	//仓库store,动态的
	var rpStore = Ext.create("Ext.data.Store", {
		fields: ["repertory_id", "repertory_name"],
		autoLoad : false,
        proxy: {
                 type: "ajax",
                 url: 'repertory.do?reqCode=getRepertory',
                 reader: {
                  type: "json",
                   root: "data"
                            }
                        }           });
	
	
	
	
	
	var selModel = Ext.create('Ext.selection.CheckboxModel');

	var grid = Ext.create('Ext.grid.Panel', {
		id : 'grid',
		region : 'center',
		frame : true,
		title : '用户信息表',
	
		collapsible:true,
		store : userStore,
		selModel : selModel,
		columns : [ {
			header : '用户编号',
			dataIndex : 'user_id',
			width : 100,
		}, {
			header : '用户名',
			dataIndex : 'user_name',
			width : 100
		}, {
			header : '用户性别',
			dataIndex : 'user_sex',
			width : 100
		}, {
			header : '登录账户',
			dataIndex : 'user_loginaccount',
			width : 100
		},  {
			header : '登录密码',
			dataIndex : 'user_password',
			width : 100
		}, {
			header : '联系方式',
			dataIndex : 'user_phone',
			width : 100
		},  {
			header : '所属仓库',
			dataIndex : 'repertory_name',
			width : 100
		},  {
			header : '所属角色',
			dataIndex : 'role_name',
			width : 100
		} ],
		dockedItems : [ {
			xtype : 'toolbar',
			items : [
					{
						text : '添加',
						iconCls : 'add',
						handler : function() {
							Ext.getCmp('adduserwindow').setTitle('添加用户');//将窗口标题改为添加用户
							Ext.getCmp('modify').setText('保存');//将修改改成添加
							addUserWindow.show();
						}
					},
					'-',
					{
						text : '修改',
						iconCls : 'modify',
						handler : function() {
							var record = grid.getSelectionModel()
									.getSelection();//获取选择行的数据
							if (record.length == 0) {
								Ext.MessageBox.show({
									title : "提示",
									msg : "请先选择您要操作的行!",
								    icon: 'Ext.MessageBox.INFO'
								})
								return;
							} else {
								Ext.getCmp('adduserwindow').setTitle('修改用户信息');//修改窗口标题
								Ext.getCmp('modify').setText('修改');//修改按钮标题
								Ext.getCmp("user_loginaccount").setValue(
										record[0].data.user_loginaccount);
								Ext.getCmp("remark").setValue(
										record[0].data.remark);
								editwindow.show();


							

							}

						}
					},
					'-',
					{
						text : '删除',
						iconCls : 'delete',
						handler : function() {
							deleteUser();

						}
					},
					'->',
					{
						xtype : 'textfield',
						id : 'searchname',
						fieldLabel : '仓库名',
						labelWidth : 50,
						name : 'repertory_name',
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
							 	userStore.getProxy().extraParams.username=Ext.getCmp('searchname').value;; 
					        	grid.getStore().getProxy().url ='user.do?reqCode=query'; 
								Ext.getCmp("toolbar").moveFirst();
							}
						
						}

					},
					'-',
					{
						text : '刷新',
						iconCls : 'refresh',
						handler : function() {
							
							grid.getStore().getProxy().extraParames = null;
							grid.getStore().getProxy().url ='user.do?reqCode=getUserList'; 
						    Ext.getCmp("toolbar").moveFirst();

						}
					} ],

		} ],
		
		
		bbar : Ext.create('Ext.PagingToolbar', {
			id : 'toolbar',
			store : userStore,
			displayInfo : true,
			displayMsg : '显示数据 {0} - {1} of {2}',
			emptyMsg : "没有任何数据",

		}),
		//renderTo : Ext.getBody()

	});
	var contextMenu = new Ext.menu.Menu({
		id : 'RepertoryTreeContextMenu',
		items : [{
					text : '新增人员',
					iconCls : 'page_addIcon',
					handler : function() {
						addUser();
					}
				}]
	});
	var store = Ext.create('Ext.data.TreeStore', {
        proxy: {
            type: 'ajax',
            url: 'user.do?reqCode=getRepertoryTree',
            reader:'json',
            autoLoad:true
            },
        sorters: [{
            property: 'leaf',
            direction: 'ASC'
        },{
            property: 'text',
            direction: 'ASC'
        }],
        root: {
            text: '红旗汽车修理厂',
            id: '10',
            expanded: true,
        }
    });
	  var tree = Ext.create('Ext.tree.Panel', {
	        store:store,
	        height:500,
	        autoScroll : true,
			animate : false,
			useArrows : false,
			border : false,
	        rootVisible:true,
	        listeners:{
	        	'itemcontextmenu':function(tree, record, item, index, e, eOpts){ 
	        	var rid=record.data.id;
	        	e.preventDefault();
	            e.stopEvent();
	            //显示右键菜单
	            contextMenu.showAt(e.getXY());
	           
	        },
	        'itemclick':function(v,r,i,i,e){ 
	        	var rid=r.get('id');//获取点击的仓库号
	        	//alert("你点的是仓库"+rid);
	        	userStore.getProxy().extraParams.repertoryId = rid; 
	        	userStore.getProxy().extraParams.username = 0; 
	        	grid.getStore().getProxy().url ='user.do?reqCode=query'; 
				Ext.getCmp("toolbar").moveFirst();
	            
	          },
	        	
	        }
	    });
	   
	  //添加用户表单
	// 
		var adduserPanel = Ext.create('Ext.form.Panel', {
			// url:'save-form.php',
			frame : false,
			bodyStyle : 'padding:5px 5px 0',
			buttonAlign : 'center',
			fieldDefaults : {
				msgTarget : 'side',
				labelWidth : 75
			},
			defaults : {
				anchor : '100%'
			},

			items : [ {
				xtype : 'textfield',
				id : 'user_name',
				fieldLabel : '用户名',
				name : 'user_name',
				emptyText : '请输入用户名',
				allowBlank : false,
				blankText : '用户名不能为空'
			},{
				xtype : 'textfield',
				id : 'user_loginaccount',
				fieldLabel : '登录账户',
				name : 'user_loginaccount',
				emptyText : '请输入账户',
				allowBlank : false,
				blankText : '账户不能为空'
			}, {
				xtype : 'textfield',
				id : 'user_password',
				fieldLabel : '密码',
				name : 'user_password',
				emptyText : '请输入密码',
				allowBlank : false,
				blankText : '密码不能为空'
			}, {
				xtype : 'textfield',
				id : 'user_phone',
				fieldLabel : '联系电话',
				name : 'user_phone',
				emptyText : '请输入电话',
				allowBlank : false,
				blankText : '电话不能为空'
			},  {
				xtype : 'combobox',
				id : 'user_sex',
				fieldLabel: "性别",
				store: sexStore,
				name : 'user_sex',
				emptyText : '请输入账户',
				allowBlank : true,
				displayField: "Name",
		        valueField: "Value",
		        emptyText: "--请选择--",
		        queryMode: "local"
				
			},{
				xtype : 'combobox',
				id : 'role_id',
				fieldLabel: "角色",
				triggerAction : 'all', 
				editable : false, 
				store: ustStore,
				name : 'role_id',
				emptyText : '请选择角色',
				selectOnFocus : true, 
				forceSelection : true, 
				displayField: "role_name",
		        valueField: "role_id",
		        emptyText: "--请选择角色--",
		        mode : 'remote', 
				
			},{
				xtype : 'combobox',
				id : 'repertory_id',
				fieldLabel: "所属仓库",
				triggerAction : 'all', 
				editable : false, 
				store: rpStore,
				name : 'repertory_id',
				emptyText : '请选择仓库',
				selectOnFocus : true, 
				forceSelection : true, 
				displayField: "repertory_name",
		        valueField: "repertory_id",
		        emptyText: "--请选择仓库--",
		        mode : 'remote', 
				
			},],

			buttons : [ {
				text : '修改',
				id : 'modify',
				handler : function() {
					var form = this.up('form').getForm();
					if (Ext.getCmp('modify').getText() == '保存') {
						addUser(form);
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
	  //添加用户窗口
	  var addUserWindow = new Ext.Window({
		    id:'adduserwindow',
			layout : 'fit',
			width : 300,
			height :280 ,
			resizable : false,
			closeAction : 'hide',
			modal : true,
			title : '添加用户',
			// iconCls : 'page_addIcon',
			collapsible : true,
			titleCollapse : true,
			maximizable : false,
			border : false,
			items : [adduserPanel]
		});

	
	var viewport = new Ext.Viewport({
		layout : 'border',
		items : [{
					title : '仓库列表',
					collapsible : true,
					width : 210,
				    height:500,
					split : true,
					region : 'west',
					autoScroll : true,
					margins : '3 3 3 3',
					// collapseMode:'mini',
					items : [tree]
				}, {
					region : 'center',
					layout : 'fit',
					border : false,
					margins : '3 3 3 3',
					items : [grid]
				}]
	});

	

	// 添加用户
	function addUser(form) {
		if (form.isValid()) {
			form.submit({
				url : 'user.do?reqCode=changgeUser',
				method : 'POST',
				waitMsg : '正在添加，请稍后',
				success : function(form, action) {
					addUserWindow.hide();
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
	//修改用户
	function updateUser() {
	   

	};
	
	
	// 修改记录
	function modify(form) {
		if (form.isValid()) {
			form
					.submit({
						url : 'repertory.do?reqCode=updateRepertory',
						params : {
							'repertory_id' : grid.getSelectionModel()
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
    function deleteUser(){
    	var record = grid.getSelectionModel()
		.getSelection();
    if (record.length == 0) {
	 Ext.MessageBox.show({
		title : "提示",
		msg : "请先选择您要删除的行!",
	    icon: 'Ext.MessageBox.INFO'
	})
	return;
   }else{
	   Ext.MessageBox.confirm('信息确认', '你确定要删除该条记录吗', function(btn){
		    if (btn == 'yes'){
		    	Ext.Ajax.request({
					url :'user.do?reqCode=deleteUser',
					params :{
						'user_id' : grid.getSelectionModel()
						.getSelection()[0].data.user_id
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
});
     
