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
     * @param cookie
     * @return 
     */

    public boolean isEatCookie(JButton cookie) {
        Rectangle rectangleCookie = cookie.getBounds();
        Rectangle rectangleUser = new Rectangle((int) x_Pos, (int) y_Pos, (int) width, (int) height);
        return rectangleUser.intersects(rectangleCookie);
    }
    
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
