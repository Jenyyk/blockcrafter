import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Block extends Actor
{
    // 0 = air
    // 1 = dirt
    private int type;
    public float blockPosX;
    public float blockPosY;
    private MyWorld world;
    
    public Block(int type, float posX, float posY, MyWorld world) {
        this.blockPosX = posX * 20;
        this.blockPosY = posY * 20;
        this.world = world;
        this.type = type;
    }
    public void act()
    {
        renderSelf();
        Player player = world.player;
        setLocation((int) (this.blockPosX - player.posX) + 400, (int) (this.blockPosY - player.posY) + 400);
    }
    private void renderSelf() {
        GreenfootImage texture;
        try {
            texture = new GreenfootImage("images/" + this.type + ".png");
        } catch (Exception e) {
            texture = new GreenfootImage("images/1.png");
        }
        texture.scale(20, 20);
        setImage(texture);
    }
}
