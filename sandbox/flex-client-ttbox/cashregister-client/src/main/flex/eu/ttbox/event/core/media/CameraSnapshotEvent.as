package eu.ttbox.event.core.media {
    import flash.display.BitmapData;
    import flash.events.Event;
    import flash.utils.ByteArray;

    public class CameraSnapshotEvent extends Event {

        public static const SNAPSHOT:String="cameraSnapshot";

        public var data:Object;

        public function CameraSnapshotEvent(type:String, data:Object) {
            super(type);
            this.data=data;
        }

    }
}