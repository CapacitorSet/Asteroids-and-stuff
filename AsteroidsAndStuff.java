
import java.awt.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class AsteroidsAndStuff extends JFrame {

    Asteroid[] sistema;
    float dt;
    float t;
    static int width = 500;
    static int height = 500;
    static int delay = 50;

    public AsteroidsAndStuff() {
        super();
        sistema = new Asteroid[3];
        sistema[0] = new Asteroid(12, 17, 15, 0, 1, 0, 0);
        sistema[1] = new Asteroid(16, 30, 16, 0, -1, 1, 0);
        sistema[2] = new Asteroid(12, 25, 29, 0, 0, 0, 0);
        dt = (float) 0.1;
    }

    public void paint(Graphics g) {
        float zoom = 4f;
        g.clearRect(0, 0, getWidth(), getHeight());
        for (Asteroid a : sistema) {
            g.fillOval((int) (zoom*a.x + getWidth()  / 2),
                       (int) (zoom*a.y + getHeight() / 2), 10, 10);
        }
        for (Asteroid a : sistema) {
            a.updateVelocity(sistema, dt);
        }
        for (Asteroid a : sistema) {
            a.updatePosition(dt);
        }
    }

    public static void main(String[] args) {
        final AsteroidsAndStuff frame = new AsteroidsAndStuff();
        frame.setSize(width, height);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setVisible(true);

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                frame.repaint();
            }

        }, 0, delay); // Repaint (= tick the simulation) every "delay" ms
    }
}
