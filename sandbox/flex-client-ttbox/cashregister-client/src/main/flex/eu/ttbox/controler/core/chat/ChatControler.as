package eu.ttbox.controler.core.chat {

    import eu.ttbox.controler.core.I18nManager;
    import eu.ttbox.event.core.chat.ChatEvent;

    import flash.desktop.NativeApplication;
    import flash.events.Event;

    import mx.collections.ArrayList;
    import mx.messaging.events.MessageEvent;
    import mx.messaging.messages.AsyncMessage;
    import mx.utils.StringUtil;

    import org.granite.gravity.Consumer;
    import org.granite.gravity.Producer;
    import org.granite.tide.events.TideUIEvent;
    import org.granite.tide.service.IServiceInitializer;
    import org.granite.tide.spring.Context;
    import org.granite.tide.spring.Identity;

    [Name]
    public class ChatControler {

        // Configuration 
        private var chatTopic:String="discussion";

        private var chatDestination:String="gravity";

        [Bindable]
        [Inject]
        public var msg:I18nManager;

        [In]
        [Bindable]
        public var identity:Identity;

        [In]
        public var tideContext:Context;

        [In]
        public var serviceInitializer:IServiceInitializer;


        [Bindable]
        public var chatMessages:ArrayList=new ArrayList();

        private var maxChatMessageHisto:Number=50;

        // Connector  	
        private var consumer:Consumer;

        private var producer:Producer;

        //Stats
        private var linesCount:int=0;
        private var msgIndex:int=0;


        [PostConstruct]
        public function init():void {
            NativeApplication.nativeApplication.addEventListener(Event.EXITING, handleNativeApplicationExiting);
        }

        public function handleNativeApplicationExiting(event:Event):void {
            disconnect();
        }

        [Observer("org.granite.tide.login")]
        public function connect():void {

            // Create Consumer
            consumer=new Consumer();
            consumer.destination=chatDestination;
            consumer.topic=chatTopic;
            serviceInitializer.initialize(consumer);
            consumer.subscribe();
            consumer.addEventListener(MessageEvent.MESSAGE, messageHandler);
            trace("ChatControler - Connect to Topic [", chatTopic, "]");


            // Create Producer
            producer=new Producer();
            producer.destination=chatDestination;
            producer.topic=chatTopic;
            serviceInitializer.initialize(producer);

            //Send Connection Notification
            send(msg.getString("msg_chat_connected"));
            //var chatEvent:TideUIEvent=new TideUIEvent(ChatEvent.SEND_MESSAGE , "has just connected");
            //tideContext.dispatchEvent(chatEvent);
        }

        private function messageHandler(event:MessageEvent):void {
            var msg:AsyncMessage=event.message as AsyncMessage;
            // Decode Msg
            var chatLine:ChatMessageVO=new ChatMessageVO(msg.body.username, msg.body.message);
            chatLine.timestamp=msg.timestamp;
            chatLine.messageId=msg.messageId;
            chatLine.correlationId=msg.correlationId;

            // Increment Stat
            linesCount++;

            // Reference message

            /*            var countMsgToRemove:Number=chatMessages.length - maxChatMessageHisto;
               if (countMsgToRemove > 0) {
               // Clean Old Mesage
               chatMessages.removeItemAt(0);
             }*/

            chatMessages.addItem(chatLine);

            //Dispatch Message
            var chatEvent:TideUIEvent=new TideUIEvent(ChatEvent.RECEIVE_MESSAGE, chatLine);
            tideContext.dispatchEvent(chatEvent);

        }

        [Observer("sendChatMessage")]
        public function send(iNewMessage:String):void {
            //Alert.show("Ask Send msg : " + iNewMessage);
            var message:String=StringUtil.trim(iNewMessage);
            if (message.length > 0) {
                var msg:AsyncMessage=new AsyncMessage();
                msg.body.username=identity.username;
                msg.body.message=message;
                msg.body.msgIndex=(msgIndex++);
                producer.send(msg);

                    //TODO chatMessages.addItem(
            }
        }


        [Observer("org.granite.tide.logout")]
        public function disconnect():void {
            trace("ChatControler - Disconnect Begin");

            if (consumer != null) {
                consumer.disconnect();
                trace("ChatControler - Disconnect from Topic [", chatTopic, "]");
                consumer=null;
            }

            if (producer != null) {
                send(msg.getString("msg_chat_deconnected"));
                producer.disconnect();
                producer=null;
            }

            //Clear Stat
            chatMessages.removeAll();
            //chatMessages=new ListCollectionView();
            linesCount=0
            msgIndex=0;
            //TODO vMessages.text = "";
            trace("ChatControler - Disconnect End");

        }



    }
}