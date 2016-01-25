package last.stand;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import java.io.*;


public class Credits extends BasicGameState {
    
    private Image enterNameImage,credits,continue_Button,continue_Button_select;
    private Image exit_button, exit_button_select,continueImage;
    
    private String diff="";
    private boolean start=false;
    public static boolean reset=false;
    
    private String name="";
    private String line="";
    
    private int choose=1;
    private int x;
    private int y;
    private int c=0;
    public static String s0,s1,s2,s3,s4,s5,s6,s7,s8,s9;

    public static String[] top=new String[10];
    
    private int[] topN=new int[10];
        
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
        if(Options.easy)diff="Easy";
        else diff="Hard";
    }
    
    public void render(GameContainer gc,StateBasedGame sbg, Graphics g)throws SlickException {
        x=Mouse.getX();
        y=800-Mouse.getY();
        g.setColor(Color.white);
        if(choose==1){
            enterNameImage.draw();
            g.drawString("PRESS ENTER TO CONTINUE", 450, 300);
            g.drawString(name.toUpperCase(),493,498);
        }
        
        if(choose==2){
           credits.draw();
           g.drawString("PRESS ENTER TO CONTINUE",400,700);
           
        }
        
        if(choose==3){
           continueImage.draw();
           
           
           if(x>=90 && x<=441 && y>=652 && y<=754){
               continue_Button_select.draw(60,600);
           }
           else{continue_Button.draw(60,600);}
           
           if(x>=629 && x<=980 && y>=652 && y<=752){
               exit_button_select.draw(600,600);
           }
           else  {exit_button.draw(600,600);}
        }
    }
    
    public void update(GameContainer gc,StateBasedGame sbg, int delta)throws SlickException{
        x=Mouse.getX();
        y=800-Mouse.getY();
        Input input=gc.getInput();
        
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
        if(gc.getInput().isKeyPressed(Input.KEY_ENTER)){
            if(choose==2){
                choose=3;
            }
            if(choose==1){
                
                try{
                    BufferedReader read=new BufferedReader(new FileReader("score.txt"));
                    while((line=read.readLine())!=null){
                        String[] test=line.split(",");
                        topN[c]=Integer.parseInt(test[3]);
                        top[c]=line;
                        c++;
                    }
                    for(int i=0;i<10;i++){
                        if(Game.kills>topN[i]||top[i].equals("0,0,0,0,0")){
                            for(int j=9;j>=i;j--){
                                top[j]=top[j-1];
                                topN[j]=topN[j-1];
                            }
                            top[i]=name+","+Game.kills*50+","+Game.round+","+Game.kills+","+diff;
                            break;
                        }
                    }
                    BufferedWriter write=new BufferedWriter(new FileWriter("score.txt"));
                    for(int i=0;i<10;i++){
                        write.write(top[i]);
                        write.newLine();
                    }
                    write.close();
                }catch(IOException e){System.out.println("Error: "+e);}
                choose=2;
            }
            

           
        }
       
        if(gc.getInput().isKeyPressed(Input.KEY_ENTER)&& choose==2){
            choose=3;
            
        }
        if((x>=90 && x<=441 && y>=652 && y<=754)&& input.isMouseButtonDown(0)){
            sbg.enterState(0);
            reset=true;
        }
        if((x>=629 && x<=980 && y>=652 && y<=752)&& input.isMouseButtonDown(0)){
            gc.exit();
        }
        
        
        
    }
    
    
    public int getID(){
        return 4;
    }
}
