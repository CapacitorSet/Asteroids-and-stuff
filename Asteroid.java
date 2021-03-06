public class Asteroid {
    
    public int mass;
    public double x, y, z;
    public double xVelocity, yVelocity, zVelocity;
    
    public static final int G = 1;
    
    public Asteroid(int mass, double x, double y, double z, double xVelocity, double yVelocity, double zVelocity) {
        this.mass = mass;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
        this.zVelocity = zVelocity;
    }
    
    public double distanceTo(Asteroid a) {
        return Math.sqrt(Math.pow(a.x - this.x, 2)
                       + Math.pow(a.y - this.y, 2)
                       + Math.pow(a.z - this.z, 2));
    }
    
    public Vector accelerationTo(Asteroid a) {
        Vector result = new Vector();
        double thing = G * a.mass / Math.pow(distanceTo(a), 3);
        result.dX = (a.x - this.x) * thing;
        result.dY = (a.y - this.y) * thing;
        result.dZ = (a.z - this.z) * thing;
        return result;
    }
    
    public Vector totalAcceleration(Asteroid[] system) {
        Vector result = new Vector();
        for (Asteroid a:system) {
            if (a != this) {
                result.add(accelerationTo(a));
            }
        }
        return result;
    }
    
    public void updateVelocity(Asteroid[] system, double dt) {
        Vector acceleration = totalAcceleration(system);
        this.xVelocity += dt * acceleration.dX;
        this.yVelocity += dt * acceleration.dY;
        this.zVelocity += dt * acceleration.dZ;
    }
    
    public void updatePosition(double dt) {
        this.x += dt * this.xVelocity;
        this.y += dt * this.yVelocity;
        this.z += dt * this.zVelocity;
    }
}
