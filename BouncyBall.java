import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Animated program with a ball bouncing off the program boundaries
 * @author mvail
 * @author Mitchell Fairbanks
 */
@SuppressWarnings("serial")
public class BouncyBall extends JPanel
{
	private final int INIT_WIDTH = 600;
	private final int INIT_HEIGHT = 400;
	private final int DELAY = 30;       // milliseconds between Timer events

	private final int DELTA_RANGE = 20; // range for xDelta and yDelta
	private final int MAX_RADIUS = 20;  // maximum radius value
	private final int MIN_RADIUS = 5;   // minimum radius value

	private Color color;                // initial ball color

	private int x, y;                   // ball anchor point coordinates
	private int xDelta, yDelta;         // change in x and y from one step to the next

	private double rDelta = 1;             // change in radius from one step to the next
	private Random rand = new Random();	// Random
	private int radius = rand.nextInt(15)+5;					// ball radius
	private boolean isGoingRight;		// Determines right and left
	private boolean isGoingUp;			// Determines up and down
	private int redDelta = rand.nextInt(10);
	private int greenDelta = rand.nextInt(10);
	private int blueDelta = rand.nextInt(10);
	int randRed = rand.nextInt(255);
	int randGreen = rand.nextInt(255);
	int randBlue = rand.nextInt(255);


	/**
	 * Draws a filled bouncy ball that stays within the bounds of the screen.
	 *
	 * @param g Graphics context
	 */
	public void paintComponent(Graphics g)
	{
			
		if (xDelta > 0)
			isGoingRight = true;
		else
			isGoingRight = false;
		if (yDelta > 0)
			isGoingUp = true;
		else
			isGoingUp = false;
			
		int width = getWidth();
		int height = getHeight();

		// Clear canvas
		//g.setColor(getBackground());
		//g.fillRect(0, 0, width, height);

		// Calculate new x anchor point value
		x += xDelta;
			if (x >= width - radius && isGoingRight) {
				xDelta *= -1;
				isGoingRight = false;
			}
			if (x <= radius && !isGoingRight) {
				xDelta *= -1;
				isGoingRight = false;
			}

		// TODO: needs more to stay in-bounds


		// Calculate new y anchor point value
		y += yDelta;
		if (y >= height - radius && isGoingUp) {
			yDelta *= -1;
			isGoingUp = false;
		}
		if (y <= radius && !isGoingUp) {
			yDelta *= -1;
			isGoingUp = true;
		}
		
		// TODO: needs more to stay in-bounds


		// TODO: use rDelta, MIN_RADIUS, and MAX_RADIUS to increase/decrease oval radius.


		 // Instantiate instance variable for reuse in paintComponent()
		
		radius += rDelta;
		if (radius >= MAX_RADIUS)
			rDelta *= -1;
		if (radius <= MIN_RADIUS)
			rDelta *= -1;
		
		randRed += redDelta;
		randGreen += greenDelta;
		randBlue += blueDelta;
		
		if (randRed > 255) {
			randRed = 511 - randRed;
			redDelta *= -1;
		}
		if (randRed <= 0) {
			randRed = -randRed;
			redDelta *= -1;
		}
		if (randGreen > 255) {
			randGreen = 511 - randGreen;
			greenDelta *= -1;
		}
		if (randGreen <= 0) {
			randGreen = -randGreen;
			greenDelta *= -1;
		}
		if (randBlue > 255) {
			randBlue = 511 - randBlue;
			blueDelta *= -1;
		}
		if (randBlue <= 0) {
			randBlue *= -1;
			blueDelta *= -1;
		}
		
		Color randColor = new Color(randRed,randGreen,randBlue);
		
		// Paint the ball at the calculated anchor point
		g.setColor(randColor);
		g.fillOval(x-radius, y-radius, 2*radius, 2*radius);

		// Makes the animation smoother
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins.
	 * This method also sets up a Timer that will call paintComponent() 
	 * with frequency specified by the DELAY constant.
	 */
	public BouncyBall()
	{
		setPreferredSize(new Dimension(INIT_WIDTH, INIT_HEIGHT));
		this.setDoubleBuffered(true);
		setBackground(Color.black);


		// Initialize ball anchor point within panel bounds
		x = rand.nextInt(INIT_WIDTH - radius);
		y = rand.nextInt(INIT_HEIGHT - radius);
		
		// Initialize deltas for x and y
		xDelta = rand.nextInt(DELTA_RANGE) - (DELTA_RANGE / 2);
		yDelta = rand.nextInt(DELTA_RANGE) - (DELTA_RANGE / 2);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY.
	 */
	private void startAnimation() {
		ActionListener taskPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				repaint();
			}
		};
		new Timer(DELAY, taskPerformer).start();
	}

	/**
	 * Starting point for the BouncyBall program.
	 * DO NOT MODIFY.
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		JFrame frame = new JFrame ("Bouncy Ball");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BouncyBall());
		frame.pack();
		frame.setVisible(true);
	}
}
