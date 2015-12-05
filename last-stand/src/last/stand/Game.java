package last.stand;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;


public class Game extends BasicGameState {
    
    public static int score=0,round=0,kills=0,money=500,ammo;
    public static String weapon=" ";
    private TiledMap map;
    private int x=15,y=13;
    
            
    public Game(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
    map=new TiledMap("res/map_game.tmx");
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
    g.drawString("Score: "+score,25,25);
    g.drawString("Round: "+round,225,25);
    g.drawString("Kills: "+kills,425,25);
    g.drawString("Money: "+money,625,25);
    g.drawString("Weapon: "+weapon, 825, 25);
    g.drawString("Ammo: "+ammo, 1000, 25);
    map.render(-64, -448); 
    g.fillRect(x*32,y*32, 32, 32);
    
     }
     
     public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
     int objectLayer = map.getLayerIndex("Tile Layer 1");
     int objectLayer2 = map.getLayerIndex("details");
   
     
     
     
     if(gc.getInput().isKeyPressed(Input.KEY_D)){
         if(map.getTileId(x+1,y,objectLayer)==266){
            x++;   
         }
     }
     
     if(gc.getInput().isKeyPressed(Input.KEY_W)){
         if(map.getTileId(x+1,y,objectLayer)==266){
            y--;   
         }
     }
     
     if(gc.getInput().isKeyPressed(Input.KEY_A)){
         if(map.getTileId(x+1,y,objectLayer)==266){
            x--;   
         }
     }
     
     if(gc.getInput().isKeyPressed(Input.KEY_S)){
         if(map.getTileId(x+1,y,objectLayer)==266){
            y++;   
         }
     }
     
     System.out.println(map.getTileId(25,y,objectLayer));
     System.out.println(map.getTileId(25,y,objectLayer2));
     }
     
      public int getID(){
        return 3;
    }
}
