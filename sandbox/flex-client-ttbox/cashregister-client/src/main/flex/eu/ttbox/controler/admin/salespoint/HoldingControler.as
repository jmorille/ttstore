package eu.ttbox.controler.admin.salespoint {
    import eu.ttbox.controler.admin.AdminTideError;
    import eu.ttbox.model.audit.AuditTrail;
    import eu.ttbox.model.salespoint.Holding;
    import eu.ttbox.service.salespoint.HoldingService;

    import mx.collections.ListCollectionView;
    import mx.data.utils.Managed;

    import org.granite.tide.events.TideResultEvent;

    [Name]
    public class HoldingControler {

        [Inject]
        public var holdingService:HoldingService;

        [Bindable]
        public var holdingList:ListCollectionView;

        public function HoldingControler() {
        }

        public function getAll():void {
            holdingService.getAll(resultHandler, AdminTideError.saveFaultHandler);
        }

        private function resultHandler(event:TideResultEvent):void {
            holdingList=event.result as ListCollectionView;
        }

        public function save(entity:Holding):void {
            if (isNaN(entity.id)) {
                holdingService.persist(entity, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
                getAll();
            } else {
                holdingService.merge(entity, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
            }
        }

        public function remove(entity:Holding):void {
            var audit:AuditTrail=entity.auditTrail as AuditTrail;
            holdingService.remove(entity, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
            getAll();
        }

        public function createNewEntity():Holding {
            var entity:Holding=new Holding();
            return entity;
        }

        public function resetEntity(entity:Holding):void {
            if (entity) {
                Managed.resetEntity(entity);
            }
        }


    }
}