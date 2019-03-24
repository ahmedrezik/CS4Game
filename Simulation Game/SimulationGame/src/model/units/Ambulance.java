package model.units;
import model.infrastructure.ResidentialBuilding;
import simulation.Address;
import model.people.*;
public class Ambulance extends MedicalUnit {
public Ambulance(String id,Address location,int stepsPerCycle) {
	super(id,location,stepsPerCycle);
}
public void treat(){
	super.treat();
	if ( ((Citizen)this.getTarget()).getBloodLoss() == 100)
	 this.jobsDone();
	else if(((Citizen)this.getTarget()).getBloodLoss()==0 ){
		((Citizen)this.getTarget()).setState(CitizenState.RESCUED);
		this.heal();}
	else
	((Citizen)this.getTarget()).setBloodLoss(((Citizen)this.getTarget()).getBloodLoss()-this.getTreatmentAmount());
	
}
}
