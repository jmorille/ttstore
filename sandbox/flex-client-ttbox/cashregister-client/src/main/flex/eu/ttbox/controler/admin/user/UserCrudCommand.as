package eu.ttbox.controler.admin.user {
    import eu.ttbox.controler.admin.AdminTideError;
    import eu.ttbox.model.user.User;
    import eu.ttbox.service.bean.SearchCriteria;
    import eu.ttbox.service.user.UserService;
    import eu.ttbox.view.admin.core.IEntityCrudCommand;

    import flash.events.EventDispatcher;

    import mx.collections.ListCollectionView;
    import mx.data.utils.Managed;

    import org.granite.tide.events.TideResultEvent;
    import org.granite.tide.spring.PagedQuery;

    [Name("userCrudCommand", restrict="true")]
    public class UserCrudCommand implements IEntityCrudCommand {

        [Bindable]
        public var entity:User;

        [Inject]
        public var service:UserService;

        [In("userList")]
        [Bindable]
        public var searchResult:PagedQuery;

        [Bindable("change")]
        public function get resultList():ListCollectionView {
            return searchResult;
        }

        public function setSelectedEntity(item:*):void {
            this.entity=item as User;
        }

        public function getSelectedEntity():* {
            return entity;
        }


        public function get searchFilter():SearchCriteria {
            return searchResult.filter as SearchCriteria;
        }

        public function doSearch():void {
            searchResult.fullRefresh();
            //  service.getAll(resultHandler, AdminTideError.saveFaultHandler);
        }

        /*        private function resultHandler(event:TideResultEvent):void {
           resultList=event.result as ListCollectionView;
         }*/

        public function doSave():void {
            if (isNaN(entity.id)) {
                service.persist(entity, saveResultHandler, AdminTideError.saveFaultHandler);
//                doSearch();
            } else {
                service.merge(entity, saveResultHandler, AdminTideError.saveFaultHandler);
            }
        }

        private function saveResultHandler(event:TideResultEvent):void {
            doSearch();
            AdminTideError.saveResultHandler(event);
        }

        public function doRemove():void {
            service.remove(entity, saveResultHandler, AdminTideError.saveFaultHandler);
            entity=null;
        }

        public function doCancel():void {
            Managed.resetEntity(entity);
        }

        public function doCreateNewEntity():void {
            entity=new User();
            entity.enabled=true;
            entity.credentialsNonExpired=true;
            entity.accountNonExpired=true;
            entity.accountNonLocked=true;
        }


    }
}