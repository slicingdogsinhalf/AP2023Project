import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Dinosaur here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Dinosaur extends Actor
{ 
    /**
     * Act - do whatever the Dinosaur wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public static  double h;
    public static  double w;
    public static GreenfootImage regpika= new GreenfootImage("images/pikcahu1.png");
    public static GreenfootImage pikajump= new GreenfootImage("images/pikajump.png");
    public Dinosaur(){
        GreenfootImage dino= new GreenfootImage("images/pikcahu1.png");
        w=dino.getWidth()/6;
        h=dino.getHeight()/6;

        dino.scale((int)w,(int)h);
        setImage(dino);

    }

    public void pikalow(){
        this.getImage().scale(this.getImage().getWidth(),this.getImage().getHeight()/2);
        this.setLocation(this.getX(),this.getY()+this.getImage().getHeight());
        Greenfoot.delay(3);
        this.getImage().scale(this.getImage().getWidth(),this.getImage().getHeight()*2);
        this.setLocation(this.getX(),this.getY()-this.getImage().getHeight()/2);
    }

    public void pikjump(int j){

        this.setImage(pikajump);
        this.getImage().scale((int)this.w,(int)this.h);
        if(Greenfoot.isKeyDown("s")){
            this.getImage().scale((int)this.w,(int)this.h/2);
        }
        for(int  i=j;i>=0;i--){
            this.setLocation(this.getX(),this.getY()-i);
            Greenfoot.delay(2);
        }
        for(int i=0;i<=j;i++){
            this.setLocation(this.getX(),this.getY()+i);
            Greenfoot.delay(2);
        }
        this.setImage(regpika);
        this.getImage().scale((int)this.w,(int)this.h);
        if(Greenfoot.isKeyDown("s")){
            this.getImage().scale((int)this.w,(int)this.h/2);
        }

    }

    public boolean pikatouch(){
        if (isTouching(Portal.class))
        {
            return true;
        }
        return false;
    }

    //public dinosaur(){
    //  GreenfootImage dinosaur = new GreenfootImage
    //}
}
