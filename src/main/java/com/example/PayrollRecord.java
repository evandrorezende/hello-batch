package com.example;

public class PayrollRecord {

	private int empID;
	private int base;
	private float bonus;
	private float tax;
	private float net;
	
	
	@Override
	public String toString(){
		return "[empID="+empID+", base="+base+", bonus="+bonus+", tax="+tax+", net="+net+"]";
	}
	
	
	public int getEmpID() {
		return empID;
	}
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	public int getBase() {
		return base;
	}
	public void setBase(int base) {
		this.base = base;
	}
	public float getBonus() {
		return bonus;
	}
	public void setBonus(float bonus) {
		this.bonus = bonus;
	}
	public float getTax() {
		return tax;
	}
	public void setTax(float tax) {
		this.tax = tax;
	}
	public float getNet() {
		return net;
	}
	public void setNet(float net) {
		this.net = net;
	}
	
	
}
