Ext.onReady(function(){
	//登录面板
	var loginpanel = Ext.create('Ext.form.Panel', {
        // url:'save-form.php',
        frame:true,
        bodyStyle:'padding:5px 5px 0',
        width: 350,
        buttonAlign:'center',
        fieldDefaults: {
            msgTarget: 'side',
            labelWidth: 75
        },
        defaults: {
            anchor: '100%'
        },

        items: [{
            xtype:'fieldset',
            title: '账户信息',
            defaultType: 'textfield',
            collapsed: false,
            layout: 'anchor',
            defaults: {
                anchor: '100%'
            },
            items :[{
                fieldLabel: '账号',
                name: 'user_loginaccount',
                emptyText: '请输入账户',
                allowBlank:false,
                blankText:'账号不能为空'
            },{
                fieldLabel: '密码',
                name: 'user_password',
                allowBlank:false,
                inputType: 'password',
                emptyText: '请输入密码',
                blankText:'密码不能为空'
            }]
        }],

        buttons: [{
            text: '登录',
            handler: function() {
           	 var form = this.up('form').getForm();
            	loginCheck(form);
            }
          },{
            text: '重置',
            handler: function() {
              	 var form = this.up('form').getForm();
              	 this.up('form').getForm().reset();
               }
        }]
    });
	//登录窗口
	var loginwindow = Ext.create('widget.window', {
        height: 200,
        width: 360,
        title: '用户登录',
        closable: false,
        plain: true,
        layout: 'fit',
        items:[loginpanel]
        
        
	}).show();
	//登录验证
	 function loginCheck(form){//登录验证
	   if(form.isValid()){
             form.submit({
                 url: 'login.do?reqCode=login',
                 method : 'POST',
                 waitMsg: '正在登录，请稍后',
                 success: function(form, action) {
                	 location.href="index.jsp";
                 },
                 failure:function(form,action){
 					var data=action.response.responseText;
 			        Ext.Msg.alert('登录失败',data);
 				 }
             });
         }
     
	 };
});