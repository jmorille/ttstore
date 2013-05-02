package eu.ttbox.config {
    import mx.logging.ILogger;
    import mx.logging.Log;
    import mx.messaging.Channel;
    import mx.messaging.ChannelSet;
    import mx.messaging.channels.AMFChannel;
    import mx.messaging.channels.SecureAMFChannel;
    import mx.rpc.remoting.RemoteObject;

    import org.granite.gravity.Consumer;
    import org.granite.gravity.Producer;
    import org.granite.gravity.channels.GravityChannel;
    import org.granite.gravity.channels.SecureGravityChannel;
    import org.granite.tide.service.IServiceInitializer;

    public class TTBoxServiceInitialiser implements IServiceInitializer {

        private static var log:ILogger=Log.getLogger("eu.ttbox.config.TTBoxServiceInitialiser");

        private var _graniteChannelSet:ChannelSet;
        private var _gravityChannelSet:ChannelSet;

        private var _contextRoot:String="/ttbox";
        private var _serverName:String="localhost";
        private var _serverPort:String="8080";
        private var _protocol:String="https";
        private var _graniteUrlMapping:String="/graniteamf/amf.txt"; // .txt for stupid bug in IE8
        private var _gravityUrlMapping:String="/gravityamf/amf.txt";

        private var ttboxConfig:TTBoxConfig=new TTBoxConfig();

        public function TTBoxServiceInitialiser(serverName:String=null, serverPort:String=null, contextRoot:String="", graniteUrlMapping:String=null, gravityUrlMapping:String=null, protocol:String="http") {
            super();
            _contextRoot=contextRoot;
            if (serverName != null) {
                _serverName=serverName;
            }
            if (serverPort != null) {
                _serverPort=serverPort;
            }
            if (graniteUrlMapping != null) {
                _graniteUrlMapping=graniteUrlMapping;
            }
            if (gravityUrlMapping != null) {
                _gravityUrlMapping=gravityUrlMapping;
            }
        }


        public function set contextRoot(contextRoot:String):void {
            _contextRoot=contextRoot;
        }

        public function set serverName(serverName:String):void {
            _serverName=serverName;
        }

        public function set serverPort(serverPort:String):void {
            _serverPort=serverPort;
        }

        public function set graniteUrlMapping(graniteUrlMapping:String):void {
            _graniteUrlMapping=graniteUrlMapping;
        }

        public function set gravityUrlMapping(gravityUrlMapping:String):void {
            _gravityUrlMapping=gravityUrlMapping;
        }

        protected function get protocol():String {
            return _protocol;
        }

        public function set protocol(protocol:String):void {
            _protocol=protocol;
        }

        protected function newAMFChannel(id:String, uri:String):Channel {
            if (_protocol == "https") {
                return new SecureAMFChannel(id, uri);
            } else {
                return new AMFChannel(id, uri);
            }
        }

        protected function newGravityChannel(id:String, uri:String):Channel {
            if (_protocol == "https") {
                return new SecureGravityChannel(id, uri);
            } else {
                return new GravityChannel(id, uri);
            }
        }

        private function get graniteChannelSet():ChannelSet {
            if (_graniteChannelSet == null) {
                _graniteChannelSet=new ChannelSet();
                _graniteChannelSet.addChannel(newAMFChannel("graniteamf", _protocol + "://" + _serverName + ":" + _serverPort + _contextRoot + _graniteUrlMapping));
            }
            return _graniteChannelSet;
        }

        private function get gravityChannelSet():ChannelSet {
            if (_gravityChannelSet == null) {
                _gravityChannelSet=new ChannelSet();
                _gravityChannelSet.addChannel(newGravityChannel("gravityamf", _protocol + "://" + _serverName + ":" + _serverPort + _contextRoot + _gravityUrlMapping));
            }
            return _gravityChannelSet;
        }

        public function initialize(service:Object):void {
            if (service is RemoteObject) {
                service.channelSet=graniteChannelSet;
            } else if (service is Consumer) {
                service.channelSet=gravityChannelSet;
            } else if (service is Producer) {
                service.channelSet=gravityChannelSet;
            }
        }
    }
}