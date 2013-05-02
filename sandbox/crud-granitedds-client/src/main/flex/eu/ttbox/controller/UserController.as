package eu.ttbox.controller
{
	import eu.ttbox.model.user.GroupRole;
	import eu.ttbox.model.user.User;
	import eu.ttbox.service.user.UserService;
	
	import mx.collections.ListCollectionView;
	import mx.controls.Alert;
	import mx.data.utils.Managed;
	
	import org.granite.tide.events.TideFaultEvent;
	import org.granite.tide.events.TideResultEvent;
	import org.granite.tide.spring.Context;
	import org.granite.tide.spring.PagedQuery;
	
//	import org.swizframework.utils.services.ServiceHelper;

	[Name] 
	public class UserController
	{
		

		[Inject]
		public var tideContext:Context;

		[Inject]
		public var userService:UserService;

		[Bindable]
		public var currentUser:User = new User();

		[Bindable]  
		[In("#{userListService}")] 
		public var users:PagedQuery;

		
		public var userGroups:ListCollectionView=new ListCollectionView();

		[Bindable]
		public var currentGroup:GroupRole = new GroupRole();

		[PostConstruct]
		public function createDefaultUser():void
		{  
			
			//getAllUsers();
		}

 
		
		public function createNewUser():void {
			currentUser = new User();
		}
		
		
/*		public function getAllUsers():void
		{
			userService.getAll(resultHandler, tideFaultHandler);
		}*/
		
		 
		
	/*	private function resultHandler(event:TideResultEvent):void
		{
			users=event.result as ListCollectionView;
		}*/
		
		private function tideFaultHandler(event:TideFaultEvent):void
		{
			//strackTrace.text=event.fault.toString();
			Alert.show(event.fault.toString(), "Error Result Tide");
		}
		
		private function handleSaveUserResult(event:TideResultEvent):void
		{
			// Show an Alert just to make it obvious that the save was successful.
			Alert.show('User saved successfully!');
		}

		[Observer("eu.ttbox.event.RESET_USER_REQUESTED" )]
		public function resetUser():void {
			Managed.resetEntity(currentUser);
		}

		
		[Observer("eu.ttbox.event.CREATE_USER_REQUESTED" )]
		public function createUser(user:User):void {
			userService.add(user, handleSaveUserResult, tideFaultHandler);
		}
		
		[Observer("eu.ttbox.event.SAVE_USER_REQUESTED")]
		public function saveUser(user:User):void {
			userService.update(user, handleSaveUserResult, tideFaultHandler);
		}



	}
}