package eu.ttbox.event.cashregister {
	
	import eu.ttbox.model.catalog.Offer;
	
	import org.granite.tide.events.TideUIEvent;

    public class BasketEvent extends TideUIEvent {

        public static const ADD_OFFER_OT_BASKET:String="addOrderToBasket";

		public var offer:Offer;
		
        public function BasketEvent(type:String, offer:Offer) {
			super(type, offer);
			this.offer = offer;
        }
		
    }
}