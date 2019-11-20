package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.Spark;

//import edu.wpi.first.wpilibj.Compressor;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  XboxController Controller;
  Joystick Stick;
  Joystick Stick2;
  SwerveDriveConfig Config;
  SwerveDrive Swerve;

  //Compressor Air;

  TalonSRX LFTalon;
  TalonSRX RFTalon;
  TalonSRX BLTalon;
  TalonSRX BRTalon;

  Spark LFSparkMax;
  Spark RFSparkMax;
  Spark BLSparkMax;
  Spark BRSparkMax;

  ADIS16448_IMU gyro;

  Wheel[] wheels;
  Wheel LFWheel,RFWheel,LBWheel,RBWheel;

  boolean lastUserButton;

  public void robotInit() {

    LFTalon = new TalonSRX(0);
    RFTalon = new TalonSRX(1);
    BLTalon = new TalonSRX(2);
    BRTalon = new TalonSRX(3);

    LFSparkMax = new Spark(1); //Actually a VictorSPX
    RFSparkMax = new Spark(3);
    BLSparkMax = new Spark(0);
    BRSparkMax = new Spark(2);

    SmartDashboard.putNumber("DriveX", 0);
    SmartDashboard.putNumber("DriveY", 0);
    SmartDashboard.putNumber("Rotate", 0);
    SmartDashboard.getNumber("GyroAngleZ", gyro.getAngleZ());

    
    Shuffleboard.getTab("Angles").add("Wheel RF", 1).withWidget(BuiltInWidgets.kGyro).getEntry();
    Shuffleboard.getTab("Angles").add("Wheel LF", 0).withWidget(BuiltInWidgets.kGyro).getEntry();
    Shuffleboard.getTab("Angles").add("Wheel BL", 2).withWidget(BuiltInWidgets.kGyro).getEntry();
    Shuffleboard.getTab("Angles").add("Wheel BR", 3).withWidget(BuiltInWidgets.kGyro).getEntry();
    Shuffleboard.getTab("Gyro Angle").add("Gyro", gyro.getAngleZ()).withWidget(BuiltInWidgets.kGyro);

    Stick = new Joystick(1);
    Stick2 = new Joystick(2);
    Controller = new XboxController(3);

    //Air = new Compressor();

    gyro = new ADIS16448_IMU();

    Config = new SwerveDriveConfig();

    /* For field-oriented drive, set gyro reference. For robot-relative drive, set to null */
    // TODO - Get robot-relative drive working first
    //Config.gyro = null;       
    Config.gyro = gyro;                                                       
    gyro.reset();

    Config.width = 18.5;
    Config.length = 21;

    LFWheel = new Wheel(LFTalon, LFSparkMax, 1.0);
    RFWheel = new Wheel(RFTalon, RFSparkMax, 1.0);
    LBWheel = new Wheel(BLTalon, BLSparkMax, 1.0);
    RBWheel = new Wheel(BRTalon, BRSparkMax, 1.0);

    wheels = new Wheel[]{

      LBWheel, RBWheel, LFWheel, RFWheel

    };

    Config.wheels = wheels;
    LFTalon.setSensorPhase(false);
    RFTalon.setSensorPhase(false);
    BLTalon.setSensorPhase(false);
    BRTalon.setSensorPhase(false);
    Swerve = new SwerveDrive(Config);

    /* Set the azimuth zero now, when robot boots up. Will not have desired
    *  effect if wheels aren't within 24 deg of straight. Might be a good
    *  idea to make a control for this in case we forgot to straighten the 
    *  wheels at power-up
    */
    Swerve.zeroAzimuthEncoders();

    

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

   
  public void robotPeriodic() {

   /* {
      Air.start();
    } */
    //Swerve.drive(SmartDashboard.getNumber("DriveY", 0), SmartDashboard.getNumber("DriveX", 0), SmartDashboard.getNumber("Rotate", 0));
    Swerve.drive(Stick2.getY(), Stick2.getX(), Stick.getZ());

    SmartDashboard.putNumber("Gyro angle", gyro.getAngle());
    SmartDashboard.putNumber("Gyro X", gyro.getAngleX());
    SmartDashboard.putNumber("Gyro Y", gyro.getAngleY());
    SmartDashboard.putNumber("Gyro Z", gyro.getAngleZ());

  }

  @Override
  public void disabledPeriodic() {
    super.disabledPeriodic();

    if(RobotController.getUserButton() && !lastUserButton){
      lastUserButton = true;
      gyro.reset();
      //Swerve.saveAzimuthPositions();
      // Might as well re-zero the wheels now
      Swerve.zeroAzimuthEncoders();
    }
    else{
      lastUserButton = false;
    }

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