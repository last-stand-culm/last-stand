package last.stand;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.*;
import java.util.ArrayList;
import java.util.Random;


public class Game extends BasicGameState {
    
    public static int score=0,round=0,kills=0,money=10000,ammo;
    private int x=-2,y=-14,x2=17,y2=27,x3=42,y3=41,bCount=0,weaponselc=0;
    
    private Animation zombieWalkingUp,zombieWalkingLeft,zombieWalkingRight,zombieWalkingDown;
    private Animation playerWalkingUp,playerWalkingLeft,playerWalkingRight,playerWalkingDown;
    
    public static String weapon="Pistol ";
    
    private TiledMap map;
    
    private Image heart;
    private Image chestClosed;
    private Image doorUp,doorDown,doorRight,doorLeft;
    private Image box_Open2;
    
    private boolean heart1=true,heart2=true,heart3=true;
    private boolean door1Open=false,door2Open=false,door3Open=false,door4Open=false,door5Open=false,door6Open=false;
    private boolean buyDoor=false,buyChest=false;
    private boolean start=false;
    private boolean boxOpen2=false;
    private boolean m4a1=false,ump=false,nova=false,p250=true;
    private boolean boxStart=false;
    
    private ArrayList<Integer> zombieX = new ArrayList();
    private ArrayList<Integer> zombieY = new ArrayList();
    private ArrayList<Integer> moveC = new ArrayList();
    private ArrayList<Integer> pos = new ArrayList();
    
    private ArrayList<Boolean> toMove = new ArrayList();
    
    private int[][] zombieSpawn=new int[3][2];
    private int[] position=new int[3];
    
    
    private int time=0,test=0,cap=10,playerMovement=1;
    
    Random randomGenerator = new Random();
            
    public Game(int state){
        
    }
    //base code for all animation
    public Animation getAnimation ( Image i , int spritesX, int spritesY , int spriteWidth , int spriteHeight, int frames, int duration )
	{
		Animation a = new Animation(false);
		
		int c = 0;
		for( int y = 0 ; y < spritesY; y++)
		{
			for( int x = 0 ; x < spritesX; x++)
			{
				if( c < frames ) a.addFrame( i.getSubImage(x*spriteWidth, y*spriteHeight, spriteWidth, spriteHeight), duration);
				c++;
			}
		}
		
		return a;
	}
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
    map=new TiledMap("res/map_game.tmx");
    heart=new Image("res/heart_pixel.png");
    chestClosed=new Image("res/mystery_box_closed.png");
    doorUp=new Image("res/door_for_game_up (2).png");
    doorDown=new Image("res/door_for_game_down (1).png");
    doorRight=new Image("res/door_for_game_right (1).png");
    doorLeft=new Image("res/door_for_game_left (1).png");
    box_Open2=new Image("res/box_open.png");
    Image zombieWalking = new Image("res/zombie_animation.png");
    Image zombieWalkingL=new Image("res/zombie_animation_left.png");
    Image zombieWalkingR = new Image("res/zombie_animation_right.png");
    Image zombieWalkingD=new Image("res/zombie_animation_down.png");
    Image playerWalkingU = new Image("res/player_walking_up.png");
    Image playerWalkingL=new Image("res/player_walking_left.png");
    Image playerWalkingR = new Image("res/player_walking_right.png");
    Image playerWalkingD=new Image("res/player_walking_down.png");
    zombieWalkingUp = getAnimation ( zombieWalking, 8 , 1 , 45, 52, 54, 100 );
    zombieWalkingLeft= getAnimation( zombieWalkingL, 1 , 8 , 52 , 45 , 54 , 100);
    zombieWalkingRight= getAnimation( zombieWalkingR, 1 , 8 , 52 , 45 , 54 , 100);
    zombieWalkingDown= getAnimation( zombieWalkingD, 8 , 1 , 45 , 52 , 54 , 100);
    playerWalkingUp=getAnimation(playerWalkingU , 8 , 1 , 36 , 59 , 54 , 100);
    playerWalkingLeft=getAnimation(playerWalkingL , 1 , 8 , 59 , 36 , 54 , 100);
    playerWalkingRight=getAnimation(playerWalkingR , 1 , 8 , 59 , 36 , 54 , 100);
    playerWalkingDown=getAnimation(playerWalkingD , 8 , 1 , 36 , 59 , 54 , 100);
    
    
    
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
    //up=1 down=2 left=3 right=4
   if(playerMovement==1){
      playerWalkingUp.draw(480, 416, 32, 32); 
   }
   if(playerMovement==2){
      playerWalkingDown.draw(480, 416, 32, 32); 
   }
   if(playerMovement==3){
      playerWalkingLeft.draw(480, 416, 32, 32); 
   }
   if(playerMovement==4){
      playerWalkingRight.draw(480, 416, 32, 32); 
   }
   if(buyChest==true && gc.getInput().isKeyPressed(Input.KEY_E ) && money>=950 ){
       money-=950;
       boxOpen2=true;
       boxStart=true;
   }
    //when buying the chest
    if(boxOpen2==true){
        
        box_Open2.draw(x*32+1408,y*32+1760);
        buyChest=false;
        if(!boxStart&&bCount<40){
            bCount++;
        }
        if(!boxStart&&bCount>=40){
            boxOpen2=false;
            if(weaponselc==0){
                 weapon="m4a1";
            }
            if(weaponselc==1){
                 weapon="UMP";
            }
            if(weaponselc==2){
                 weapon="nova";
            }
        }
        if(boxStart){
            bCount=0;
            boxStart=false;
        }
       Random boxGen =new Random();
       
        weaponselc=boxGen.nextInt(3);
        System.out.println("bCount: "+bCount);
       
            if(weaponselc==0){
                 g.drawString("m4a1", 464, 398);
            }
            if(weaponselc==1){
                 g.drawString("UMP", 464, 398);
            }
            if(weaponselc==2){
                 g.drawString("nova", 464, 398);
            }
            
       
        if((x2==44 && y2==56) || (x2==45 && y2==56)&& buyChest==false)
        g.drawString("pick up(E)", 464, 398);
        
    }
    
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
    if(buyChest==true ){
     g.drawString("$950 (E)", 464, 400);
    }
    
    g.setColor(Color.red);
    
    for(int i=0;i<pos.size();i++){
        if(pos.get(i)==1){
            zombieWalkingUp.draw(x*32+zombieX.get(i)*32,y*32+zombieY.get(i)*32,32,32);
        }
        if(pos.get(i)==2){
            zombieWalkingDown.draw(x*32+zombieX.get(i)*32,y*32+zombieY.get(i)*32,32,32);
        }
        if(pos.get(i)==3){
            zombieWalkingLeft.draw(x*32+zombieX.get(i)*32,y*32+zombieY.get(i)*32,32,32);
        }
        if(pos.get(i)==4){
            zombieWalkingRight.draw(x*32+zombieX.get(i)*32,y*32+zombieY.get(i)*32,32,32);
        }
    }
    

     }
     
    public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
     int objectLayer = map.getLayerIndex("Tile Layer 1");
     
     //update for animations
      zombieWalkingUp.update(delta);
      zombieWalkingLeft.update(delta);
      zombieWalkingDown.update(delta);
      zombieWalkingRight.update(delta);
      playerWalkingUp.update(delta);
      playerWalkingLeft.update(delta);
      playerWalkingRight.update(delta);
      playerWalkingDown.update(delta);
     
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
        pos.add(2);
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
        for(int i=0;i<pos.size();i++){
        }
           
        //if zombie spawn is Up
        if(position[randomInt]==1){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieX.get(i)==zombieSpawn[randomInt][0] && (zombieY.get(i)==zombieSpawn[randomInt][1] || zombieY.get(i)==zombieSpawn[randomInt][1]-1)){
                        test=1;
                        break;
                }
            }
            if(test==0&&zombieX.size()<cap){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
                pos.add(1);
            }
        }
        
        //if zombie spawn is Down
        if(position[randomInt]==2){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieX.get(i)==zombieSpawn[randomInt][0] && (zombieY.get(i)==zombieSpawn[randomInt][1] || zombieY.get(i)==zombieSpawn[randomInt][1]+1)){
                        test=1;
                        break;
                }
            }
            if(test==0&&zombieX.size()<cap){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
                pos.add(2);
            }
        }
        
        //if zombie spawn is Left
        if(position[randomInt]==3){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieY.get(i)==zombieSpawn[randomInt][1] && (zombieX.get(i)==zombieSpawn[randomInt][0] || zombieX.get(i)==zombieSpawn[randomInt][0]-1)){
                        test=1;
                        break;
                }
            }
            if(test==0&&zombieX.size()<cap){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
                pos.add(3);
            }
        }
        
        //if zombie spawn is Right
        if(position[randomInt]==4){
            test=0;
            for(int i=0;i<zombieX.size();i++){
                if(zombieY.get(i)==zombieSpawn[randomInt][1] && (zombieX.get(i)==zombieSpawn[randomInt][0] || zombieX.get(i)==zombieSpawn[randomInt][0]+1)){
                        test=1;
                        break;
                }
            }
            if(test==0&&zombieX.size()<cap){
                zombieX.add(zombieSpawn[randomInt][0]);
                zombieY.add(zombieSpawn[randomInt][1]);
                toMove.add(true);
                moveC.add(0);
                pos.add(4);
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
            playerMovement=4;
            buyDoor=false;
         }
     }
     //to move up and checking for doors/box
     if((x2==44 && y2==56) || (x2==45 && y2==56)){
         buyChest=true;
     }
     else {
         buyChest=false;
     }
     
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
            playerMovement=1;
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
            playerMovement=3;
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
            playerMovement=2;
            buyDoor=false;
        }
    }
    
     
     
    System.out.println("x: "+x2);
    System.out.println("y: "+y2);

     }
    
    //base code for zombie spawning and moving out
    public void moveOut(){
        if(time>=500){
            for(int i=0;i<toMove.size();i++){
                if(toMove.get(i)==true&&moveC.get(i)<2){
                    if(pos.get(i)==1){
                        moveC.set(i,moveC.get(i)+1);
                        zombieY.set(i,zombieY.get(i)-1);
                    }
                    if(pos.get(i)==2){
                        moveC.set(i,moveC.get(i)+1);
                        zombieY.set(i,zombieY.get(i)+1);
                    }
                    if(pos.get(i)==3){
                        moveC.set(i,moveC.get(i)+1);
                        zombieX.set(i,zombieX.get(i)-1);
                    }
                    if(pos.get(i)==4){
                        moveC.set(i,moveC.get(i)+1);
                        zombieX.set(i,zombieX.get(i)+1);
                    }
                    if(moveC.get(i)==2){
                        toMove.set(i,false);
                    }
                }
            }
        }
    }
     
      public int getID(){
        return 3;
    }
}
