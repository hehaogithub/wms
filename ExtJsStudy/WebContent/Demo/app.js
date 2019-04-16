Ext.onReady(function() {
	Ext.QuickTips.init();

	Ext.Loader.setConfig({
		enabled : true
	});

	Ext.application({

		// 设定命名空间

		name : 'AM',

		// 指定配置选项，设置相应的路径

		appFolder : 'Demo/app',

		// 加载控制器

		controllers : [ 'demoController' ],

		// 自动加载和实例化Viewport文件
		autoCreateViewport : true

	});

});