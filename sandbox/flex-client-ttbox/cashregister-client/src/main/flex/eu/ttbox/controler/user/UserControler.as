package eu.ttbox.controler.user {
    import eu.ttbox.service.user.UserService;

    import mx.collections.ListCollectionView;
    import mx.controls.Alert;

    import org.granite.tide.events.TideFaultEvent;
    import org.granite.tide.events.TideResultEvent;

    public class UserControler {

        [Inject]
        public var userService:UserService;

        [Bindable]
        public var users:ListCollectionView;


        public function UserControler() {
        }

        public function getAll():void {
            userService.getAll(resultHandler, faultHandler);
        }

        private function resultHandler(event:TideResultEvent):void {
            users=event.result as ListCollectionView;
        }

        private function faultHandler(event:TideFaultEvent):void {
            Alert.show(event.fault.message, "Fault result");
            // todo Display Message Cancel
        }
    }
}