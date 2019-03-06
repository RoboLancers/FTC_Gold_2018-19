package org.firstinspires.ftc.teamcode.utility.vision;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

@SuppressWarnings({"WeakerAccess", "unused", "FieldCanBeLocal"})
public class LancerPID {
    private double kP;
    private double pOutput;

    private double kI;
    private double errorSum;
    private double iOutput;

    private double kD;
    private double dOutput;

    private double actual;
    private double setpoint;
    private double output;
    private double error;

    private boolean firstRun;
    private double lastError;

    private ElapsedTime elapsedTime;
    private double currentTime;
    private double lastTime;
    private double deltaTime;

    private double tolerance;

    private static final double MAX_OUTPUT = 1;
    private static final double MIN_OUTPUT = -1;

    public LancerPID(double kP, double kI, double kD){
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;

        elapsedTime = new ElapsedTime();
        actual = 0;
    }

    public void setSetpoint(double setpoint){
        this.setpoint = setpoint;
        reset();
    }

    public double getOutput(double actual){
        return getOutput(setpoint, actual);
    }

    public double getOutput(double setpoint, double actual){
        currentTime = elapsedTime.time(TimeUnit.MILLISECONDS);
        error = setpoint - actual;

        pOutput = kP * error;

        if(firstRun){
            lastError = error;
            lastTime = currentTime;
            firstRun = false;
        }

        deltaTime = currentTime - lastTime;

        errorSum += error * deltaTime;
        iOutput = kI * errorSum;

        dOutput = kD * ((error - lastError) / (deltaTime));

        output = pOutput + iOutput + dOutput;

        if(output > MAX_OUTPUT){
            output = MAX_OUTPUT;
        }else if(output < MIN_OUTPUT){
            output = MIN_OUTPUT;
        }

        lastError = error;
        lastTime = currentTime;

        return output;
    }

    public void setTolerance(double tolerance){
        this.tolerance = tolerance;
    }

    public boolean isFinished(){
        return Math.abs(error) < tolerance;
    }

    public void reset(){
        errorSum = 0;
        elapsedTime.reset();
        firstRun = true;
    }
}
