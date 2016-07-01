package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

import models.TexturedModel;
import renderEngine.DisplayManager;

public class Player extends Entity{

	private static final float RUN_SPEED = 80;
	private static final float TURN_SPEED = 160;
	private static final float GRAVITY = -50;
	private static final float JUMP_POWER = 30;
	
	private static final float TERRAIN_HEIGHT = 1.5f;
	private boolean isInAir = false;
	
	
	private float currentSpeed = 0;
	private float currentTurnSpeed = 0;
	private float upwardsSpeed = 0;
	
	public Player(TexturedModel model, Vector3f position, float rotX, float rotY, float rotZ, float scale) {
		super(model, position, rotX, rotY, rotZ, scale);
		
	}
	
	public void move(){
		checkInputs();
		super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
		float distance = currentSpeed * DisplayManager.getFrameTimeSeconds();
		float dx = distance * (float)(distance * Math.sin(Math.toRadians(super.getRotY())));
		float dz = distance * (float)(distance * Math.cos(Math.toRadians(super.getRotY())));
		super.increasePosition(dx, 0, dz);	
		upwardsSpeed += GRAVITY * DisplayManager.getFrameTimeSeconds();
		super.increasePosition(0, upwardsSpeed * DisplayManager.getFrameTimeSeconds(),
				0);
		if(super.getPosition().y<TERRAIN_HEIGHT){
			upwardsSpeed = 0;
			isInAir = false;
			super.getPosition().y = TERRAIN_HEIGHT;
			
		}
		
	}
	
	private void jump(){
		if(!isInAir){
		this.upwardsSpeed = JUMP_POWER;
		
		isInAir = true;
		}
	}

	private void checkInputs(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			this.currentSpeed = RUN_SPEED;
		//	this.upwardsSpeed = JUMP_POWER/3;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			this.currentSpeed = -RUN_SPEED;
		}else{
			this.currentSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			this.currentTurnSpeed = -TURN_SPEED;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			this.currentTurnSpeed = TURN_SPEED;
		}else{
			this.currentTurnSpeed = 0;
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			jump();
			
		}
		boolean shot = false;
		if(Keyboard.isKeyDown(Keyboard.KEY_L)==true){
		shot = true;
			}
		
		while(shot==true){
			super.increaseRotation(0, currentTurnSpeed * DisplayManager.getFrameTimeSeconds(), 0);
			super.increasePosition(30*DisplayManager.getFrameTimeSeconds(), 60*DisplayManager.getFrameTimeSeconds(), -60*DisplayManager.getFrameTimeSeconds());	
		
		break;
		}
		 float j = -1;float i=-2*j;
		
		if(Keyboard.isCreated()){}
    	if(super.getPosition().x!=100 && super.getPosition().y!=1&&super.getPosition().z!=-49&&Keyboard.isKeyDown(Keyboard.KEY_M)){
    	
    		super.increasePosition(i, 20*DisplayManager.getFrameTimeSeconds(), j);
    		
			
    	
    	
    			}
    	
		
		
	}

}
	

