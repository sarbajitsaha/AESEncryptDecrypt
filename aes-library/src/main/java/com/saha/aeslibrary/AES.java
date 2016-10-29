package com.saha.aeslibrary;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public final class AES
{

    private static final String AES_MODE = "AES/CBC/PKCS7Padding";
    private static final String CHARSET = "UTF-8";
    private static final String HASH_ALGORITHM = "SHA-256";
    private static String password;

    private static final byte[] ivBytes = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};


    public static void setKey(String key)
    {
        password = key;
    }

    private static SecretKeySpec generateKey(final String password) throws NoSuchAlgorithmException,
            UnsupportedEncodingException
    {
        final MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }


    public static String encrypt(String message)
            throws GeneralSecurityException
    {

        if (password == null)
        {
            throw new GeneralSecurityException("Key is null");
        }

        try
        {
            final SecretKeySpec key = generateKey(password);
            byte[] cipherText = encrypt(key, ivBytes, message.getBytes(CHARSET));
            return Base64.encodeToString(cipherText, Base64.NO_WRAP);
        }
        catch (Exception e)
        {
            throw new GeneralSecurityException(e);
        }
    }


    private static byte[] encrypt(final SecretKeySpec key, final byte[] iv, final byte[] message)
            throws GeneralSecurityException
    {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher.doFinal(message);
    }


    public static String decrypt(String base64EncodedCipherText)
            throws GeneralSecurityException
    {

        if (password == null)
        {
            throw new GeneralSecurityException("Key is null");
        }

        try
        {
            final SecretKeySpec key = generateKey(password);
            byte[] decodedCipherText = Base64.decode(base64EncodedCipherText, Base64.NO_WRAP);
            byte[] decryptedBytes = decrypt(key, ivBytes, decodedCipherText);
            return new String(decryptedBytes, CHARSET);
        }
        catch (Exception e)
        {
            throw new GeneralSecurityException(e);
        }
    }


    private static byte[] decrypt(final SecretKeySpec key, final byte[] iv, final byte[] decodedCipherText)
            throws GeneralSecurityException
    {
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        return cipher.doFinal(decodedCipherText);
    }

    private AES()
    {
    }
}