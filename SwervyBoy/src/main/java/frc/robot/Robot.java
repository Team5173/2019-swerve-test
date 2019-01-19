/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/*
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  DoubleSolenoid wooow = new DoubleSolenoid (1,2);
  DoubleSolenoid woow = new DoubleSolenoid (1,2);
  DoubleSolenoid wow = new DoubleSolenoid (1,2);
  XboxController controller = new XboxController(0);

  XboxController Controller;
  Joystick Stick;
  SwerveDriveConfig config;
  SwerveDrive Swerve;

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

    LFVictor = new VictorSP(0);
    RFVictor = new VictorSP(1);
    BLVictor = new VictorSP(2);
    BRVictor = new VictorSP(3);

    gyro = new ADIS16448_IMU();
    config = new SwerveDriveConfig();

    config.gyro = gyro;
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

    Swerve = new SwerveDrive(config);
    Stick = new Joystick(1);

    Controller = new XboxController(2);

    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    Swerve.drive(Stick.getY(), Stick.getX(), Stick.getZ()); 

    SmartDashboard.putNumber("Gyro-X", gyro.getAngleX());
    SmartDashboard.putNumber("Gyro-Y", gyro.getAngleY());
    SmartDashboard.putNumber("Gyro-Z", gyro.getAngleZ());
    
    SmartDashboard.putNumber("Accel-X", gyro.getAccelX());
    SmartDashboard.putNumber("Accel-Y", gyro.getAccelY());
    SmartDashboard.putNumber("Accel-Z", gyro.getAccelZ());
    
    SmartDashboard.putNumber("Pitch", gyro.getPitch());
    SmartDashboard.putNumber("Roll", gyro.getRoll());
    SmartDashboard.putNumber("Yaw", gyro.getYaw());
    
    SmartDashboard.putNumber("Pressure: ", gyro.getBarometricPressure());
    SmartDashboard.putNumber("Temperature: ", gyro.getTemperature());
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
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

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if(controller.getAButton() == true) {
      wow.set(DoubleSolenoid.Value.kForward);
    }
    if(controller.getBButton() == true) {
      woow.set(DoubleSolenoid.Value.kForward);
    }
    if(controller.getXButton() == true) {
      wooow.set(DoubleSolenoid.Value.kForward);
    }
    }


    


  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}