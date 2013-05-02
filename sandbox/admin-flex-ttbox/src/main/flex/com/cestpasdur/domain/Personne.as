package com.cestpasdur.domain
{
	[RemoteClass(alias="com.cestpasdur.domain.Personne")]
	[Bindable]
	public class Personne
	{
		
		public var id:int;
		
		public var firstName:String;
		
		public var lastName:String;
		
		public var matricule:String;
		
		
		
		
		public function Personne()
		{
		}
	}
}