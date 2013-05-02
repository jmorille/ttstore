package eu.ttbox.view.admin.salespoint {
    import eu.ttbox.controler.admin.AdminTideError;
    import eu.ttbox.controler.admin.salespoint.HoldingControler;
    import eu.ttbox.model.salespoint.Holding;
    import eu.ttbox.service.salespoint.HoldingService;
    
    import flash.events.Event;
    import flash.events.EventDispatcher;
    
    import mx.collections.ListCollectionView;
    import mx.data.utils.Managed;
    
    import org.granite.tide.events.TideResultEvent;
    import eu.ttbox.view.admin.core.IEntityCrudCommand;

    public class HoldingCrudCommand extends EventDispatcher 
		implements IEntityCrudCommand {

        [Bindable]
        public var entity:Holding;

        public var service:HoldingService;

        [Bindable]
        public var resultList:ListCollectionView;


        public function setSelectedEntity(item:*):void {
            this.entity=item as Holding;
            // dispatchEvent(new Event("change"));
            trace("Select item", item, " => ", entity.id);
        }

        public function getSelectedEntity():* {
            trace("GET Select entity", entity);
            return entity;
        }


        public function doSearch():void {
            service.getAll(resultHandler, AdminTideError.saveFaultHandler);
        }

        private function resultHandler(event:TideResultEvent):void {
            resultList=event.result as ListCollectionView;
        }

        public function doSave():void {
            if (isNaN(entity.id)) {
                service.persist(entity, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
                doSearch();
            } else {
                service.merge(entity, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
            }
        }

        public function doRemove():void {
            service.remove(entity, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
            doSearch();
            entity=null;
        }

        public function doCancel():void {
            Managed.resetEntity(entity);
        }

        public function doCreateNewEntity():void {
            entity=new Holding();
        }


    }
}