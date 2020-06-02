package pong;

import entitites.AbstractEntity;

import entitites.AbstractMovableEntity;
import entitites.Entity;
import entitites.MovableEntity;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/*import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.opengl.*;*/
import static org.lwjgl.opengl.GL11.*;

public class PongGame {
	private boolean isRunning=true;//logic to determine whent o stop game
	public static final int WIDTH=640;//width of window
	public static final int HEIGHT=480;//height of window
	private Ball ball;// ball object extended from entity abstract class
	private Bat bat;//bat object extended from entitity class
	private Bat bat2;
	public PongGame() {
		//constructor initialization
	
		setUpDisplay();//methods which are defined below
		setUpOpenGl();
		setUpEntities();
		setUpTimer();
		glEnable(GL_TEXTURE_2D); 
		while(isRunning) {
			render();
			logic(getDelta());
			input(direction());
			Display.update();
			Display.sync(60);
			if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				isRunning=false;
			}
			if(Display.isCloseRequested()) {
				isRunning=false;
			}
		}
		Display.destroy();
	}
	private long lastFrame;
	private long getTime() {
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}
	private int getDelta() {
		/*returns the time elapsed between each frame or while loop iteration , for instance
		 * if fps=60, 1/60 is the time taken for 1 frame to render, which is also the delta value.Java time is in milliseconds*1000
		 */
		long currentTime=getTime();
		int delta=(int)(currentTime-lastFrame);
		lastFrame=getTime();
		return delta;
	}
private void input(Bat b) {
	//changes the position of bat depending on which direction ball is moving
	if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
		b.setDY(0.3);
	}
	else if(Keyboard.isKeyDown(Keyboard.KEY_UP)) {
		b.setDY(-0.3);
	}
	else {
		b.setDY(0);
	}
}	
private void setUpDisplay() {
	try {Display.setDisplayMode(new DisplayMode(640,490));
	Display.setTitle("Pong");
	Display.create();
	}catch(LWJGLException e) {
		e.printStackTrace();
	}
}
private void setUpOpenGl(){
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	glOrtho(0,640,480,0,1,-1);
	glMatrixMode(GL_MODELVIEW);

}
private void setUpEntities(){
	bat2=new Bat(630,HEIGHT/2 -80/2,80,10);
	bat=new Bat(10,HEIGHT/2 -80/2,80,10);
	ball=new Ball((WIDTH/2)-5,(HEIGHT/2) -5,20,20);
	ball.setDX(-0.1);
}
private void setUpTimer(){
	lastFrame=getTime();
}
private void render() {
	glClear(GL_COLOR_BUFFER_BIT);
	ball.draw();
	bat2.draw();
	bat.draw();
	
	
}
private Bat direction() {
	if(ball.getDX()<0) {
		return bat;
	}
	else {
		return bat2;
	}
}
private void logic(int delta) {
	ball.update(delta);
	bat.update(delta);
	bat2.update(delta);
	if(ball.getX()>=bat.getX()&&ball.getX()<=(bat.getX()+bat.getWidth())&&ball.getY()>=bat.getY() &&ball.getY()<=(bat.getY()+bat.getHeight())){
		ball.setDX(0.3);
	}
	if(ball.getX()>=bat2.getX()&&ball.getX()<=(bat2.getX()+bat2.getWidth())&&ball.getY()>=bat2.getY() &&ball.getY()<=(bat2.getY()+bat2.getHeight())){
		ball.setDX(-0.3);
	}
}
private static class Bat extends AbstractMovableEntity{

	public Bat(double x, double y, double height, double width) {
		super(x, y, height, width);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		glRectd(x,y,x+width,y+height);
	}

	
	
}
private static class Ball extends AbstractMovableEntity{

	public Ball(double x, double y, double height, double width) {
		super(x, y, height, width);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		glRectd(x,y,x+width,y+height);
	}

	
	
}
	public static void main(String[] args) {
		new PongGame();
	}

}
