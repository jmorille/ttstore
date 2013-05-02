package eu.ttbox.view.core.media {
    import flash.display.Bitmap;
    import flash.display.BitmapData;
    import flash.display.Sprite;
    import flash.geom.Matrix;
    import flash.media.Camera;
    import flash.media.Video;
    import flash.system.Security;
    import flash.system.SecurityPanel;
    import flash.utils.ByteArray;

    import mx.core.mx_internal;
    import mx.graphics.codec.JPEGEncoder;
    import mx.graphics.codec.PNGEncoder;
    import mx.utils.ObjectUtil;

    public class CameraStream extends Sprite {

        private var camera:Camera;
        private var video:Video;
        private var _cameraWidth:Number;
        private var _cameraHeight:Number;

        private static const DEFAULT_CAMERA_HEIGHT:Number=240;
        private static const DEFAULT_CAMERA_WIDTH:Number=320;
        private static const DEFAULT_CAMERA_FPS:Number=30;



        public function CameraStream(cam:Camera=null, cameraWidth:Number=NaN, cameraHeight:Number=NaN) {
            if (cameraWidth) {
                this._cameraWidth=cameraWidth;
            }
            if (cameraHeight) {
                this._cameraHeight=cameraHeight;
            }

            if (!_cameraHeight) {
                _cameraHeight=DEFAULT_CAMERA_HEIGHT;
            }
            if (!_cameraWidth) {
                _cameraWidth=DEFAULT_CAMERA_WIDTH;
            }
            setCamera(cam);
        }

        public function setCamera(cam:Camera):void {
            if (video != null) {
                removeChild(video);
                // video.attachCamera(undefined);
                video.clear();
                //trace("attachCamera null", ObjectUtil.toString(video));
                //  trace("clear 2", ObjectUtil.toString(video));
                video=null;
            }
            if (cam == null) {
                cam=Camera.getCamera();
                if (cam == null) {
                    Security.showSettings(SecurityPanel.CAMERA);
                    trace("Invalid Camera Specification");
                }

            }
            trace("Init Camera ", cam.name);

            this.camera=cam;
            this.camera.setMode(_cameraWidth, _cameraHeight, DEFAULT_CAMERA_FPS);
            video=new Video(this.camera.width, this.camera.height);
            video.attachCamera(this.camera);
            addChild(video);
        }

        public function setDimaension(width:Number, height:Number):void {
            this._cameraWidth=width;
            this._cameraHeight=height;
            initCameraMode();
        }

        public function get cameraWidth():Number {
            return this._cameraWidth;
        }

        public function set cameraWidth(width:Number):void {
            this._cameraWidth=width;
            initCameraMode();
        }

        public function get cameraHeight():Number {
            return this._cameraHeight;
        }

        public function set cameraHeight(height:Number):void {
            this._cameraHeight=height;
            initCameraMode();
        }

        private function initCameraMode():void {
            if (camera) {
                this.camera.setMode(_cameraWidth, _cameraHeight, DEFAULT_CAMERA_FPS);
            }
        }



        public function getSnapshot():Bitmap {
            var snapshotBitmap:Bitmap=new Bitmap(getSnapshotBitmapData());
            return snapshotBitmap;
        }

        public function getSnapshotJPEG(quality:Number=75):ByteArray {
            var jpg:JPEGEncoder=new JPEGEncoder(quality);
            return jpg.encode(getSnapshotBitmapData());
        }

        public function getSnapshotPNG():ByteArray {
            var pngEncoder:PNGEncoder=new PNGEncoder();
            return pngEncoder.encode(getSnapshotBitmapData());
        }

        public function getSnapshotBitmapData():BitmapData {
            var snapshot:BitmapData=new BitmapData(video.width, video.height, true);
            snapshot.draw(video, new Matrix());
            return snapshot;
        }

        public function setCameraByIndex(index:Number):void {
            setCamera(Camera.getCamera(index.toString()));
        }
    }
}