package com.cryptography.project.sender.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.cryptography.project.sender.controller.CantekController;

/**
 * @author canberkardic
 */

public class SenderDevice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String uid;
	private List<CantekController> controllers;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public List<CantekController> getControllers() {
		return controllers;
	}

	public void setControllers(List<CantekController> controllers) {
		this.controllers = controllers;
	}

	/*
	 * 
	 * try { String jsonInString = obj2Json.writeValueAsString(c);
	 * System.out.println(jsonInString); } catch (JsonProcessingException e) {
	 * System.out.println("Json error : " + e.getMessage()); }
	 *
	 */
	public SenderDevice() {
		this.uid = "OG" + UUID.randomUUID().toString();

		controllers = new ArrayList<>();
		controllers.add(new CantekController(1));
		controllers.add(new CantekController(2));
	}

	public void updateMockData() {
		/* Convert Java Object to JSON */
		for (CantekController c : controllers) {
			c.getMockData();
		}
	}

}
