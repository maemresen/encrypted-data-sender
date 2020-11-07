package com.cryptography.project.sender.controller;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

/**
 * @author canberkardic
 */

public class CantekController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uid;

	private int modbusAdress;
	private double temp1, temp2;
	private double pressure1, pressure2;
	private boolean alarm, relay;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getModbusAdress() {
		return modbusAdress;
	}

	public void setModbusAdress(int modbusAdress) {
		this.modbusAdress = modbusAdress;
	}

	public double getTemp1() {
		return temp1;
	}

	public void setTemp1(double temp1) {
		this.temp1 = temp1;
	}

	public double getTemp2() {
		return temp2;
	}

	public void setTemp2(double temp2) {
		this.temp2 = temp2;
	}

	public double getPressure1() {
		return pressure1;
	}

	public void setPressure1(double pressure1) {
		this.pressure1 = pressure1;
	}

	public double getPressure2() {
		return pressure2;
	}

	public void setPressure2(double pressure2) {
		this.pressure2 = pressure2;
	}

	public boolean isAlarm() {
		return alarm;
	}

	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}

	public boolean isRelay() {
		return relay;
	}

	public void setRelay(boolean relay) {
		this.relay = relay;
	}

	public CantekController(int modbusAdress) {
		this.uid = UUID.randomUUID().toString();
		this.modbusAdress = modbusAdress;
	}

	public void getMockData() {

		/* Random pressure datas in range (-5,20) */
		pressure1 = pressureMockData();
		pressure2 = pressureMockData();

		/* Random temp datas in range (-40,100) */
		temp1 = tempMockData();
		temp2 = tempMockData();
		/* Random booleans for Alarm and Relay */
		Random rand = new Random();
		alarm = rand.nextBoolean();
		relay = rand.nextBoolean();
	}

	public double tempMockData() {
		double maxTemp = 100.0;
		double minTemp = -40.0;
		Random randomT = new Random();
		double randomTempValue = minTemp + (maxTemp - minTemp) * randomT.nextDouble();
		return Double.parseDouble(String.format("%.2f", randomTempValue));

	}

	public double pressureMockData() {
		double maxTemp = 20.0;
		double minTemp = -5.0;
		Random randomT = new Random();
		double randomPressureValue = minTemp + (maxTemp - minTemp) * randomT.nextDouble();
		return Double.parseDouble(String.format("%.2f", randomPressureValue));
	}

}
