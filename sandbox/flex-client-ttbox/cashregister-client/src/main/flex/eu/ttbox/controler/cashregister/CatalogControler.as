package eu.ttbox.controler.cashregister {
    import eu.ttbox.model.catalog.Catalog;
    import eu.ttbox.model.catalog.Offer;
    import eu.ttbox.service.catalog.CatalogService;
    
    import mx.collections.ArrayList;
    import mx.collections.ListCollectionView;
    import mx.controls.Alert;
    
    import org.granite.ns.tide;
    import org.granite.tide.events.TideEvent;
    import org.granite.tide.events.TideResultEvent;

    [Name]
    public class CatalogControler {


        [Inject]
        public var catalogService:CatalogService;

		[Bindable]
        public var offers:ArrayList = new ArrayList();

        public function CatalogControler() {
        }

        [PostConstruct]
        public function init():void {
            getAllActive();
        }

        public function getAllActive():void {
            catalogService.getAll(resultHandler);
        }

        private function resultHandler(event:TideResultEvent):void {
            var catalogs:ListCollectionView=event.result as ListCollectionView;
            //var newOffers:ArrayList= new ArrayList()
            for each (var obj:Object in catalogs) {
				var catalog:Catalog = obj as Catalog;
				var catOffers:ListCollectionView = catalog.offers; 
				for each (var obj2:Object in catalog.offers) {
					var offer:Offer = obj2 as Offer;
				}
				//Alert.show("Add offers " + catOffers.length + "for " + catalog.name );
				if (catOffers!=null && catOffers.length>0) {
					offers.addAll(catalog.offers);
					//Alert.show("Add offers " + catOffers.length);
				}
            }
            //offers=newOffers;
        }

    }
}