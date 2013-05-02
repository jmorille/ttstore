package eu.ttbox.event.admin.catalog {
	import eu.ttbox.model.catalog.Catalog;
	
	import org.granite.tide.events.TideUIEvent;

    public class CatalogEvent  extends TideUIEvent {
		
		public static const SAVE:String="catalogSave";

		public static const DELETE:String="catalogDelete";

		public var catalog:Catalog;
		
        public function CatalogEvent(type:String, entity:Catalog=null) {
			super(type, entity);
			this.catalog=entity;
		}

    }
}