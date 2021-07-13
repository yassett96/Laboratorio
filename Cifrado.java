package Validaciones;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
/*author Jairo Martinez
 */
public class Cifrado {
    
    /*
    * Código fácil para cifrar y descifrar en Java ("encriptar" y "desencriptar").
    * No ha sido auditado, ni garantizo su seguridad.
    */
    public static byte[] cifra(String sinCifrar) throws Exception {
	final byte[] bytes = sinCifrar.getBytes("UTF-8");
	final Cipher aes = obtieneCipher(true);
	final byte[] cifrado = aes.doFinal(bytes);
	return cifrado;
    }


    public static String descifra(byte[] cifrado) throws Exception {
        
        final Cipher aes = obtieneCipher(false);
        System.out.println("llegada Final");
        System.out.println(cifrado.length);                
        //final byte[] bytes = aes.doFinal(cifrado.toString().trim().getBytes("UTF-8"));
        
        String by = new BASE64Decoder().decodeBuffer(cifrado.toString().trim()).toString().trim();
        //final byte[] bytes = aes.doFinal(by.getBytes("UTF-8"));
        //final String sinCifrar = new String(bytes, "UTF-8");
	return by;
}

    private static Cipher obtieneCipher(boolean paraCifrar) throws Exception {
	final String frase = "clave_encriptacion";
        System.out.println("llegada 1");
	final MessageDigest digest = MessageDigest.getInstance("SHA");
        System.out.println("llegada 2");
	digest.update(frase.getBytes("UTF-8"));
        System.out.println("llegada 3");
	final SecretKey key = new SecretKeySpec(digest.digest(),0,16, "AES");
        System.out.println("llegada 4");
	final Cipher aes = Cipher.getInstance("AES");
        System.out.println("llegada 5");
	if (paraCifrar) {
		aes.init(Cipher.ENCRYPT_MODE, key);
                System.out.println("llegada 10");
	} else {
		aes.init(Cipher.DECRYPT_MODE, key);
                System.out.println("llegada 10.1");
	}

	return aes;
    }
    
    public static void main(String[] args) {
        
    }
}
/*
 * Código fácil para cifrar y descifrar en Java ("encriptar" y "desencriptar").
 * No ha sido auditado, ni garantizo su seguridad.
 

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public byte[] cifra(String sinCifrar) throws Exception {
	final byte[] bytes = sinCifrar.getBytes("UTF-8");
	final Cipher aes = obtieneCipher(true);
	final byte[] cifrado = aes.doFinal(bytes);
	return cifrado;
}

public String descifra(byte[] cifrado) throws Exception {
	final Cipher aes = obtieneCipher(false);
	final byte[] bytes = aes.doFinal(cifrado);
	final String sinCifrar = new String(bytes, "UTF-8");
	return sinCifrar;
}

private Cipher obtieneCipher(boolean paraCifrar) throws Exception {
	final String frase = "FraseLargaConDiferentesLetrasNumerosYCaracteresEspeciales_áÁéÉíÍóÓúÚüÜñÑ1234567890!#%$&()=%_NO_USAR_ESTA_FRASE!_";
	final MessageDigest digest = MessageDigest.getInstance("SHA");
	digest.update(frase.getBytes("UTF-8"));
	final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

	final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
	if (paraCifrar) {
		aes.init(Cipher.ENCRYPT_MODE, key);
	} else {
		aes.init(Cipher.DECRYPT_MODE, key);
	}

	return aes;
}
*/





