
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.*;
import org.lwjgl.*;

public class SimpleOGLRenderer {
	static double x_p=0;
	static double y_p=0;
	static int r=100;
	static double rad=0;
	static double x=r*x_p;
	static double y=r*y_p;
public SimpleOGLRenderer() {
	try {Display.setDisplayMode(new DisplayMode(640,490));
	Display.setTitle("Hello World");
	Display.create();
	}catch(LWJGLException e) {
		e.printStackTrace();
	}
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0,640,480,0,1,-1);
	glMatrixMode(GL_MODELVIEW);
	glEnable(GL_TEXTURE_2D);
	while(!Display.isCloseRequested()) {
		glClear(GL_COLOR_BUFFER_BIT);
		glBegin(GL_QUADS);
		     glVertex2i(400,400);
		     glVertex2i(450,400) ; 
		     glVertex2i(450,450);
		     glVertex2i(400,450);
		glEnd(); 
		//draws a line
		glBegin(GL_LINES);
		    glVertex2d(100,100);
		    glVertex2d(200,200);
		   
		    
		glEnd();    
		Display.update();
		Display.sync(60);
		
	}
	Display.destroy();
}
	public static void main(String[] args) {
		new SimpleOGLRenderer();
	}
}
