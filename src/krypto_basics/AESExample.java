package krypto_basics;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class AESExample {
	
	//Länge des Initialisierungsvektors (IV) für AES (128-bit Blockgröße)
	private static final int IV_SIZE = 16;
	
	public static void main(String[] args) throws Exception{
		//1.Text, that we want to encrypt
		String plainText = "AES encrypted message!";
		System.out.println("Plain text:" + plainText);
		
		//2.Generate key (AES-256)
		SecretKey key = generateAESKey(256);
		
		//3.Generate IV (for CBC)
		byte[] iv = generateIV();
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		
		//4.Encryption
		String encryptedText = encryptAES(plainText, key, ivSpec);
		System.out.println("Encrypted: " + encryptedText);
		
		//5.Decryption
		String decryptedText = decryptAES(encryptedText, key, ivSpec);
		System.out.println("Decrypted: " + decryptedText);
	}
	
	//Generate AES-key
	public static SecretKey generateAESKey(int keySize) throws Exception{
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(keySize); //128, 192 oder 256
		return keyGen.generateKey();
	}
	
	//Generate initialization vector
	public static byte[] generateIV() {
		byte[] iv = new byte[IV_SIZE];
		new SecureRandom().nextBytes(iv);
		return iv;
	}
	
	//AES Encryption (CBC with PKCS5 Padding)
	public static String encryptAES(String plainText, SecretKey key, IvParameterSpec iv) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] encrypted = cipher.doFinal(plainText.getBytes());
		return Base64.getEncoder().encodeToString(encrypted);
	}
	
	//AES Decryption
	public static String decryptAES(String encryptedText, SecretKey key, IvParameterSpec iv) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
		return new String(decryptedBytes);
	}
}
