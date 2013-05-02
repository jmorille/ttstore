package eu.ttbox.controler.admin {
    import eu.ttbox.controler.admin.catalog.CatalogAdminControler;
    import eu.ttbox.controler.admin.product.ProductControler;
    import eu.ttbox.controler.admin.salespoint.HoldingControler;
    import eu.ttbox.controler.admin.user.UserCrudCommand;
    import eu.ttbox.controler.user.UserControler;
    import eu.ttbox.service.bean.SearchCriteria;

    import org.granite.tide.ITideModule;
    import org.granite.tide.Tide;
    import org.granite.tide.spring.PagedQuery;

    public class AdminModule implements ITideModule {

        public function AdminModule() {
        }


        public function init(tide:Tide):void {
            tide.addComponent("holdingControler", HoldingControler, false, true, Tide.RESTRICT_YES);

            tide.addComponent("userControler", UserControler, false, true, Tide.RESTRICT_YES);
            tide.addComponent("productControler", ProductControler, false, true, Tide.RESTRICT_YES);
            tide.addComponent("catalogAdminControler", CatalogAdminControler, false, true, Tide.RESTRICT_YES);

//            tide.addComponentWithFactory("userList", PagedQuery, {remoteComponentName: "userService", methodName: "findUsers", filter: new SearchCriteria(), maxResults: 36});
            tide.addComponentWithFactory("userList", PagedQuery, {remoteComponentName: "userSearchService", methodName: "list", filter: new SearchCriteria(), maxResults: 36});
            tide.addComponents([UserCrudCommand]);

        }


    }
}