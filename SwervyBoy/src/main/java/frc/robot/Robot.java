package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.Utility;

//import edu.wpi.first.wpilibj.Compressor;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  XboxController Controller;
  Joystick Stick;
  SwerveDriveConfig Config;
  SwerveDrive Swerve;
  RobotController user;

  //Compressor Air;

  VictorSP LFVictor;
  VictorSP RFVictor;
  VictorSP BLVictor;
  VictorSP BRVictor;

  TalonSRX LFTalon;
  TalonSRX RFTalon;
  TalonSRX BLTalon;
  TalonSRX BRTalon;

  ADIS16448_IMU gyro;

  Wheel[] wheels;
  Wheel LFWheel,RFWheel,LBWheel,RBWheel;

  public void robotInit() {

    RobotController.getUserButton();

    LFTalon = new TalonSRX(0);
    RFTalon = new TalonSRX(1);
    BLTalon = new TalonSRX(2);
    BRTalon = new TalonSRX(3);

    LFVictor = new VictorSP(0);
    RFVictor = new VictorSP(1);
    BLVictor = new VictorSP(2);
    BRVictor = new VictorSP(3);

    Stick = new Joystick(1);
    Controller = new XboxController(2);

    //Air = new Compressor();

    gyro = new ADIS16448_IMU();

    Config = new SwerveDriveConfig();

    Config.gyro = gyro;
    gyro.reset();

    Config.width = 27.5;
    Config.length = 31;

    LFWheel = new Wheel(LFTalon, LFVictor, 1.0);
    RFWheel = new Wheel(RFTalon, RFVictor, 1.0);
    LBWheel = new Wheel(BLTalon, BLVictor, 1.0);
    RBWheel = new Wheel(BRTalon, BRVictor, 1.0);

    wheels = new Wheel[]{

      LFWheel, RFWheel, LBWheel, RBWheel

    };

    Config.wheels = wheels;

    Swerve = new SwerveDrive(Config);

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  public void robotPeriodic() {

   /* {
      Air.start();
    }*/

    Swerve.drive(Stick.getY(), Stick.getX(), Stick.getZ()); 

    SmartDashboard.putNumber("Gyro angle", gyro.getAngle());
    SmartDashboard.putNumber("Gyro X", gyro.getAngleX());
    SmartDashboard.putNumber("Gyro Y", gyro.getAngleY());
    SmartDashboard.putNumber("Gyro Z", gyro.getAngleZ());

  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);

    gyro.reset();

  }

  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  public void teleopPeriodic() {
    //Called during Teleop Mode
    }

  public void testPeriodic() {
    //Called during TestMode
  }
}