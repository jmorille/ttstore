package eu.ttbox.controler.core {
    import flash.events.Event;
    import flash.events.EventDispatcher;
    import flash.events.IEventDispatcher;
    import flash.utils.IExternalizable;
    import flash.utils.Proxy;
    import flash.utils.flash_proxy;

    import mx.binding.utils.BindingUtils;
    import mx.controls.Alert;
    import mx.resources.IResourceManager;
    import mx.resources.ResourceManager;

    [Name]
    [Bindable("change")]
    public dynamic class I18nManager extends Proxy implements IEventDispatcher {


        private var bundleName:String="ttbox_message";

        [Bindable]
        private var resourceManager:IResourceManager;

        private static var instance:I18nManager;

        protected var _eventDispatcher:IEventDispatcher;

        public function I18nManager() {
            _eventDispatcher=new EventDispatcher();
            resourceManager=ResourceManager.getInstance();
            resourceManager.addEventListener(Event.CHANGE, changeResourceManagerhandler);
        }

        public function changeResourceManagerhandler(event:Event):void {
            dispatchEvent(event); 
        }

        [Bindable("change")]
        public static function getInstance():I18nManager {
            if (!instance) {
                instance=new I18nManager();
            }
            return instance;
        }

        public function getLocales():Array {
            return resourceManager.getLocales();
            //dispatchEvent(Event.CHANGE
        }


        [Bindable("change")]
        override flash_proxy function getProperty(name:*):* {
            var propName:String=name.toString();
            var propValue:String=getString(propName);
            return propValue;
        }

        [Bindable("change")]
        public function getBoolean(key:String, locale:String=null):Boolean {
            var msg:Boolean=resourceManager.getBoolean(bundleName, key, locale);
            return msg;
        }

        [Bindable("change")]
        public function getInt(key:String, locale:String=null):int {
            var msg:int=resourceManager.getInt(bundleName, key, locale);
            return msg;
        }

        [Bindable("change")]
        public function getNumber(key:String, locale:String=null):Number {
            var msg:Number=resourceManager.getNumber(bundleName, key, locale);
            return msg;
        }

        [Bindable("change")]
        public function getString(key:String, parameters:Array=null, locale:String=null):String {
            var msg:String=resourceManager.getString(bundleName, key, parameters, locale);
            if (msg == null) {
                msg=key;
            }
            return msg;
        }

        [Bindable("change")]
        public function getStringArray(key:String, locale:String=null):Array {
            var msg:Array=resourceManager.getStringArray(bundleName, key, locale);
            return msg;
        }

        [Bindable("change")]
        public function getUint(key:String, parameters:Array=null, locale:String=null):uint {
            var msg:uint=resourceManager.getUint(bundleName, key, locale);
            return msg;
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

