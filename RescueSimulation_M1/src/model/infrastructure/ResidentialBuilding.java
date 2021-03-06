package model.infrastructure;

import java.util.ArrayList;
import model.events.*;
import java.util.Random;

import model.disasters.Disaster;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

public class ResidentialBuilding implements Rescuable, Simulatable {

	private Address location;
	private int structuralIntegrity;
	private int fireDamage;
	private int gasLevel;
	private int foundationDamage;
	private ArrayList<Citizen> occupants;
	private Disaster disaster;
	private SOSListener emergencyService;

	public ResidentialBuilding(Address location) {

		this.location = location;
		this.structuralIntegrity = 100;
		occupants = new ArrayList<Citizen>();

	}

	public int getStructuralIntegrity() {
		return structuralIntegrity;
	}

	public void setStructuralIntegrity(int structuralIntegrity) {
		if(structuralIntegrity<=0){
			this.structuralIntegrity=0;
			for(int i=0;i<this.occupants.size();i++){
				this.occupants.get(i).setHp(0);
			}
		}
		else if(structuralIntegrity>=100) {
			this.structuralIntegrity=100;
		}else
		this.structuralIntegrity = structuralIntegrity;
	}
	

	public int getFireDamage() {
		return fireDamage;
	}

	public void setFireDamage(int fireDamage) {
		if(fireDamage<=0) this.fireDamage=0;
		else if(fireDamage>=100) {
			this.fireDamage=100;
		}
		else
		this.fireDamage = fireDamage;
	}

	public int getGasLevel() {
		return gasLevel;
	}

	public void setGasLevel(int gasLevel) {
		if(gasLevel<=0) this.gasLevel=0;
		else if(gasLevel>=100) { this.gasLevel=100;
		for(int i=0;i<this.occupants.size();i++){
			this.occupants.get(i).setHp(0);
		}
		}
		else
		this.gasLevel = gasLevel;
	}

	public int getFoundationDamage() {
		return foundationDamage;
	}

	public void setFoundationDamage(int foundationDamage) {
		if(foundationDamage>=100){
			this.foundationDamage=100;
			this.setStructuralIntegrity(0);
		}
		else if(foundationDamage<=0) this.foundationDamage=0; //habdaya mn 3andy
		else this.foundationDamage = foundationDamage;
	}

	public Address getLocation() {
		return location;
	}

	public ArrayList<Citizen> getOccupants() {
		return occupants;
	}

	public Disaster getDisaster() {
		return disaster;
	}
	public void setEmergencyService(SOSListener emergencyService) {
		this.emergencyService = emergencyService;
	}
	public void cycleStep(){
		
		if(this.foundationDamage>0){
			Random r= new Random();
			int x=r.nextInt(6)+5;
			int n=this.structuralIntegrity-x;
			this.setStructuralIntegrity(n);
		}
		if(fireDamage>0 && fireDamage<30) {
			int n=structuralIntegrity-3;
			this.setStructuralIntegrity(n);}
		else if(fireDamage>=30 && fireDamage<70) {
			int n=structuralIntegrity-5;
			this.setStructuralIntegrity(n);
		}
		else { int n =structuralIntegrity-7;
		this.setStructuralIntegrity(n);
		}
		//fadel gaslevel msh by-affect directly
	}
	@Override
	public void struckBy(Disaster d) {
		this.disaster = d;
		this.emergencyService.receiveSOSCall(this);
		// TODO Auto-generated method stub
		
	}
	 
}
