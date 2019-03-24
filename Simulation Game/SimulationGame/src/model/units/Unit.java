package model.units;
import simulation.Simulatable;
import model.events.WorldListener;
import simulation.Address;
import simulation.Rescuable;


public abstract class Unit implements Simulatable {
private String unitID;
private UnitState state;
private  Address location;
private Rescuable target;
private int distanceToTarget;
private int stepsPerCycle;
private WorldListener worldListener;

public Unit(String id,Address location,int stepsPerCycle) {
	unitID=id;
	this.location=location;
	this.stepsPerCycle= stepsPerCycle;
	this.state=UnitState.IDLE;
}

public UnitState getState() {
	return state;
}
public void setState(UnitState state) {
	this.state = state;
}
public Address getLocation() {
	return location;
}
public void setLocation(Address location) {
	this.location = location;
}
public String getUnitID() {
	return unitID;
}
public Rescuable getTarget() {
	return target;
}
public int getStepsPerCycle() {
	return stepsPerCycle;
}

public void setDistanceToTarget(int distanceToTarget) {
	if( distanceToTarget <= 0){
		this.distanceToTarget = 0 ;
		worldListener.assignAddress(this, this.target.getLocation().getX(), this.target.getLocation().getY());
	   this.state = UnitState.TREATING ;
	}
	
	else
	this.distanceToTarget = distanceToTarget;
}

public void setWorldListener(WorldListener worldListener) {
	this.worldListener = worldListener;
}

public WorldListener getWorldListener() {
	return worldListener;
}
public  void cycleStep(){
		if(this.state == UnitState.RESPONDING){
			int x = this.distanceToTarget - this.stepsPerCycle ;
			this.setDistanceToTarget(x);
			}
		if(this.state == UnitState.TREATING){
			this.treat();
		}

	
}
public void treat(){
	this.target.getDisaster().setActive(false);
}
public void jobsDone(){
	this.setState(UnitState.IDLE);
}
}


