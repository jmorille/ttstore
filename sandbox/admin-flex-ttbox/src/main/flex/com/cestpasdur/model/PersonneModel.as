package com.cestpasdur.model
{
	import com.cestpasdur.event.PersonneEvent;
	
	import flash.events.EventDispatcher;
	
	import mx.collections.ArrayCollection;
	import mx.events.CollectionEvent;

	public class PersonneModel extends EventDispatcher {
		
		[Bindable]
		private var _personnes:ArrayCollection;

		public function get personnes():ArrayCollection {
			return _personnes;
		}
		
		public function set personnes(value:ArrayCollection):void {
			_personnes = value;
			dispatchEvent(new PersonneEvent(PersonneEvent.TYPE_MODEL_CHANGED, true, true));
		}
	}
}