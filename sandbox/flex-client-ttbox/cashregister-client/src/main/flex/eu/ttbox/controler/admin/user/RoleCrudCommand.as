package eu.ttbox.controler.admin.user {
    import eu.ttbox.controler.admin.AdminTideError;
    import eu.ttbox.model.user.Role;
    import eu.ttbox.service.user.RoleService;
    import eu.ttbox.view.admin.core.IEntityCrudCommand;

    import flash.events.EventDispatcher;

    import mx.collections.ListCollectionView;
    import mx.data.utils.Managed;

    import org.granite.tide.events.TideResultEvent;

    [Name("roleCrudCommand", restrict="true")]
    public class RoleCrudCommand implements IEntityCrudCommand {

        [Bindable]
        public var entity:Role;

        public var service:RoleService;

        [Bindable]
        public var resultList:ListCollectionView;


        public function setSelectedEntity(item:*):void {
            this.entity=item as Role;
        }

        public function getSelectedEntity():* {
            return entity;
        }


        public function doSearch():void {
            service.getAll(resultHandler, AdminTideError.saveFaultHandler);
        }

        private function resultHandler(event:TideResultEvent):void {
            resultList=event.result as ListCollectionView;
        }

        public function doSave():void {
            if (isNaN(entity.id)) {
                service.persist(entity, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
                doSearch();
            } else {
                service.merge(entity, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
            }
        }

        public function doRemove():void {
            service.remove(entity, AdminTideError.saveResultHandler, AdminTideError.saveFaultHandler);
            doSearch();
            entity=null;
        }

        public function doCancel():void {
            Managed.resetEntity(entity);
        }

        public function doCreateNewEntity():void {
            entity=new Role();
        }


    }
}