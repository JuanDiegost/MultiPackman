
package model;

import java.awt.Point;

/**
 *
 * @author Yuliana Boyaca
 */
public class AbstractCookie {
    
    public static final double DEFAULT_WIDTH = 50.0;
    public static final double DEFAULT_HEIGHT = 50.0;
    public static final double DEFAULT_LIMIT_WIDTH = 500.0;
    public static final double DEFAULT_LIMIT_HEIGTH = 500.0;
    
    protected double width;
    protected double height;
    protected double limit_width;
    protected double limit_heigth;
    protected boolean state_visible;
    protected double x_Pos;
    protected double y_pos;

    public AbstractCookie(Point position) {
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.limit_width = DEFAULT_LIMIT_WIDTH;
        this.limit_heigth = DEFAULT_LIMIT_HEIGTH;
        this.x_Pos = position.getX();
        this.y_pos = position.getY();
        state_visible = false;
    }

    public void localizateCookie(Point movement){
//        aleatoriamente genere los puntos donde poner la galleta
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

    public double getY_pos() {
        return y_pos;
    }

    public void setY_pos(double y_pos) {
        this.y_pos = y_pos;
    }
}
