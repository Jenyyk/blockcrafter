import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    public Player player;
    public MyWorld() {    
        super(800, 800, 1, false);
        player = new Player(this);
        addObject(player, 400, 400);
        
        addObject(new Block(1, 0, 65, this), 0, 0);
        addObject(new Block(1, 1, 65, this), 0, 0);
        addObject(new Block(1, 2, 65, this), 0, 0);
        addObject(new Block(1, -1, 64, this), 0, 0);
        addObject(new Block(1, 3, 64, this), 0, 0);
    }
}
