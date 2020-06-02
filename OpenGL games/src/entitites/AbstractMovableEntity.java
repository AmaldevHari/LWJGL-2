package entitites;

import java.awt.Rectangle;

public abstract class AbstractMovableEntity extends AbstractEntity implements MovableEntity {
	protected double dx,dy;
	public AbstractMovableEntity(double x, double y, double height, double width) {
		super(x, y, height, width);
		this.dx=0;
		this.dy=0;

	}
	@Override
	public void update(int delta) {
      this.x+=delta*dx;
      this.y+=delta*dy;
	}
	public double getDX() {
		return dx;
	}
	public double getDY() {
		return dy;
	}
	public void setDY(double dy) {
		this.dy=dy;
	}
	public void setDX(double dx){
		this.dx=dx;
	}

}
