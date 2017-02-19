package org.certificatic.cui.door.api;

public interface Door {

       String URI = "https://api.particle.io/v1/devices/2a003f000551343530343432/RGB_LED";
       String ACCESS_TOKE_NAME = "access_token";
       String ACCESS_TOKEN_VALUE = "040661324a096a54a9c46378a765a79247d1c873";
       String OPERATION = "args";
    
	String CLOSE = "red";
	String OPEN = "green";
	String ALARM = "blue";
	
	
	
	public boolean open(String name);
	
	public boolean close(String name);
	
	public boolean alarm(String name);

}
