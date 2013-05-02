package eu.ttbox.controler.admin {
    import mx.controls.ProgressBar;
    import mx.controls.ProgressBarMode;
    import mx.effects.Fade;
    import mx.events.ModuleEvent;
    import mx.modules.ModuleLoader;

    public class CustomModuleLoader extends ModuleLoader {
        private var loadbar:ProgressBar=new ProgressBar();

        public function CustomModuleLoader() {
            super();
            addEventListener(ModuleEvent.PROGRESS, onModuleProgress);
            addEventListener(ModuleEvent.READY, onModuleReady);
            addChild(loadbar);
        }

        private function onModuleProgress(event:ModuleEvent):void {
            loadbar.mode=ProgressBarMode.MANUAL;
            var progress:int=(event.bytesLoaded / event.bytesTotal) * 100;
            loadbar.setProgress(progress, 100);
        }

        private function onModuleReady(event:ModuleEvent):void {
            var fade:Fade=new Fade();
            fade.target=child;
            fade.alphaFrom=0;
            fade.alphaTo=1;
            fade.duration=2000;
            if (child) {
                child.alpha=0;
            }
            removeChild(loadbar);
            fade.play();
        }
    }
}