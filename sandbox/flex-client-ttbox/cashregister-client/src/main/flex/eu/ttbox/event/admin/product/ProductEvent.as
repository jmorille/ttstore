package eu.ttbox.event.admin.product {
    import eu.ttbox.model.product.Product;

    import org.granite.tide.events.TideUIEvent;

    public class ProductEvent extends TideUIEvent {

		
		public static const REFRESH_SEARCH_LIST:String="productRefreshSearchList";
 
        public static const SAVE:String="productSave";

        public static const DELETE:String="productRemove";

        public var product:Product;

        public function ProductEvent(type:String, product:Product=null) {
            super(type, product);
            this.product=product;
        }

    }
}