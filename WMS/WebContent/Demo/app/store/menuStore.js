Ext.define("AM.store.menuStore",{
    extend:'Ext.data.TreeStore',
    
  
    //requires: 'Demo.model.menuModel',//我加了这两行，会报错
    //model: 'Demo.model.menuModel',
    proxy:{
        type:'ajax',
        url:'login.do?reqCode=listMenu',
        reader:'json',
        autoLoad:true
    },
    root: {
        text: '根',
        id: '0',
        expanded: false,
   },
   folderSort: true,
   sorters: [{
      property: 'id',
      direction: 'ASC'
}]
 
	  
	 
   
});