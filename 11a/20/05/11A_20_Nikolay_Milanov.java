package nikigmi;

import robocode.HitRobotEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.HitWallEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.awt.*;

public class Nikigmi extends Robot {
	int count = 0;
	double gunTurnAmt;
	String trackName; 
	int direction = 1;
	public void run() {
		setAdjustGunForRobotTurn(true);
		gunTurnAmt = 10;
		while (true) {
			turnGunRight(gunTurnAmt);
			count++;
			if (count > 2) {
				gunTurnAmt = -10;
			}
			if (count > 5) {
				gunTurnAmt = 10;
			}
		}
	}
	public void onScannedRobot(ScannedRobotEvent e) {
		count = 0;
		gunTurnAmt = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));
		turnGunRight(gunTurnAmt);
		if (e.getDistance() > 300) {
			turnRight(e.getBearing());
			ahead(e.getDistance() - 250);
		}else if (e.getDistance() < 150) {
			turnRight(e.getBearing());
			back(200 - e.getDistance());
		}
		else
		{
			
			if(getEnergy() > 10)
			{
				fire(1);
			}
			turnRight(e.getBearing() + 90 * direction);
			ahead(30);
		}
		
		scan();
	}
	public void onHitWall() 
	{
		out.println("a");
	
		direction = -direction;
	}
}
