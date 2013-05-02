package eu.ttbox.controler.admin.fileimporter {

    import org.granite.tide.ITideModule;
    import org.granite.tide.Tide;

    public class FileImporterModule implements ITideModule {


        public function FileImporterModule() {
        }

        public function init(tide:Tide):void {
            trace("Init Tide Module FileImporterModule");
            tide.addComponent("productFileControler", ProductFileControler, false, true, Tide.RESTRICT_YES);

        }
    }
}