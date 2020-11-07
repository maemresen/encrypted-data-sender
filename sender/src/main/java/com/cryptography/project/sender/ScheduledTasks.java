package com.cryptography.project.sender;

import com.cryptography.project.sender.helper.HttpRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cryptography.project.sender.model.SenderDevice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.cryptography.project.securelib.symmetric.SymmetricCrypto;

/**
 * @author canberkardic
 */
@Component
public class ScheduledTasks {

	@Scheduled(fixedRate = 5000)
	public void generateMockData() {
		SenderDevice senderDevice = new SenderDevice();
		senderDevice.updateMockData();
		ObjectMapper obj2Json = new ObjectMapper();
		try {

			/* Initialize Data */
			String senderDeviceValue = obj2Json.writeValueAsString(senderDevice);
			System.out.println("======= Plain Text : ");
			System.out.println(senderDeviceValue);
			System.out.println("Size : " + senderDeviceValue.length());

			/* Encryption */
			SymmetricCrypto symmetricCrypto = new SymmetricCrypto("MySecret", 16, "AES");
			String encrypted_msg = symmetricCrypto.encryptText(senderDeviceValue);

			/* Printing Encrypting Message */
			System.out.println("======= Encrypted Text : ");
			System.out.println(encrypted_msg);

			/* Sending Data */
			HttpRequest.doGet("http://localhost:8080/saveValues?json=" + encrypted_msg);

		} catch (JsonProcessingException e) {
			System.out.println("Json error : " + e.getMessage());
		} catch (Exception e) {
			System.out.println("General : " + e.getMessage());
		}

	}
}
