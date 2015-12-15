package last.stand;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;
import java.util.ArrayList;
import java.util.Random;


public class Game extends BasicGameState {
    
    public static int score=0,round=0,kills=0,money=10000,ammo;
    private int x=-2,y=-14,x2=17,y2=27,x3=42,y3=41;
    
    private Animation a;
    
    public static String weapon=" ";
    
    private TiledMap map;
    
    private Image heart;
    private Image chestClosed;
    private Image doorUp,doorDown,doorRight,doorLeft;
    
    private boolean heart1=true,heart2=true,heart3=true;
    private boolean door1Open=false,door2Open=false,door3Open=false,door4Open=false,door5Open=false,door6Open=false;
    private boolean buyDoor=false;
    private boolean start=false;
    
    private ArrayList<Integer> zombieX = new ArrayList();
    private ArrayList<Integer> zombieY = new ArrayList();
    private ArrayList<Integer> moveC = new ArrayList();
    
    private ArrayList<Boolean> toMove = new ArrayList();
    
    private int[][] zombieSpawn=new int[3][2];
    private int[] position=new int[3];
    
    
    private int time=0,test=0;
    
    Random randomGenerator = new Random();
            
    public Game(int state){
        
    }
    //base code for all animation
//    public Animation getAnimation ( Image i , int spritesX, int spritesY , int spriteWidth , int spriteHeight, int frames, int duration )
//	{
//		Animation a = new Animation(false);
//		
//		int c = 0;
//		for( int y = 0 ; y < spritesY; y++)
//		{
//			for( int x = 0 ; x < spritesX; x++)
//			{
//				if( c < frames ) a.addFrame( i.getSubImage(x*spriteWidth, y*spriteHeight, spriteWidth, spriteHeight), duration);
//				c++;
//			}
//		}
//		
//		return a;
//	}
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
    map=new TiledMap("res/map_game.tmx");
    heart=new Image("res/heart_pixel.png");
    chestClosed=new Image("res/mystery_box_closed.png");
    doorUp=new Image("res/door_for_game_up (2).png");
    doorDown=new Image("res/door_for_game_down (1).png");
    doorRight=new Image("res/door_for_game_right (1).png");
    doorLeft=new Image("res/door_for_game_left (1).png");
   // Image i = new Image("res/zombie_animation.png");
   // a = getAnimation ( i, 8 , 1 , 45, 52, 32, 150 );
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
    // rendering all images/strings to screen
    map.render(x*32, y*32); 
    g.drawString("Score: "+score,25,25);
    g.drawString("Round: "+round,125,25);
    g.drawString("Kills: "+kills,225,25);
    g.drawString("Money: $"+money,355,25);
    g.drawString("Weapon: "+weapon, 625, 25);
    g.drawString("Ammo: "+ammo, 525, 25);
    if(heart1==true){heart.draw(850, 20);}
    if(heart2==true){heart.draw(900, 20);}
    if(heart3==true){heart.draw(950, 20);}
    chestClosed.draw(x*32+1408,y*32+1760);
    g.fillRect(480, 416, 32, 32);
   // a.draw(300, 400);
    
    if(door1Open==false){ //rendering botton door in main room
        doorUp.draw(x*32+480,y*32+1088);
        doorUp.draw(x*32+512,y*32+1088);
        doorUp.draw(x*32+544,y*32+1088);
        doorUp.draw(x*32+576,y*32+1088);
    }
    if(door2Open==false){ //rendering top right door in main room
        doorLeft.draw(x*32+864,y*32+736);
        doorLeft.draw(x*32+864,y*32+768);
        doorLeft.draw(x*32+864,y*32+800);
        doorLeft.draw(x*32+864,y*32+832);
    }
    if(door3Open==false){ //rendering bottom left room top left door
        doorRight.draw(x*32+352,y*32+2112);
        doorRight.draw(x*32+352,y*32+2144);
        doorRight.draw(x*32+352,y*32+2176);
        doorRight.draw(x*32+352,y*32+2208);
    }
    if(door4Open==false){ //rendering middel room botton door 
        doorUp.draw(x*32+1280,y*32+2208);
        doorUp.draw(x*32+1312,y*32+2208);
        doorUp.draw(x*32+1344,y*32+2208);
        doorUp.draw(x*32+1376,y*32+2208);
    }
    if(door5Open==false){ //rendering bottom right room botton door 
        doorUp.draw(x*32+2304,y*32+2240);
        doorUp.draw(x*32+2336,y*32+2240);
        doorUp.draw(x*32+2368,y*32+2240);
        doorUp.draw(x*32+2400,y*32+2240);
    }
    if(door6Open==false){ //rendering bottom right room top left door 
        doorRight.draw(x*32+2016,y*32+1632);
        doorRight.draw(x*32+2016,y*32+1664);
        doorRight.draw(x*32+2016,y*32+1696);
        doorRight.draw(x*32+2016,y*32+1728);
        doorRight.draw(x*32+2016,y*32+1760);
    }
    if(buyDoor==true){g.drawString("$750 (E)", 464, 400);}
    g.setColor(Color.red);
    for(int i=0;i<zombieX.size();i++){
         g.fillRect(x*32+zombieX.get(i)*32,y*32+zombieY.get(i)*32,32,32);
    }
//    if(zombieX.size()>=2){g.fillRect(x*32+zombieX.get(1)*32,y*32+zombieY.get(1)*32,32,32);}
   // g.fillRect(x*32+736,y*32+576,32,32);
   // g.fillRect(x*32+896,y*32+992,32,32);
     }
     
    public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
     int objectLayer = map.getLayerIndex("Tile Layer 1");
     
    // a.update(delta);
     
     //getting the zombie to come out of its spawn and zombie spawning
    if(!start){
        //up=1,down=2,left=3,right=4;
        position[0]=2;
        position[1]=2;
        position[2]=3;
        
        
        zombieX.add(12);
        zombieY.add(18);
        toMove.add(true);
        moveC.add(0);
        start=true;
        zombieSpawn[0][0]=12;
        zombieSpawn[0][1]=18;
        zombieSpawn[1][0]=23;
        zombieSpawn[1][1]=18;
        zombieSpawn[2][0]=28;
        zombieSpawn[2][1]=31;
        
    }
    time+=delta;
    if(time>=500){
        moveOut();
        int randomInt = randomGenerator.nextInt(3);
        System.out.println("randomInt: "+randomInt);
        
        //Spawning
        
        //Up
        if(position[randomInt]==1){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieX.get(i)==zombieSpawn[randomInt][0] && (zombieY.get(i)==zombieSpawn[randomInt][1] || zombieY.get(i)==zombieSpawn[randomInt][1]-1)){
                        test=1;
                        break;
                }
            }
            if(test==0){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
            }
        }
        
        //Down
        if(position[randomInt]==2){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieX.get(i)==zombieSpawn[randomInt][0] && (zombieY.get(i)==zombieSpawn[randomInt][1] || zombieY.get(i)==zombieSpawn[randomInt][1]+1)){
                        test=1;
                        break;
                }
            }
            if(test==0){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
            }
        }
        
        //Left
        if(position[randomInt]==3){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieY.get(i)==zombieSpawn[randomInt][1] && (zombieX.get(i)==zombieSpawn[randomInt][0] || zombieX.get(i)==zombieSpawn[randomInt][0]-1)){
                        test=1;
                        break;
                }
            }
            if(test==0){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
            }
        }
        
        //Right
        if(position[randomInt]==2){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieY.get(i)==zombieSpawn[randomInt][1] && (zombieX.get(i)==zombieSpawn[randomInt][0] || zombieX.get(i)==zombieSpawn[randomInt][0]+1)){
                        test=1;
                        break;
                }
            }
            if(test==0){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
            }
        }
        

        
        time=0;
    }
    
     //to move right and checking for doors
     if(((x2==10&&y2==66)||(x2==10&&y2==67)||(x2==10&&y2==68)||(x2==10&&y2==69))&&!door3Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door3Open=true;
             money-=750;
         }
     }
     else if(((x2==26&&y2==23)||(x2==26&&y2==24)||(x2==26&&y2==25)||(x2==26&&y2==26))&&!door2Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door2Open=true;
             money-=750;
         }
     }
     else if(((x2==62&&y2==51)||(x2==62&&y2==52)||(x2==62&&y2==53)||(x2==62&&y2==54)||(x2==62&&y2==55))&&!door6Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door6Open=true;
             money-=750;
         }
     }
     else if(gc.getInput().isKeyPressed(Input.KEY_D)){
         if(map.getTileId(x2+1,y2,objectLayer)==0){
            x--;
            x2++;
            buyDoor=false;
         }
     }
     //to move up and checking for doors
     if(((x2==15&&y2==35)||(x2==16&&y2==35)||(x2==17&&y2==35)||(x2==18&&y2==35))&&!door1Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door1Open=true;
             money-=750;
         }
     }
     else if(((x2==40&&y2==70)||(x2==41&&y2==70)||(x2==42&&y2==70)||(x2==43&&y2==70))&&!door4Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door4Open=true;
             money-=750;
         }
     }
     else if(((x2==72&&y2==71)||(x2==73&&y2==71)||(x2==74&&y2==71)||(x2==75&&y2==71))&&!door5Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door5Open=true;
             money-=750;
         }
     }
     else if(gc.getInput().isKeyPressed(Input.KEY_W)){
         if(map.getTileId(x2,y2-1,objectLayer)==0 ){
            y++;
            y2--;
            buyDoor=false;
         }
     }
     //to move left and checking for doors
     if(((x2==12&&y2==66)||(x2==12&&y2==67)||(x2==12&&y2==68)||(x2==12&&y2==69))&&!door3Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door3Open=true;
             money-=750;
         }
     }
     else if(((x2==28&&y2==23)||(x2==28&&y2==24)||(x2==28&&y2==25)||(x2==28&&y2==26))&&!door2Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door2Open=true;
             money-=750;
         }
     }
     else if(((x2==64&&y2==51)||(x2==64&&y2==52)||(x2==64&&y2==53)||(x2==64&&y2==54)||(x2==64&&y2==55))&&!door6Open){
         buyDoor=true;
         if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door6Open=true;
             money-=750;
         }
     }
     else if(gc.getInput().isKeyPressed(Input.KEY_A)){
         if(map.getTileId(x2-1,y2,objectLayer)==0){
            x++;
            x2--;
            buyDoor=false;
         }
     }
     //to move down and checking for doors
    if(((x2==15&&y2==33)||(x2==16&&y2==33)||(x2==17&&y2==33)||(x2==18&&y2==33))&&!door1Open){
        buyDoor=true;
        if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door1Open=true;
             money-=750;
         }
    }
    else if(((x2==40&&y2==68)||(x2==41&&y2==68)||(x2==42&&y2==68)||(x2==43&&y2==68))&&!door4Open){
        buyDoor=true;
        if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door4Open=true;
             money-=750;
         }
    }
    else if(((x2==72&&y2==69)||(x2==73&&y2==69)||(x2==74&&y2==69)||(x2==75&&y2==69))&&!door5Open){
        buyDoor=true;
        if(gc.getInput().isKeyPressed(Input.KEY_E ) && money>=750){
             door5Open=true;
             money-=750;
         }
    }
    else if(gc.getInput().isKeyPressed(Input.KEY_S)){
        if(map.getTileId(x2,y2+1,objectLayer)==0){
            y--;
            y2++;
            buyDoor=false;
        }
    }
    
     
     
//     System.out.println("x: "+zombieX.get(0));
//     System.out.println("y: "+zombieY.get(0));
//     System.out.println("Zombie Spawn X: "+zombieSpawn[0][0]);
//     System.out.println("Zombie Spawn Y: "+zombieSpawn[0][1]);
//     
     }
    
    public void moveOut(){
        if(time>=500){
            for(int i=0;i<toMove.size();i++){
                if(toMove.get(i)==true&&moveC.get(i)<2){
                    moveC.set(i,moveC.get(i)+1);
                    zombieX.set(i,zombieX.get(i)+1);
                    zombieY.set(i,zombieY.get(i)+1);
                    if(moveC.get(i)==2){
                        toMove.set(i,true);
                    }
                }
            }
        }
    }
     
      public int getID(){
        return 3;
    }
}
