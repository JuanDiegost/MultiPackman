package model;

import java.awt.Point;
import java.awt.Rectangle;
import javax.swing.JButton;

/**
 *
 * @author Yuliana Boyaca
 */
public abstract class AbstractPacman {

    public static final double DEFAULT_WIDTH = 50.0;
    public static final double DEFAULT_HEIGHT = 50.0;
    public static final double DEFAULT_LIMIT_WIDTH = 440.0;
    public static final double DEFAULT_LIMIT_HEIGTH = 500.0;

    protected String ip;
    protected String name;
    protected double width;
    protected double height;
    protected double limit_width;
    protected double limit_heigth;
    protected boolean state_visible;
    protected double x_Pos;
    protected double y_Pos;

    public AbstractPacman(String ip, String name, Point position) {
        this.ip = ip;
        this.name = name;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.limit_width = DEFAULT_LIMIT_WIDTH;
        this.limit_heigth = DEFAULT_LIMIT_HEIGTH;
        this.x_Pos = position.getX();
        this.y_Pos = position.getY();
        state_visible = false;
    }

    public void locatePackman() {

    }

    public void makeMovementUP(double space) {
        if (y_Pos > 0) {
            y_Pos -= space;
            System.out.println("Y" + y_Pos);
        }
    }

    public void makeMovementDown(double space) {

        if (y_Pos < limit_heigth) {
            y_Pos += space;
        }
    }

    public void makeMovementLeft(double space) {
        if (x_Pos > 0) {
            x_Pos -= space;
        }
    }

    public void makeMovementRight(double space) {
        if (x_Pos < limit_width) {
            x_Pos += space;
        }
    }

    public void makeMovementDia_Up_Left(double space) {
        if (y_Pos > 0 && x_Pos > 0) {
            y_Pos -= space;
            x_Pos -= space;
        }
    }

    public void makeMovementDia_Up_Right(double space) {
        if (y_Pos > 0 && x_Pos < limit_width) {
            y_Pos -= space;
            x_Pos += space;
        }
    }

    public void makeMovementDia_Down_Left(double space) {
        if (y_Pos < limit_heigth && x_Pos > 0) {
            y_Pos += space;
            x_Pos -= space;
        }
    }

    public void makeMovementDia_Down_Right(double space) {
        if (y_Pos < limit_heigth && x_Pos < limit_width) {
            y_Pos += space;
            x_Pos += space;
        }
    }

    /**
     * metodo que se encarga de comer la galleta
     */
    public boolean eatCookie(JButton cookie) {
        return eatCookieLeft(cookie) || eatCookieRight(cookie) || eatCookieUp(cookie) || eatCookieDown(cookie);

    }

    public boolean isEatCookie(JButton cookie) {
        Rectangle rectangleCookie = cookie.getBounds();
        Rectangle rectangleUser = new Rectangle((int) x_Pos, (int) y_Pos, (int) width, (int) height);
        return rectangleUser.intersects(rectangleCookie);
    }

    public boolean eatCookieLeft(JButton cookie) {
        boolean isEaten = false;
        Point pointPacmanMouth1 = new Point((int) (x_Pos + width), (int) y_Pos);
        Point pointPacmanMouth2 = new Point((int) (x_Pos + width), (int) (y_Pos + height));
        Point pointCookie1 = new Point((int) (cookie.getX()), (int) cookie.getY());
        Point pointCookie2 = new Point((int) (cookie.getX()), (int) (cookie.getY() + cookie.getHeight()));

//		boolean collisionY = (x_Pos+width >= cookie.getX_Pos() && y_Pos+height >= cookie.getHeight()+cookie.getHeight()); 
        if (pointPacmanMouth1.getX() == pointCookie1.getX()
                && pointPacmanMouth1.getY() == pointCookie1.getY()
                && pointPacmanMouth2.getX() == pointCookie2.getX()
                && pointPacmanMouth2.getY() == pointCookie2.getY()) {
            isEaten = true;

            System.out.println("siii me la comi por la izquierda");
        }
        return isEaten;
    }

    public boolean eatCookieRight(JButton cookie) {
        boolean isEaten = false;
        Point pointPacmanMouth1 = new Point((int) (x_Pos), (int) y_Pos);
        Point pointPacmanMouth2 = new Point((int) (x_Pos), (int) (y_Pos + height));
        Point pointCookie1 = new Point((int) (cookie.getX() + cookie.getWidth()), (int) cookie.getY());
        Point pointCookie2 = new Point((int) (cookie.getX() + cookie.getWidth()), (int) (cookie.getY() + cookie.getHeight()));

//		boolean collisionY = (x_Pos+width >= cookie.getX_Pos() && y_Pos+height >= cookie.getHeight()+cookie.getHeight()); 
        if (pointPacmanMouth1.getX() == pointCookie1.getX()
                && pointPacmanMouth1.getY() == pointCookie1.getY()
                && pointPacmanMouth2.getX() == pointCookie2.getX()
                && pointPacmanMouth2.getY() == pointCookie2.getY()) {
            isEaten = true;
            System.out.println("siii me la comi por la derecha");
        }
        return isEaten;
    }

    public boolean eatCookieUp(JButton cookie) {
        boolean isEaten = false;
        Point pointPacmanMouth1 = new Point((int) (x_Pos), (int) (y_Pos + height));
        Point pointPacmanMouth2 = new Point((int) (x_Pos + width), (int) (y_Pos + height));
        Point pointCookie1 = new Point((int) (cookie.getX()), (int) cookie.getY());
        Point pointCookie2 = new Point((int) (cookie.getX() + cookie.getWidth()), (int) (cookie.getY()));

//		boolean collisionY = (x_Pos+width >= cookie.getX_Pos() && y_Pos+height >= cookie.getHeight()+cookie.getHeight()); 
        if (pointPacmanMouth1.getX() == pointCookie1.getX()
                && pointPacmanMouth1.getY() == pointCookie1.getY()
                && pointPacmanMouth2.getX() == pointCookie2.getX()
                && pointPacmanMouth2.getY() == pointCookie2.getY()) {
            isEaten = true;
            System.out.println("siii me la comi por arriba");
        }
        return isEaten;
    }

    public boolean eatCookieDown(JButton cookie) {
        boolean isEaten = false;
        Point pointPacmanMouth1 = new Point((int) (x_Pos), (int) y_Pos);
        Point pointPacmanMouth2 = new Point((int) (x_Pos + width), (int) (y_Pos));
        Point pointCookie1 = new Point((int) (cookie.getX()), (int) cookie.getY());
        Point pointCookie2 = new Point((int) (cookie.getX() + cookie.getWidth()), (int) (cookie.getY()));

//		boolean collisionY = (x_Pos+width >= cookie.getX_Pos() && y_Pos+height >= cookie.getHeight()+cookie.getHeight()); 
        if (pointPacmanMouth1.getX() == pointCookie1.getX()
                && pointPacmanMouth1.getY() == pointCookie1.getY()
                && pointPacmanMouth2.getX() == pointCookie2.getX()
                && pointPacmanMouth2.getY() == pointCookie2.getY()) {
            isEaten = true;
            System.out.println("siii me la comi por la abajo");
        }
        return isEaten;
    }
//        public boolean eatCookie(Cookie cookie) {
//        	boolean collisionX = (cookie.getX_Pos() >= x_Pos && 
//				cookie.getX_Pos() <= x_Pos+ width)
//				|| (cookie.getX_Pos() + width >= x_Pos && 
//				cookie.getX_Pos() + width <= x_Pos + width);
//		boolean collisionY = (cookie.getY_pos() >= y_Pos && 
//				cookie.getY_pos() <= y_Pos + height)
//				|| (cookie.getY_pos() + height >= y_Pos && 
//				cookie.getY_pos() + height <= y_Pos + height);
//		return collisionX && collisionY;
//	}

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getLimit_width() {
        return limit_width;
    }

    public void setLimit_width(double limit_width) {
        this.limit_width = limit_width;
    }

    public double getLimit_heigth() {
        return limit_heigth;
    }

    public void setLimit_heigth(double limit_heigth) {
        this.limit_heigth = limit_heigth;
    }

    public boolean isState_visible() {
        return state_visible;
    }

    public void setState_visible(boolean state_visible) {
        this.state_visible = state_visible;
    }

    public double getX_Pos() {
        return x_Pos;
    }

    public void setX_Pos(double x_Pos) {
        this.x_Pos = x_Pos;
    }

    public double getY_Pos() {
        return y_Pos;
    }

    public void setY_Pos(double y_Pos) {
        this.y_Pos = y_Pos;
    }

}
