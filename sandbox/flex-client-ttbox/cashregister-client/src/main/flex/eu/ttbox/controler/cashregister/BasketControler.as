package eu.ttbox.controler.cashregister {
    import eu.ttbox.model.catalog.Offer;
    import eu.ttbox.model.order.OrderItem;

    import mx.collections.ArrayCollection;

    [Name]
    public class BasketControler {

        [Bindable]
        public var offers:ArrayCollection=new ArrayCollection();

        public function BasketControler() {
        }

        [Observer("addOrderToBasket")]
        public function addOrder(offer:Offer):void {
            var orderProduct:OrderItem=new OrderItem();
            orderProduct.quantity=1;
			orderProduct.offer=offer;
            offers.addItem(offer);

        }

    }
}