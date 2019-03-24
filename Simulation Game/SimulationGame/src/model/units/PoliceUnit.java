package model.units;
import java.util.*;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
abstract class PoliceUnit extends Unit {
private ArrayList<Citizen> passengers;
private int maxCapacity;
private int distanceToBase;

public PoliceUnit(String id , Address location , int stepsPerCycle , int maxCapacity){
	super(id,location,stepsPerCycle);
	this.maxCapacity = maxCapacity;
	passengers = new ArrayList<Citizen>();
}


public int getMaxCapacity() {
	return maxCapacity;
}

public int getDistanceToBase() {
	return distanceToBase;
}
public void setDistanceToBase(int distanceToBase) {
	if(distanceToBase<=0){
		this.distanceToBase=0;
	    this.getWorldListener().assignAddress(this, this.getTarget().getLocation().getX(),this.getTarget().getLocation().getY());
	    for(int i=0;i<this.passengers.size();i++){
	    	this.passengers.get(i).setState(CitizenState.RESCUED);
	    }
	    if(this.getState()!=UnitState.IDLE)
	    		this.setState(UnitState.RESPONDING);
	    
	}
	this.distanceToBase = distanceToBase;
}


public void setPassengers(ArrayList<Citizen> passengers) {
	this.passengers = passengers;
}
public void cycleStep(){
	if(passengers.size()==0){
		super.cycleStep();
	}else{
		this.setDistanceToBase(distanceToBase-this.getStepsPerCycle());
	}
		}
public void treat(){
	super.treat();
    while(this.passengers.size()<this.maxCapacity && ((ResidentialBuilding)this.getTarget()).getOccupants().size()!=0){
    	this.passengers.add(((ResidentialBuilding)this.getTarget()).getOccupants().get(0));
    	((ResidentialBuilding)this.getTarget()).getOccupants().remove(0);
    }
    if(this.passengers.size()==0 && ((ResidentialBuilding)this.getTarget()).getOccupants().size()==0){
    	this.jobsDone();
    }
}
}
