package eu.ttbox.controler.cashregister {
	import org.granite.tide.ITideModule;
	import org.granite.tide.Tide;

    public class CashRegisterModule implements ITideModule {

        public function CashRegisterModule() {
        }

		
		public function init(tide:Tide):void {
			tide.addComponent("catalogControler", CatalogControler, false, true, Tide.RESTRICT_YES); 
			tide.addComponent("basketControler", BasketControler, false, true, Tide.RESTRICT_YES); 

		}
		
		
    }
}