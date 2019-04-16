Ext.onReady(function () {
    	var loginForm = Ext.create("Ext.form.Panel", {
    	    title: '用户登陆',
    	    frame: true,
    	    width: 320,
    	    bodyPadding: 10,
    	    defaultType: 'textfield',
    	    defaults: {
    	        anchor: '100%',
    	        labelWidth: 50,
    	        labelAlign: "right"
    	    },
    	    items: [
    	        {
    	            allowBlank: false,
    	            fieldLabel: '用户名',
    	            name: 'UserName',
    	            emptyText: '用户名'
    	        },
    	        {
    	            allowBlank: false,
    	            fieldLabel: '密码',
    	            name: 'Password',
    	            emptyText: '密码',
    	            inputType: 'password'
    	        },
    	        {
    	            xtype: 'checkbox',
    	            fieldLabel: '记住我',
    	            name: 'remember'
    	        }
    	    ],
    	    buttons: [
    	        { text: '重置' },
    	        { text: '登陆' }
    	    ],
    	    renderTo: "container"
    	});
	});
