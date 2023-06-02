import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class duck here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class duck extends Actor
{
    /**
     * Act - do whatever the duck wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public duck(){
        GreenfootImage duck= new GreenfootImage("ducker.png");
        duck.scale(duck.getWidth()/4,duck.getHeight()/(8));
        setImage(duck);
    }
    public void duckmove(double dif){
        
        Greenfoot.delay((int)Math.random()*20+20);
        boolean offScreen=true;
        boolean value=true;
        this.getImage().setTransparency(255);
        Greenfoot.playSound("quack.mp3");
        int i=0;

        if(offScreen){  
            
            offScreen=false;
            while(this.getX()>0){

                i++;
                this.setLocation(this.getX() - (int)(dif*i/2),this.getY());
                Greenfoot.delay(2);
                
                if(this.getX()<=0){
                    this.getImage().setTransparency(0);
                }
                else{this.getImage().setTransparency(255); offScreen=true;}

            }
        }
        this.getImage().setTransparency(0);
        this.setLocation(600,(int)(Math.random()*80+175));
    }
}
