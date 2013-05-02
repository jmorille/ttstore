package com.cestpasdur.domain
{
	import mx.collections.ArrayCollection;

	[RemoteClass(alias="com.cestpasdur.domain.Groupe")]
	[Bindable]
	public class Groupe
	{
		public var id:int;
		
		public var groupes:ArrayCollection
		
		public var libelle:String;
		
		public function Groupe()
		{
		}
	}
}