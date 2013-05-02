package eu.ttbox.controler.core.chat {

	[Bindable]
    public class ChatMessageVO {
		
		public var timestamp:Number;
		public var username:String;
		public var message:String;

		public var messageId:String;
		public var correlationId:String;
		
		public function ChatMessageVO(username:String,message:String  ) {
			this.username = username;
			this.message = message; 
        }
		
		
    }
}