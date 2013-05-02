package eu.ttbox.config {

    import com.googlecode.flexxb.FlexXBEngine;

    import eu.ttbox.config.model.ApplicationDescriptorModel;
    import eu.ttbox.config.model.TTBoxConfigModel;

    import flash.desktop.NativeApplication;
    import flash.events.Event;
    import flash.events.EventDispatcher;
    import flash.events.IEventDispatcher;
    import flash.events.IOErrorEvent;
    import flash.filesystem.File;
    import flash.filesystem.FileMode;
    import flash.filesystem.FileStream;
    import flash.net.URLLoader;
    import flash.net.URLRequest;
    import flash.utils.Proxy;
    import flash.utils.flash_proxy;
    import flash.xml.XMLDocument;
    import flash.xml.XMLNode;

    import mx.controls.Alert;
    import mx.rpc.xml.SimpleXMLDecoder;
    import mx.rpc.xml.SimpleXMLEncoder;

    /**
     *  http://nwebb.co.uk/blog/?p=478
     * http://nwebb.co.uk/nwebb_example_code/ConfigProxyExample.zip
     */
    public dynamic class AppConfigLoader implements IEventDispatcher {

        private var _eventDispatcher:IEventDispatcher;

        private var _url:String="ttbox-config.xml";

        [Bindable]
        public var config:TTBoxConfigModel;

        [Bindable]
        private static var instance:AppConfigLoader=new AppConfigLoader();


        public function AppConfigLoader() {
            // instance=new AppConfigLoader();
            _eventDispatcher=new EventDispatcher();
        }

        public static function getInstance():AppConfigLoader {
            if (!instance.config) {
                instance.load();
            }
            return instance;
        }


        public function load():void {
            config=loadXmlFile(_url);
            trace("Load configuration file", _url);
        }

        public function save():void {
            if (!config) {
                config=new TTBoxConfigModel();
            }
            saveXmlFile(_url, config);
            trace("Save configuration file", _url);
        }

        private function loadXmlFile(url:String):TTBoxConfigModel {
            //Read File
            var dataFile:File=File.documentsDirectory.resolvePath(url);
            var resultObj:TTBoxConfigModel;
            if (dataFile.exists) {
                var stream:FileStream=new FileStream();
                stream.open(dataFile, FileMode.READ);
                var dataString:String=stream.readUTFBytes(stream.bytesAvailable);
                stream.close();
                //Convert File TO Object
                var xmlObject:XML=new XML(dataString);
                resultObj=FlexXBEngine.instance.deserialize(xmlObject, TTBoxConfigModel);
            }
            if (!resultObj) {
                resultObj=new TTBoxConfigModel();
            }
            return resultObj;
        }

        /**
         * Save the modified data to the xml file.
         */
        private function saveXmlFile(url:String, configModel:TTBoxConfigModel):void {
            // Convert To Xml
            var modelConfigXml:XML=FlexXBEngine.instance.serialize(configModel, false);

            // Write To File
            var dataFile:File=File.documentsDirectory.resolvePath(url);
            var stream:FileStream=new FileStream();
            stream.openAsync(dataFile, FileMode.WRITE);
            stream.writeUTFBytes(modelConfigXml);
            stream.addEventListener(Event.CLOSE, onDataSaved);
            stream.close();
        }

        /**
         * Called when the data has been successfully saved.
         * Load the saved data back into the application.
         */
        private function onDataSaved(event:Event):void {
            load();
        }

        /*
           public function loadConfigFile(url:String):void {
           _url=url;
           // load file
           var request:URLRequest=new URLRequest(url);
           _urlLoader=new URLLoader();
           _urlLoader.addEventListener(Event.COMPLETE, onDataLoaded);
           _urlLoader.addEventListener(IOErrorEvent.IO_ERROR, onIOError);
           _urlLoader.load(request);
           // Reset Cache
           _cache=new Object();
           }

           protected function onDataLoaded(event:Event):void {
           _data=XML(_urlLoader.data);
           _dataLoaded=true;
           removeListeners();
           dispatchEvent(new Event(Event.COMPLETE));
           }


           protected function onIOError(event:IOErrorEvent):void {
           removeListeners()
           dispatchEvent(event);
           }

           protected function removeListeners():void {
           _urlLoader.removeEventListener(Event.COMPLETE, onDataLoaded);
           _urlLoader.removeEventListener(IOErrorEvent.IO_ERROR, onIOError);
         }*/


        public function getApplicationDescriptor():ApplicationDescriptorModel {
            var appXml:XML=NativeApplication.nativeApplication.applicationDescriptor;
            var ns:Namespace=appXml.namespace();
            // Create Model
            var appModel:ApplicationDescriptorModel=new ApplicationDescriptorModel();
            appModel.appId=appXml.ns::id[0];
            appModel.appVersion=appXml.ns::version[0];
            appModel.appName=appXml.ns::filename[0];

            return appModel;
        }

        //IEventDispatcher Stuff:
        public function addEventListener(type:String, listener:Function, useCapture:Boolean=false, priority:int=0, useWeakReference:Boolean=false):void {
            _eventDispatcher.addEventListener(type, listener, useCapture, priority, useWeakReference);
        }

        public function removeEventListener(type:String, listener:Function, useCapture:Boolean=false):void {
            _eventDispatcher.removeEventListener(type, listener, useCapture);
        }

        public function dispatchEvent(event:Event):Boolean {
            return _eventDispatcher.dispatchEvent(event);
        }

        public function hasEventListener(type:String):Boolean {
            return _eventDispatcher.hasEventListener(type);
        }

        public function willTrigger(type:String):Boolean {
            return _eventDispatcher.willTrigger(type);
        }

    }
}

