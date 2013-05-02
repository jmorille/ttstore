package com.cestpasdur.controller{

import com.cestpasdur.model.PersonneModel;

import mx.collections.ArrayCollection;
import com.cestpasdur.event.PersonneEvent;
import mx.rpc.AsyncToken;
import mx.rpc.remoting.RemoteObject;

public class GetPersonneCommand{
	

	
	[Inject(id="personneService")]
	public var personneService:RemoteObject;
	
	[Inject]
	public var  personneModel:PersonneModel;
	
	public function execute (event:PersonneEvent) : AsyncToken {
		trace("Handling GetPersonneCommand.execute()");
		return personneService.getAll();
	}
	
	
}
}