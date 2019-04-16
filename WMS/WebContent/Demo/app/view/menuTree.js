Ext.define('AM.view.menuTree', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.menutree',
    border: false,
 
    hrefTarget: 'mainContent',
    rootVisible:true,
    store: 'menuStore',
    height:500,
    bodyStyle: {
       
        padding: '10px'
    }
});