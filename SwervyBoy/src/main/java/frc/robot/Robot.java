package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.VictorSP;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Compressor;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  XboxController Controller;
  Joystick Stick;

  SwerveDriveConfig config;
  SwerveDrive Swerve;

  Compressor Air;

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
    LFTalon = new TalonSRX(0);
    RFTalon = new TalonSRX(1);
    BLTalon = new TalonSRX(2);
    BRTalon = new TalonSRX(3);
  
    /*****************************************************************
    * DO NOT ENABLE THE FOLLOWING UNTIL TUNING IS COMPLETE AND THE 
    *  SwerveTalonConfig class has been updated
    ******************************************************************
    // Apply the default config to the Talons
    var swerveTalonCfg = new SwerveTalonConfig();
    swerveTalonCfg.applyConfig(LFTalon, RFTalon);
    swerveTalonCfg.applyConfig(BLTalon);
    swerveTalonCfg.applyConfig(BRTalon);
    */
  
    LFVictor = new VictorSP(0);
    RFVictor = new VictorSP(1);
    BLVictor = new VictorSP(2);
    BRVictor = new VictorSP(3);

    Swerve = new SwerveDrive(config);

    Stick = new Joystick(1);
    Controller = new XboxController(2);

    Air = new Compressor();

    gyro = new ADIS16448_IMU();

    config = new SwerveDriveConfig();

    config.gyro = gyro;
    gyro.reset();

    config.width = 27.5;
    config.length = 31;

    LFWheel = new Wheel(LFTalon, LFVictor, 1.0);
    RFWheel = new Wheel(RFTalon, RFVictor, 1.0);
    LBWheel = new Wheel(BLTalon, BLVictor, 1.0);
    RBWheel = new Wheel(BRTalon, BRVictor, 1.0);

    wheels = new Wheel[]{

      LFWheel, RFWheel, LBWheel, RBWheel

    };

    config.wheels = wheels;

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  public void robotPeriodic() {

    Swerve.drive(Stick.getY(), Stick.getX(), Stick.getZ()); 

    SmartDashboard.putNumber("Gyro angle", gyro.getAngle());

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