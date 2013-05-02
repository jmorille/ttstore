package eu.ttbox.config {


    import eu.ttbox.controler.admin.fileimporter.FileImporterModule;

    import flash.events.Event;

    import mx.logging.Log;
    import mx.logging.targets.TraceTarget;

    import org.granite.tide.data.DataObserver;
    import org.granite.tide.data.OptimisticLockExceptionHandler;
    import org.granite.tide.spring.Spring;
    import org.granite.tide.validators.ValidatorExceptionHandler;

    public class TTBoxConfig {


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
            // Read Parameter
            // like  var val:String=_settings.remotingEndpoint;
            // Static Tide Init

            Spring.getInstance().addComponentWithFactory("serviceInitializer", TTBoxServiceInitialiser, {serverName: "localhost", serverPort: "8080", contextRoot: "/ttbox"});
        }


        public function applicationCreationComplete():void {
            initLog();
            trace("TTBoxonfig applicationCreationComplete");
            // Load Module
//            Spring.getInstance().addModule(FileImporterModule);

            var springInstance:Spring=Spring.getInstance();

            // Exeption handling
            springInstance.addExceptionHandler(ValidatorExceptionHandler);
            springInstance.addExceptionHandler(OptimisticLockExceptionHandler);

            // Gravity
            springInstance.addComponent("dataTopic", DataObserver, false, true);
            springInstance.addEventObserver("org.granite.tide.login", "dataTopic", "subscribe");
            springInstance.addEventObserver("org.granite.tide.logout", "dataTopic", "unsubscribe");

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

