package com.cestpasdur.event
{
	
	import flash.events.Event;
	
	public class PersonneEvent extends Event
	{
		
		public static const TYPE_GET:String = "get";
		public static const TYPE_SAVE:String = "save";
		public static const TYPE_MODEL_CHANGED:String = "ModelChanged";
		public static const TYPE_DELETE:String = "delete";
		
		public function PersonneEvent(type:String, bubbles:Boolean, cancelable:Boolean) {
			super(type, bubbles, cancelable);
		}
		
		
	}
}