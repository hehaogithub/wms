Ext.onReady(function() {

	Ext.define('Repertory', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'repertory_id',
			type : 'int'
		}, {
			name : 'repertory_name',
			type : 'string'
		}, {
			name : 'remark',
			type : 'string'
		}, ],

	});

	var repertoryStore = Ext.create('Ext.data.Store', {
		model : 'Repertory',
		proxy : {
			type : 'ajax',
			url : 'repertory.do?reqCode=getRepertoryList',
			reader : {
				type : 'json',
				root : 'repertory',
				totalProperty : 'total',
				
			}
		},
		autoLoad : false,
		pageSize : 3
	});
	repertoryStore.load({
		params : {
			start : 0,
			limit : 3,
			page : 1
		}
	});
	// 修改信息面板
	var editpanel = Ext.create('Ext.form.Panel', {
		// url:'save-form.php',
		frame : true,
		bodyStyle : 'padding:5px 5px 0',
		width : 350,
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
			id : 'repertory_name',
			fieldLabel : '仓库名',
			name : 'repertory_name',
			emptyText : '请输入账户',
			allowBlank : false,
			blankText : '仓库名不能为空'
		}, {
			xtype : 'textareafield',
			fieldLabel : '描述信息',
			id : 'remark',
			name : 'remark',
			allowBlank : false,
			emptyText : '请输入仓库描述信息',
			blankText : '描述信息不能为空'
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
		height : 200,
		width : 360,
		title : '修改仓库信息',
		closable : false,
		plain : true,
		layout : 'fit',
		items : [ editpanel ]
	});

	var selModel = Ext.create('Ext.selection.CheckboxModel');

	var grid = Ext.create('Ext.grid.Panel', {
		id : 'grid',
		title : '仓库信息表',
		frame : true,
		region : 'center',
		margins : '3 3 3 3',
		store : repertoryStore,
		selModel : selModel,
		columns : [ {
			header : '仓库编号',
			dataIndex : 'repertory_id',
			width : 300,
		}, {
			header : '仓库名',
			dataIndex : 'repertory_name',
			width : 300
		}, {
			header : '备注',
			dataIndex : 'remark',
			width : 300
		} ],
		dockedItems : [ {
			xtype : 'toolbar',
			items : [
					{
						text : '添加',
						iconCls : 'add',
						handler : function() {
							Ext.getCmp('editW').setTitle('添加仓库信息');
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

								Ext.getCmp('editW').setTitle('修改仓库信息');
								Ext.getCmp('modify').setText('修改');
								Ext.getCmp("repertory_name").setValue(
										record[0].data.repertory_name);
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
							deleteRepertory();
							

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

								/*repertoryStore.on('beforeload', function(
										repertoryStore, options) {
									var new_params = {
									
										repertory_name : name
									};
									Ext.apply(repertoryStore.proxy.extraParams,
											new_params);
								});
								grid.getStore().getProxy().url='repertory.do?reqCode=getRepertoryListByKey'
							   }*/
								var new_params = {repertory_name : name};
								Ext.apply(repertoryStore.proxy.extraParams,
										new_params);
								grid.getStore().getProxy().url ='repertory.do?reqCode=getRepertoryListByKey'; 
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
							grid.getStore().getProxy().url ='repertory.do?reqCode=getRepertoryList'; 
						    Ext.getCmp("toolbar").moveFirst();

						}
					} ],

		} ],
		
		bbar : Ext.create('Ext.PagingToolbar', {
			id : 'toolbar',
			store : repertoryStore,
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
				url : 'repertory.do?reqCode=addRepertory',
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
    function deleteRepertory(){
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
						'repertory_id' : grid.getSelectionModel()
						.getSelection()[0].data.repertory_id
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
     
