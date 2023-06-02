import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class over here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class over extends Actor
{
    public static GreenfootImage over=new GreenfootImage("startover.png");
    /**
     * Act - do whatever the over wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public over()
    {
        
        over.scale(over.getWidth()/3,over.getHeight()/3);
        setImage(over);
    }
}
