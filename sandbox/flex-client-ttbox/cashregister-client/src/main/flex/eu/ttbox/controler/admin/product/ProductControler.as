package eu.ttbox.controler.admin.product {
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
    public class ProductControler {

        [Inject]
        public var productService:ProductService;


        [Bindable]
        public var productList:ListCollectionView;


        [Bindable]
        public var currentState:String="productList";

        [Inject]
        public var uploadService:UploadService;

        [PostConstruct]
        public function init():void {
            getAll();
        }

        /*
         * Read Method
         */
        [Observer("productRefreshSearchList")]
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

        private function saveResultHandler(event:TideResultEvent):void {
            // todo Display Message Succes
        }



        private function uploadPhoto(imageData:ByteArray):void {
            // method accepts raw JPEG data as ByteArray as input 
            // create a URLRequest object pointed at the URL of the remote upload script 
            var request:URLRequest=new URLRequest("http://www.o2apps.com/fileUpload.pl");
            // the call we will make is a standard HTTP POST call 
            request.method="POST";
            // this enables us to send binary data for the body of the HTTP POST call 
            request.contentType="application/octet-stream";
            var loader:URLLoader=new URLLoader();
            loader.addEventListener(Event.COMPLETE, uploadPhotoHandler);
            // the loader is the data being sent along to the server. Its dataFormat property lets us specify the format for the body, which, in our case, will be BINARY data 
            loader.dataFormat=URLLoaderDataFormat.BINARY; // the data property of our URLRequest object is the actual data being sent to the server, which in this case is the photo JPEG data 
            request.data=imageData;
            loader.load(request);
        }

        private function uploadPhotoHandler(event:Event):void {
            trace("Response from server: " + event.target.data);
        }

    }
}