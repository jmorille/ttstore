package eu.ttbox.event.core.chat {

    import eu.ttbox.controler.core.chat.ChatMessageVO;

    import flash.events.Event;

    import org.granite.tide.events.TideUIEvent;

    public class ChatEvent extends TideUIEvent {

        public static const SEND_MESSAGE:String="sendChatMessage";

        public static const RECEIVE_MESSAGE:String="receiveChatMessage";

        public var chatLine:ChatMessageVO;

        public function ChatEvent(type:String, chatLine:ChatMessageVO) {
            super(eventType, chatLine);
            this.chatLine=chatLine;

        }

/*        public override function clone():Event {
            var chatEvent:ChatEvent=new ChatEvent(this.eventType, chatLine);
            return chatEvent;
        }*/

    }
}