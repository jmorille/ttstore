package eu.ttbox.event
{
	import eu.ttbox.model.user.User;
	
	import flash.events.Event;
	
	import org.granite.tide.events.AbstractTideEvent;
	import org.granite.tide.events.TideUIEvent;
	
	public class UserEvent extends TideUIEvent
	{
		public static const SAVE_USER_REQUESTED:String = "eu.ttbox.event.SAVE_USER_REQUESTED";
		public static const CREATE_USER_REQUESTED : String = "eu.ttbox.event.CREATE_USER_REQUESTED";
		public static const RESET_USER_REQUESTED : String = "eu.ttbox.event.RESET_USER_REQUESTED";

		
		public var user : User;
		
		/**
		 * This is just a normal Flex event. The only thing to note is that we set 'bubbles' to true,
		 * so that the event will bubble up the display list, allowing Swiz to listen for your events.
		 */
		public function UserEvent( type:String, user:User=null )
		{
			super( type , user  );
			this.user = user;
		}
	}
}