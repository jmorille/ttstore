package eu.ttbox.controler.admin.fileimporter {
    import eu.ttbox.controler.admin.AdminTideError;
    import eu.ttbox.model.product.Product;
    import eu.ttbox.service.product.ProductService;
    import eu.ttbox.service.product.UploadService;

    import flash.net.URLLoader;
    import flash.net.URLLoaderDataFormat;
    import flash.net.URLRequest;
    import flash.utils.ByteArray;

    import mx.collections.ListCollectionView;
    import mx.controls.Alert;

    import org.granite.tide.events.TideFaultEvent;
    import org.granite.tide.events.TideResultEvent;

    import spark.components.Group;

    [Name]
    public class ProductFileControler {

        [Inject]
        public var productService:ProductService;


        [Bindable]
        public var productList:ListCollectionView;


        [PostConstruct]
        public function init():void {
            // getAll();
        }

        /*
         * Read Method
         */
        public function getAll():void {
            productService.getAll(resultHandler, faultHandler);

        }

        private function resultHandler(event:TideResultEvent):void {
            productList=event.result as ListCollectionView;
        }

        private function faultHandler(event:TideFaultEvent):void {
            Alert.show(event.fault.message, "Fault result");
            // todo Display Message Cancel
        }

        /*
         * Write Method
         */
        [Observer("productPersist")]
        public function persist(product:Product):void {
            productService.persist(product, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
        }

        [Observer("productMerge")]
        public function merge(product:Product):void {
            productService.merge(product, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
        }

        [Observer("productSave")]
        public function save(product:Product):void {
            if (isNaN(product.id)) {
                productService.persist(product, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
            } else {
                productService.merge(product, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
            }
        }

        [Observer("productRemove")]
        public function remove(product:Product):void {
            productService.remove(product, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
        }



    }
}