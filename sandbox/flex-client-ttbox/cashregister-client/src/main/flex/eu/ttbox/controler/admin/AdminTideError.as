package eu.ttbox.controler.admin {
    import mx.controls.Alert;

    import org.granite.tide.events.TideFaultEvent;
    import org.granite.tide.events.TideResultEvent;

    public class AdminTideError {

        public function AdminTideError() {
        }

        public static function saveResultHandler(event:TideResultEvent):void {
            // todo Display Message Succes
            trace("Save Result", event.result);
        }

        public static function saveFaultHandler(event:TideFaultEvent):void {
            trace("Save Fault", event.fault);
            Alert.show(event.fault.message, "Fault result");
            // todo Display Message Cancel
            // tod Cancel object or display Errors
        }
    }
}