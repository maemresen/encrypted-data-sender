package com.cryptography.project.listener.controller;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cryptography.project.securelib.symmetric.SymmetricCrypto;

/**
 * @author Emre Sen
 * @date Dec 26, 2018
 * @contact maemresen07@gmail.com
 */
@RestController
public class SenderDeviceController {

	@GetMapping("/saveValues")
	public boolean saveValues(@RequestParam("json") String json) {
		try {

			/* Printing Encrypted Message */
			System.out.println("======= Encrypted Text : ");
			System.out.println(json);

			/* Decryption */
			SymmetricCrypto symmetricCrypto = new SymmetricCrypto("MySecret", 16, "AES");
			String encrypted_msg = json;
			String decrypted_msg = symmetricCrypto.decryptText(encrypted_msg);

			/* Printing Decrypted Message */
			System.out.println("======= Decrypted Text : ");
			System.out.println(decrypted_msg);

		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
