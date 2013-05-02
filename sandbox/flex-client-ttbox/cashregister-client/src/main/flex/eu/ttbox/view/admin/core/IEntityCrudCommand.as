package eu.ttbox.view.admin.core {
    import mx.collections.ListCollectionView;

    [Bindable]
    public interface IEntityCrudCommand {

        function get resultList():ListCollectionView;
 
        function setSelectedEntity(item:*):void;

        function getSelectedEntity():*;
 
        function doSearch():void;

        function doSave():void;

        function doRemove():void;

        function doCancel():void;

        function doCreateNewEntity():void;


    }
}