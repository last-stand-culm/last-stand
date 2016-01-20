package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import java.io.*;


public class Credits extends BasicGameState {
    
    private Image enterNameImage,credits,continue_Button,continue_Button_select;
    private Image exit_button, exit_button_select,continueImage;
    
    private boolean nameSelected=false,choose=false;
    private boolean start=false;
    
    private String name="";
    
    
    
    public Credits(int state){
        
    }
    
    public void init(GameContainer gc, StateBasedGame sbg)throws SlickException{
        enterNameImage=new Image("res/enter_name.png");
        credits=new Image("res/credits.png");
        continue_Button=new Image("res/continue_button.png");
        continue_Button_select=new Image("res/continue_button_select.png");
        exit_button=new Image("res/exit_button.png");
        exit_button_select=new Image("res/exit_button_select.png");
        continueImage=new Image("res/continue_Image.png");
        name="";
        start=false;
        
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
        g.setColor(Color.white);
        if(nameSelected==false){
            enterNameImage.draw();
            g.drawString(name.toUpperCase(),493,498);
        }
        
        if(nameSelected==true){
           credits.draw();
           choose=true;
        }
        
        if(choose==true){
           continueImage.draw();
           continue_Button.draw(60,600);
           exit_button.draw(600,600);
        }
    }
    
    public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
        if(gc.getInput().isKeyPressed(Input.KEY_A)){
            name+="a";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_B)){
            name+="b";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_C)){
            name+="c";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_D)){
            name+="d";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_E)){
            name+="e";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_F)){
            name+="f";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_G)){
            name+="g";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_H)){
            name+="h";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_I)){
            name+="i";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_J)){
            name+="j";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_K)){
            name+="k";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_L)){
            name+="l";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_M)){
            name+="m";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_N)){
            name+="n";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_O)){
            name+="o";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_P)){
            name+="p";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_Q)){
            name+="q";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_R)){
            name+="r";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_S)){
            name+="s";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_T)){
            name+="t";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_U)){
            name+="u";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_V)){
            name+="v";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_W)){
            name+="w";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_X)){
            name+="x";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_Y)){
            name+="y";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_Z)){
            name+="z";
        }
        if(gc.getInput().isKeyPressed(Input.KEY_BACK)&&name.length()>0){
            name=name.substring(0,name.length()-1);
        }
        if(gc.getInput().isKeyPressed(Input.KEY_SPACE)){
            name+=" ";
        }
        if(!start){
            name="";
            start=true;
        }
        int x=Mouse.getX();
        int y=Mouse.getY();
        System.out.println("x: "+x+" y: "+y);
        if(gc.getInput().isKeyPressed(Input.KEY_ENTER)){
//            try{
//                BufferedWriter write = new BufferedWriter(new FileWriter("Score File"));
//                write.write(name+","+Game.score+","+Game.round+","+Game.kills+","+Options.easy);
//            }catch(IOException e){System.out.println("Error"+e);}
            nameSelected=true;
        }
    }
    
    
    public int getID(){
        return 4;
    }
}
