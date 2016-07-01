package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.RawModel;
import models.TexturedModel;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrains.Terrain;
import textures.ModelTexture;
import textures.TerrainTexture;
import textures.TerrainTexturePack;
import entities.Camera;
import entities.Entity;
import entities.Light;
import entities.Player;

public class MainGameLoop {

	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		

		///Input start
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grassy"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("dirt"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("pinkFlowers"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));
		
		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture,rTexture,
				gTexture,bTexture);
		
		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));
		
		///Input end
		
		RawModel model = OBJLoader.loadObjModel("tree", loader);
		
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("tree")));
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("fern", loader),
	    		new ModelTexture(loader.loadTexture("fern")));
		TexturedModel cup = new TexturedModel(OBJLoader.loadObjModel("sspanzerkorps", loader),
	    		new ModelTexture(loader.loadTexture("grass_color")));
		
		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassModel", loader),
	    		new ModelTexture(loader.loadTexture("grassTexture")));
		grass.getTexture().setUseFakeLighting(true);
		fern.getTexture().setHasTransparency(true);
		grass.getTexture().setHasTransparency(true);
		
		
		List<Entity> entities = new ArrayList<Entity>();
		
		
		Random random = new Random();
		entities.add(new Entity(cup, new Vector3f(100,0,-50),0,0,0,3));
		for(int i=0;i<50;i++){
			entities.add(new Entity(staticModel, new Vector3f(random.nextFloat()*800 - 400,0,
					random.nextFloat() * -600),0,0,0,3));
			
			
			
			entities.add(new Entity(grass, new Vector3f(random.nextFloat()*800 - 400,0,
					random.nextFloat() * -600),0,0,0,3));
			entities.add(new Entity(fern, new Vector3f(random.nextFloat()*800 - 400,0,
					random.nextFloat() * -600),0,0,0,0.6f));
			
		}
		
		Light light = new Light(new Vector3f(20000,20000,2000),new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(0,0,loader,texturePack,blendMap,"heightmap");
		Terrain terrain2 = new Terrain(1,0,loader,texturePack,blendMap,"heightmap");
		
			
		MasterRenderer renderer = new MasterRenderer();
		
		RawModel bunnyModel = 
	OBJLoader.loadObjModel("golfBall", loader);
		
		TexturedModel stanfordBunny = 
	new TexturedModel(bunnyModel, new ModelTexture(loader.loadTexture("hz")));
	Player player = new Player(stanfordBunny, 
	new Vector3f(0,0,0),0,1,0,0.6f);
		
	Camera camera = new Camera(player);
	boolean AI=false;
		while(!Display.isCloseRequested()){
			camera.move();
			player.move();
			///0System.out.println((int)(player.getPosition().x)+" "+(int)(player.getPosition().y)+" "+(int)(player.getPosition().z));
		//	System.out.println(+" "+player.getRotY()+" "+player.getRotZ());
		
			
		
	    if(((int)(player.getPosition().x) == 98  && (int)(player.getPosition().y)==1 && (int)(player.getPosition().z)==-49) ||
	    		((int)(player.getPosition().x) == 99   && (int)(player.getPosition().y)==1 && (int)(player.getPosition().z)==-51||
	    		((int)(player.getPosition().x) == 99   && (int)(player.getPosition().y)==1 && (int)(player.getPosition().z)==-49)||
	    		((int)(player.getPosition().x) == 102   && (int)(player.getPosition().y)==1 && (int)(player.getPosition().z)==-50))	
	    		){
					
	    
	    	
	    	break;
					}
			
			renderer.processEntity(player);
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			for(Entity entity:entities){
				renderer.processEntity(entity);
			}
			renderer.render(light, camera);
			DisplayManager.updateDisplay();
		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();

	}
	
	
	
	public void AI(){
		
		Player bot = new Player(null, null, 0, 0, 0, 0);
		
	}

}
