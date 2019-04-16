Ext.define('Am.model.menuModel', {
    extend: 'Ext.data.Model',
    fields:[
        {name:'id', type:'int'},
        {name:'pid', type:'int'},
        {name:'text', type:'varchar'},
        //type为布尔型时，后面的默认值是必须写的，要不会出错
        {name:'leaf', type:'boolean', defaultValue: true},
        {name: 'url', type:'varchar'}
    ]
});