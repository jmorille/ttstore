package eu.ttbox.controler.admin.catalog {

    import eu.ttbox.model.catalog.Catalog;
    import eu.ttbox.service.catalog.CatalogService;

    import mx.collections.ListCollectionView;
    import mx.controls.Alert;

    import org.granite.tide.events.TideFaultEvent;
    import org.granite.tide.events.TideResultEvent;


    [Name]
    public class CatalogAdminControler {

        [Inject]
        public var catalogService:CatalogService;

        [Bindable]
        public var catalogList:ListCollectionView;

        public function CatalogAdminControler() {
        }

        [PostConstruct]
        public function init():void {
            getAll();
        }

        public function getAll():void {
            catalogService.getAll(resultHandler, faultHandler);
        }

        private function resultHandler(event:TideResultEvent):void {
            catalogList=event.result as ListCollectionView;
        }

        private function faultHandler(event:TideFaultEvent):void {
            Alert.show(event.fault.message, "Fault result");
            // todo Display Message Cancel
        }


        [Observer("catalogSave")]
        public function save(catalog:Catalog):void {
            if (isNaN(catalog.id)) {
                catalogService.persist(catalog, saveResultHandler, saveFaultHandler);
            } else {
                catalogService.merge(catalog, saveResultHandler, saveFaultHandler);
            }
        }

        private function saveResultHandler(event:TideResultEvent):void {
            // todo Display Message Succes
        }

        private function saveFaultHandler(event:TideFaultEvent):void {
            Alert.show(event.fault.message, "Fault result");
            // todo Display Message Cancel
            // tod Cancel object or display Errors
        }


    }
}