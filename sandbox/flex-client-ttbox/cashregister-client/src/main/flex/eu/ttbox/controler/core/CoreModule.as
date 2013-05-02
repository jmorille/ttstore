package eu.ttbox.controler.core {
    import eu.ttbox.controler.core.chat.ChatControler;
    
    import org.granite.tide.ITideModule;
    import org.granite.tide.Tide;

    public class CoreModule implements ITideModule {
        
        public function CoreModule() {
        }

        public function init(tide:Tide):void {
			tide.addComponent("chatControler", ChatControler, false, true, Tide.RESTRICT_YES); 
			tide.addComponent("msg", I18nManager, false, true, Tide.RESTRICT_YES); 
		}
		
		
    }
}