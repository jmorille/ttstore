package eu.ttbox.view.admin.salespoint {
    import eu.ttbox.controler.admin.AdminTideError;
    import eu.ttbox.model.salespoint.Salespoint;
    import eu.ttbox.service.salespoint.SalespointService;
    import eu.ttbox.view.admin.core.IEntityCrudCommand;

    import flash.events.EventDispatcher;

    import mx.collections.ListCollectionView;
    import mx.data.utils.Managed;

    import org.granite.tide.events.TideResultEvent;

    public class SalespointCrudCommand extends EventDispatcher implements IEntityCrudCommand {

        [Bindable]
        public var entity:Salespoint;

        public var service:SalespointService;

        [Bindable]
        public var resultList:ListCollectionView;


        public function setSelectedEntity(item:*):void {
            this.entity=item as Salespoint;
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
            entity=new Salespoint();
        }


    }
}