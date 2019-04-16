Ext.onReady(function(){
	var win = Ext.create("Ext.window.Window", {
	    id: "myWin",
	    title: "示例窗口",
	    width: 500,
	    height: 300,
	    layout: "fit",
	    items: [
	        {
	            xtype: "form",
	            defaultType: 'textfield',
	            defaults: {
	                anchor: '100%',
	            },
	            fieldDefaults: {
	                labelWidth: 80,
	                labelAlign: "left",
	                flex: 1,
	                margin: 5
	            },
	            items: [
	                {
	                    xtype: "container",
	                    layout: "hbox",
	                    items: [
	                        { xtype: "textfield", name: "name", fieldLabel: "姓名", allowBlank: false },
	                        { xtype: "numberfield", name: "age", fieldLabel: "年龄", decimalPrecision: 0, vtype: "age" }
	                    ]
	                }
	            ]
	        }
	    ],
	    buttons: [
	        { xtype: "button", text: "确定", handler: function () { this.up("window").close(); } },
	        { xtype: "button", text: "取消", handler: function () { this.up("window").close(); } }
	    ]
	});
	
	win.show();
	
})
