package org.certificatic.cui.client.client;

import org.certificatic.cui.client.impl.DoorImpl;
import org.certificatic.cui.door.api.Door;

public class ClientDoor {
	
	static Door door;
	
	public static void main(String[] args) {
		
		door = new DoorImpl();
		
		String name = "Tu nombre";
		
		door.open(name);
		
		// door.close(name);
		
	    // door.alarm(name);
		
	}
	

	private static void openDoor(String name){
		door.open(name);
	}
	
	
	private static void closeDoor(String name){
		door.open(name);
	}
	
	private static void warning(String name){
		door.open(name);
	}


}
