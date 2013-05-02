package eu.ttbox.controler.admin.user {
    import eu.ttbox.controler.admin.AdminTideError;
    import eu.ttbox.model.user.GroupRole;

    import eu.ttbox.service.user.GroupRoleService;
    import eu.ttbox.view.admin.core.IEntityCrudCommand;

    import flash.events.EventDispatcher;

    import mx.collections.ListCollectionView;
    import mx.data.utils.Managed;

    import org.granite.tide.events.TideResultEvent;

    [Name("groupRoleCrudCommand", restrict="true")]
    public class GroupRoleCrudCommand implements IEntityCrudCommand {

        [Bindable]
        public var entity:GroupRole;

        public var service:GroupRoleService;

        [Bindable]
        public var resultList:ListCollectionView;


        public function setSelectedEntity(item:*):void {
            this.entity=item as GroupRole;
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
            entity=new GroupRole();
        }


    }
}