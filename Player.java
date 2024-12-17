import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    public float posX = 0;
    public float posY = 64 * 20;
    
    public float velX = 0;
    public float velY = 0;
    
    public void act()
    {
       moveTrig();
    }
    
    private void moveTrig() {
        boolean[] collisions = blockDirections();
        // user input to movement handle
        if (Greenfoot.isKeyDown("a")) {
            velX = (float) Math.max(velX - 0.5, -3.0);
            if (collisions[0]) {
                velX = 0;
            }
        }
        if (Greenfoot.isKeyDown("d")) {
            velX = (float) Math.min(velX + 0.5, 3.0);
            if (collisions[1]) {
                velX = 0;
            }
        }
        if (Greenfoot.isKeyDown("w")) {
            velY = -5;
        }
        // to slowly stop when no key is held
        if (!Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("d")) {
            if (velX > 0) { velX -= 0.5; }
            else if (velX < 0) { velX += 0.5; }
        }
        velY = (collisions[2]) ? Math.min(velY, 0) : (float) Math.min(velY + 0.3, 5);
        posX = (collisions[2]) ? (posX / 20) * 20 : posX;
        posX += velX;
        posY += velY;
    }
    
    // stores what directions blocks are
    // used for collisions
    private boolean[] blockDirections() {
        int roundX = (int) Math.floor(posX / 20);
        int roundY = (int) posY / 20;
        boolean[] directions = {false, false, false, false};
        for (Block block : getObjectsInRange(80, Block.class)) {
            int blockX = (int) Math.round(block.blockPosX / 20);
            int blockY = (int) block.blockPosY / 20;
            // left
            if (blockX == roundX && blockY == roundY) { directions[0] = true; }
            // right
            if (blockX == roundX + 1 && blockY == roundY) { directions[1] = true; }
            
            // bottoms
            if ((blockX == roundX + 1 || blockX == roundX) && blockY == roundY + 1) { directions[2] = true; }
            // tops
            if ((blockX == roundX + 1 || blockX == roundX) && blockY == roundY) { directions[3] = true; }
        }
        return directions;
    }
}
