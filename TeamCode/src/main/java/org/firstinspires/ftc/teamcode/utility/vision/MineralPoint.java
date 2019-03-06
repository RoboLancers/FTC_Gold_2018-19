package org.firstinspires.ftc.teamcode.utility.vision;

@SuppressWarnings("WeakerAccess")
public class MineralPoint {

    private double x;
    private double y;
    private double theta;

    public MineralPoint(double x, double y, double theta){
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTheta(){
        return theta;
    }
}
