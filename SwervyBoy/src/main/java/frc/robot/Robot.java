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

  boolean lastUserButton;

  public void robotInit() {

    LFTalon = new TalonSRX(0);
    RFTalon = new TalonSRX(1);
    BLTalon = new TalonSRX(2);
    BRTalon = new TalonSRX(3);

    LFVictor = new VictorSP(0);
    RFVictor = new VictorSP(1);
    BLVictor = new VictorSP(2);
    BRVictor = new VictorSP(3);

    Stick = new Joystick(1);
    Stick2 = new Joystick(2);
    Controller = new XboxController(3);

    //Air = new Compressor();

    gyro = new ADIS16448_IMU();

    Config = new SwerveDriveConfig();

    /* For field-oriented drive, set gyro reference. For robot-relative drive, set to null */
    // TODO - Get robot-relative drive working first
    Config.gyro = null;       
    //Config.gyro = gyro;                                                       
    gyro.reset();

    Config.width = 18.5;
    Config.length = 20;

    LFWheel = new Wheel(LFTalon, LFVictor, 1.0);
    RFWheel = new Wheel(RFTalon, RFVictor, 1.0);
    LBWheel = new Wheel(BLTalon, BLVictor, 1.0);
    RBWheel = new Wheel(BRTalon, BRVictor, 1.0);

    wheels = new Wheel[]{

      LFWheel, RFWheel, LBWheel, RBWheel

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
      Swerve.saveAzimuthPositions();
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