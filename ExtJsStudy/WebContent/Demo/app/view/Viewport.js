var aWeek=['星期天','星期一','星期二','星期三','星期四','星期五','星期六'];
var apM=new Date().getHours()>=12?'下午':'上午';
Ext.define('AM.view.Viewport',
 { 
  extend:'Ext.container.Viewport',

    //布局方式：border
  layout:'border',

    items:
 [{

	    border: false,
	    layout:'anchor',
	    region:'north',
	    cls: 'docs-header',
	    height:80,
	    items: [{
			id:'header-top',
			xtype:'box',
			cls:'header',
			border:false,
			anchor: 'none -25',
			html:'<span style="margin-left:10px;font-size:30px;color:white;FONT-FAMILY:微软雅黑">红旗汽车厂物资流通管理系统</span>',
			
		},new Ext.Toolbar({
			items:[
					
					{
					 //此处加载登录用户信息
					 xtype:'label',
					 iconCls: 'grid-add',
					 id:'head-lb-1',
					 cls:'classDiv2',
					 text:'欢迎您,'+name+'！ '+'账户： '+account,
					 margins:'0 20 0 20'
					 },
					 {
					 xtype:'label',
					 id:'clock',
					 margins:'0 20 0 20',
					 cls:'classDiv2',
					 text:'今天是:',
					 listeners: {
						      'render': function() {
						    	  Ext.TaskManager.start({
						    		    run: function() {
						    		      Ext.getCmp("clock").setText('今天是:'+Ext.Date.format(new Date(), 'Y-m-d '+aWeek[new Date().getDay()]+' G:i:s '+apM));
						    		    },
						    		    interval: 1000
						    		  });
						      }
						    }
					 }, '->', {
					 	xtype:'button',
					 	text:'换肤',
						iconCls: 'grid-add',
						//tooltip: '全屏显示主操作窗口',
						handler: function(){

						}
					 },'-', {
						xtype:'button',
					 	text:'注销',
						iconCls: 'grid-add',
						 handler: function(){
							logout();
						 }
					 },'-'
				]}
			)]

    },{

        title:'功能菜单',

        region:'west',

        width:180,

        split:true,

        collapsible:true,

        items:[{
            xtype: 'menutree'
        }]

    },
 {

        id:'mainContent',

        title:'主题内容显示',

        layout:'fit',

        region:'center',

        collapsible:true,

       // contentEl:'contentIframe'

    },{
        region: 'center',
        id: 'mainContent',
        xtype:'tabpanel',
        title: '主题内容显示',
        layout: 'fit',
        collapsible: true,
       
    },{ margins:'1,1,1,1',
        region: 'south',
        id: 'bottom',
        xtype:'toolbar',
        height:30, 
        items:['->',"版权所有     Copyright © 2019 杭电计算机学院",'->'] 
       
    }]

});
function logout(){
	Ext.MessageBox.show({
			title : '提示',
			msg : '确认要注销系统,退出登录吗?',
			width : 250,
			buttons : Ext.MessageBox.YESNO,
			animEl : Ext.getBody(),
			icon : Ext.MessageBox.QUESTION,
			fn : function(btn) {
				if (btn == 'yes') {
						Ext.MessageBox.show({
								title : '请等待',
								msg : '正在注销...',
								width : 300,
								wait : true,
								waitConfig : {
									interval : 50
								}
							});
				  window.location.href = 'login.do?reqCode=logout';
				}
			}
		});
}
