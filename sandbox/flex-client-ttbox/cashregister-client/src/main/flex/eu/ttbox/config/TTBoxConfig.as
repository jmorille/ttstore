package eu.ttbox.config {


    import eu.ttbox.config.model.ConnexionConfig;
    import eu.ttbox.controler.admin.AdminModule;
    import eu.ttbox.controler.cashregister.CashRegisterModule;
    import eu.ttbox.controler.core.CoreModule;

    import flash.desktop.NativeApplication;
    import flash.events.Event;

    import mx.controls.Alert;
    import mx.events.AIREvent;
    import mx.logging.Log;
    import mx.logging.targets.TraceTarget;

    import org.granite.events.SecurityEvent;
    import org.granite.tide.data.DataObserver;
    import org.granite.tide.data.OptimisticLockExceptionHandler;
    import org.granite.tide.events.TideUIEvent;
    import org.granite.tide.spring.Spring;
    import org.granite.tide.validators.ValidatorExceptionHandler;

    public class TTBoxConfig {

        private var _settings:AppConfigLoader=AppConfigLoader.getInstance();

        private static var instance:TTBoxConfig=new TTBoxConfig();


        public function TTBoxConfig() {
        }

        public static function getInstance():TTBoxConfig {
            return instance;
        }

        public function applicationPreinitialize():void {
            Spring.getInstance().initApplication();
            // read Configuration File
            //TODO _settings.addEventListener(Event.COMPLETE, onConfigLoaded);
            //TODO _settings.loadConfigFile("assets/appconfig.xml"); //relative link to config file
            onConfigLoaded(null);
        }

        private function onConfigLoaded(event:Event):void {
            if (event) {
                _settings.removeEventListener(Event.COMPLETE, onConfigLoaded);
            }
            // Read Parameter
            // like  var val:String=_settings.remotingEndpoint;
            // Static Tide Init
            var server:ConnexionConfig=_settings.config.connector;
            Spring.getInstance().addComponentWithFactory("serviceInitializer", TTBoxServiceInitialiser, {serverName: server.serverHost, serverPort: server.serverPort, contextRoot: server.contextRoot, protocol: server.protocol});
        }


        public function applicationCreationComplete():void {
            initLog();
            // Load Module
            Spring.getInstance().addModule(CoreModule);
            Spring.getInstance().addModule(AdminModule);
            Spring.getInstance().addModule(CashRegisterModule);

            var springInstance:Spring=Spring.getInstance();

            // Exeption handling
            springInstance.addExceptionHandler(ValidatorExceptionHandler);
            springInstance.addExceptionHandler(OptimisticLockExceptionHandler);
            springInstance.addExceptionHandler(SecurityExceptionHandler);


            // Gravity
            springInstance.addComponent("dataTopic", DataObserver, false, true);
            springInstance.addEventObserver("org.granite.tide.login", "dataTopic", "subscribe");
            springInstance.addEventObserver("org.granite.tide.logout", "dataTopic", "unsubscribe");

            //springInstance.addEventObserver(SecurityEvent.ALL, "securityEventManager", "onSecurityEvent");

            //TODO springInstance.addEventObserver(Event.EXITING, "dataTopic", "unsubscribe");

            //TODO  NativeApplication.nativeApplication.addEventListener(Event.EXITING, handleNativeApplicationExiting);
        }

        public function handleNativeApplicationExiting(event:Event):void {
            trace("Application Exiting", Event.EXITING.toString());
        }

        private function initLog():void {
            // Logging
            var t:TraceTarget=new TraceTarget();
            t.filters=["org.granite.*", "eu.ttbox.*"];
            Log.addTarget(t);

        }

    }
}

