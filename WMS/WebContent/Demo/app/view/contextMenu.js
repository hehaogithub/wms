Ext.define('AM.view.contextMenu', {
    extend: 'Ext.menu.Menu',
    alias: 'widget.contextmenu',
    float: true,
    items: [{
        xtype: 'button',
        text: '添加',
        action: 'add',
        iconCls: 'leaf'
    }, {
        xtype: 'button',
        text: '删除',
        action: 'del',
        iconCls: 'leaf'
    }, {
        xtype: 'button',
        text: '编辑',
        action: 'edit',
        iconCls: 'leaf'
    }]
});