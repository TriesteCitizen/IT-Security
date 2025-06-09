package krypto_basics;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;

//Class to create an asymmetric key
public class RSAExample {
	
	private static final String RSA = "RSA";
	
	//Generating public and private keys
	//using RSA algorithm
	public static KeyPair generateRSAKkeyPair() throws Exception {
		SecureRandom secureRandom = new SecureRandom(); 
		//This class generates public & private key. Its used to generate random numbers
		
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
		//The KeyPairGenerator provides a getInstance() method which can be used to pass a string variable which
		//denotes the Key Generation Algorithm. It returns KeyGenerator Object. We are using RSA algorithm for
		//generating the keys
		
		keyPairGenerator.initialize(2048,secureRandom);
		//Initializing the keyGenerator object with 2048 bits key size and passing the random number
		
		return keyPairGenerator.generateKeyPair();
		//Now the secret key is generated and if we wish to actually see the generated key which is an object, we can
		//convert it with Base64
	}
	
	//Encryption function which converts the plainText into a cipherText using privateKey
	public static byte[] do_RSAEncryption(String plainText, PublicKey publicKey) throws Exception{
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(plainText.getBytes());
		//The doFinal() method is invoked on cipher which encrypts/decrypts data in a single-part operation, or
		//finishes a multiple-part operation and returns byte array 
	}
	
	//Decryption function which converts the cipherText into the prior plainText using publicKey
	public static String do_RSADecryption(byte[] cipherText, PrivateKey privateKey) throws Exception{
		Cipher cipher = Cipher.getInstance(RSA);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] result = cipher.doFinal(cipherText);
		return new String(result);
	}
	
	//Driver code
	public static void main(String args[]) throws Exception {
		KeyPair keypair = generateRSAKkeyPair();
		
		String plainText = "This is a PlainText. I want to encrypt using RSA.";
		byte[] cipherText = do_RSAEncryption(plainText, keypair.getPublic());
		Base64.Encoder encoder = Base64.getEncoder();
		
		System.out.println("Public key is: " + encoder.encodeToString(keypair.getPublic().getEncoded()));
		System.out.println("Private key is: " + encoder.encodeToString(keypair.getPrivate().getEncoded()));
		System.out.println("Encrypted Text (Base64): " + encoder.encodeToString(cipherText));
		
		String decryptedText = do_RSADecryption(cipherText, keypair.getPrivate());
		System.out.println("The Decrypted Text is: " + decryptedText);
	}

}
