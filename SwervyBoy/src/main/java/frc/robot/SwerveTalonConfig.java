/**
 * Simple to store the swerve drive azimuth motor config
 * 
 * May need some conditional settings depending on position
 */

package frc.robot;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

public class SwerveTalonConfig {
    /* Hold all of the config data */
    public TalonSRXConfiguration _talonCfg;
    
    public SwerveTalonConfig()
    {
        _talonCfg = new TalonSRXConfiguration();
        
        /* Talon SRX azimuth motor config*/
/* TODO - THESE ARE DUMMY VALUES AT THE MOMENT */
        _talonCfg.primaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Relative;
        _talonCfg.primaryPID.selectedFeedbackCoefficient = 1.0;
        _talonCfg.auxiliaryPID.selectedFeedbackSensor = FeedbackDevice.CTRE_MagEncoder_Absolute;
        _talonCfg.auxiliaryPID.selectedFeedbackCoefficient = 1.0;
        _talonCfg.forwardLimitSwitchSource = LimitSwitchSource.Deactivated;
        _talonCfg.reverseLimitSwitchSource = LimitSwitchSource.Deactivated;
       /* _talonCfg.sum0Term = FeedbackDevice.QuadEncoder;
        _talonCfg.sum1Term = FeedbackDevice.RemoteSensor0;
        _talonCfg.diff0Term = FeedbackDevice.RemoteSensor1;
        _talonCfg.diff1Term = FeedbackDevice.PulseWidthEncodedPosition;*/
        _talonCfg.peakCurrentLimit = 40;
        _talonCfg.peakCurrentDuration = 500;
        _talonCfg.continuousCurrentLimit = 30;
        _talonCfg.openloopRamp = 1.0;
        _talonCfg.closedloopRamp = 1.0;
        _talonCfg.peakOutputForward = 1.0;
        _talonCfg.peakOutputReverse = -1.0;
        _talonCfg.nominalOutputForward = 0.0;
        _talonCfg.nominalOutputReverse = 0.0;
        _talonCfg.neutralDeadband = 0.04;
        //_talonCfg.voltageCompSaturation = 9.296875;
        //_talonCfg.voltageMeasurementFilter = 16;
        //_talonCfg.velocityMeasurementPeriod = VelocityMeasPeriod.Period_25Ms;
        //_talonCfg.velocityMeasurementWindow = 8;
        //_talonCfg.forwardLimitSwitchDeviceID = 6;
        //_talonCfg.reverseLimitSwitchDeviceID = 5;
        _talonCfg.forwardLimitSwitchNormal = LimitSwitchNormal.Disabled;
        _talonCfg.reverseLimitSwitchNormal = LimitSwitchNormal.Disabled;
        //_talonCfg.forwardSoftLimitThreshold = 2767;
        //_talonCfg.reverseSoftLimitThreshold = -1219;
        _talonCfg.forwardSoftLimitEnable = false;
        _talonCfg.reverseSoftLimitEnable = false;
        _talonCfg.slot0.kP = 0.0666015625;
        _talonCfg.slot0.kI = 0.0;
        _talonCfg.slot0.kD = 0.0;
        _talonCfg.slot0.kF = 0.0;
        _talonCfg.slot0.integralZone = 900;
        _talonCfg.slot0.allowableClosedloopError = 217;
        _talonCfg.slot0.maxIntegralAccumulator = 254.000000;
        _talonCfg.slot0.closedLoopPeakOutput = 0.869990;
        _talonCfg.slot0.closedLoopPeriod = 33;
        /*_talonCfg.slot1.kP = 1.0;
        _talonCfg.slot1.kI = 0.0;
        _talonCfg.slot1.kD = 0.0;
        _talonCfg.slot1.kF = 0.0;
        _talonCfg.slot1.integralZone = 100;
        _talonCfg.slot1.allowableClosedloopError = 200;
        _talonCfg.slot1.maxIntegralAccumulator = 91.000000;
        _talonCfg.slot1.closedLoopPeakOutput = 0.199413;
        _talonCfg.slot1.closedLoopPeriod = 34;
        _talonCfg.slot2.kP = 223.232000;
        _talonCfg.slot2.kI = 34.000000;
        _talonCfg.slot2.kD = 67.000000;
        _talonCfg.slot2.kF = 6.323232;
        _talonCfg.slot2.integralZone = 44;
        _talonCfg.slot2.allowableClosedloopError = 343;
        _talonCfg.slot2.maxIntegralAccumulator = 334.000000;
        _talonCfg.slot2.closedLoopPeakOutput = 0.399804;
        _talonCfg.slot2.closedLoopPeriod = 14;
        _talonCfg.slot3.kP = 34.000000;
        _talonCfg.slot3.kI = 32.000000;
        _talonCfg.slot3.kD = 436.000000;
        _talonCfg.slot3.kF = 0.343430;
        _talonCfg.slot3.integralZone = 2323;
        _talonCfg.slot3.allowableClosedloopError = 543;
        _talonCfg.slot3.maxIntegralAccumulator = 687.000000;
        _talonCfg.slot3.closedLoopPeakOutput = 0.129032;
        _talonCfg.slot3.closedLoopPeriod = 12;
        _talonCfg.auxPIDPolarity = true;
        _talonCfg.remoteFilter0.remoteSensorDeviceID = 22;
        _talonCfg.remoteFilter0.remoteSensorSource = RemoteSensorSource.GadgeteerPigeon_Roll;
        _talonCfg.remoteFilter1.remoteSensorDeviceID = 41;
        _talonCfg.remoteFilter1.remoteSensorSource = RemoteSensorSource.GadgeteerPigeon_Yaw;*/
        _talonCfg.motionCruiseVelocity = 1536;
        _talonCfg.motionAcceleration = 154;
        _talonCfg.motionProfileTrajectoryPeriod = 20;
        /*_talonCfg.feedbackNotContinuous = true;
        _talonCfg.remoteSensorClosedLoopDisableNeutralOnLOS = false;
        _talonCfg.clearPositionOnLimitF = true;
        _talonCfg.clearPositionOnLimitR = true;
        _talonCfg.clearPositionOnQuadIdx = false;
        _talonCfg.limitSwitchDisableNeutralOnLOS = true;
        _talonCfg.softLimitDisableNeutralOnLOS = false;
        _talonCfg.pulseWidthPeriod_EdgesPerRot = 9;
        _talonCfg.pulseWidthPeriod_FilterWindowSz = 32;
        _talonCfg.customParam0 = 3;
        _talonCfg.customParam1 = 5;*/
    }

    /**
    * Apply the canned config to the given TalonSRX
    *
    * @param talon TalonSRX object on which to apply the configuration
    */
    public ErrorCode applyConfig(TalonSRX ... talons)
    {
        ErrorCode err;
        for (TalonSRX talon : talons) {
            err = talon.configAllSettings(_talonCfg);

            if (err != ErrorCode.OK) 
                return err;
        }
        return ErrorCode.OK;
    }
}
