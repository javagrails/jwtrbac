package com.jwtrbac.app.security.jwt;

import com.jwtrbac.app.web.rest.vm.HeaderInfo;

import javax.crypto.*;
import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class EDOWithDES {
    private static Cipher ecipher;
    private static Cipher dcipher;

    private static SecretKey key;

    static {
        try {
            key = KeyGenerator.getInstance("DES").generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static SealedObject encrypt(Serializable object) {
        SealedObject sealed = null;
        try {
            ecipher = Cipher.getInstance("DES");
            ecipher.init(Cipher.ENCRYPT_MODE, key);
            sealed = new SealedObject(object, ecipher);
            return sealed;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return sealed;
    }

    public static Object decrypt(Object objectIn) {
        Object objectOrObjects = null;
        SealedObject sealed = (SealedObject) objectIn;
        try {
            dcipher = Cipher.getInstance("DES");
            dcipher.init(Cipher.DECRYPT_MODE, key);

            String algorithm = sealed.getAlgorithm();
            System.out.println("Algorithm " + algorithm);
            objectOrObjects = sealed.getObject(dcipher);
            System.out.println("Real Serializable Object/Objects: " + objectOrObjects);
            return objectOrObjects;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return objectOrObjects;
    }

    public static void main(String[] args) {
        List<HeaderInfo> headerInfos = new ArrayList<>();
        HeaderInfo       headerInfo1 = new HeaderInfo(1l, "key1", "value1");
        HeaderInfo       headerInfo2 = new HeaderInfo(2l, "key2", "value2");
        HeaderInfo       headerInfo3 = new HeaderInfo(3l, "key3", "value3");
        headerInfos.add(headerInfo1);
        headerInfos.add(headerInfo2);
        headerInfos.add(headerInfo3);


        SealedObject encrypt = EDOWithDES.encrypt(headerInfo1);
        Object       decrypt = EDOWithDES.decrypt(encrypt);
        System.out.println("Original Object: " + decrypt);

        SealedObject encrypts = EDOWithDES.encrypt((Serializable) headerInfos);
        Object       decrypts = EDOWithDES.decrypt(encrypts);
        System.out.println("Original Objects: " + decrypts);

    }


    /*public static void main_ol(String[] args) {

        try {

            // generate secret key using DES algorithm
            key = KeyGenerator.getInstance("DES").generateKey();

            ecipher = Cipher.getInstance("DES");
            dcipher = Cipher.getInstance("DES");

            // initialize the ciphers with the given key

            ecipher.init(Cipher.ENCRYPT_MODE, key);

            dcipher.init(Cipher.DECRYPT_MODE, key);

            // create a sealed object
            List<HeaderInfo> headerInfos = new ArrayList<>();
            HeaderInfo       headerInfo1 = new HeaderInfo(1l, "key1", "value1");
            HeaderInfo       headerInfo2 = new HeaderInfo(2l, "key2", "value2");
            HeaderInfo       headerInfo3 = new HeaderInfo(3l, "key3", "value3");
            headerInfos.add(headerInfo1);
            headerInfos.add(headerInfo2);
            headerInfos.add(headerInfo3);

            SealedObject sealed = new SealedObject((Serializable) headerInfos, ecipher);

            // get the algorithm with the object has been sealed

            String algorithm = sealed.getAlgorithm();

            System.out.println("Algorithm " + algorithm);

            // unseal (decrypt) the object

            Object o = sealed.getObject(dcipher);

            System.out.println("Original Object: " + o);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("No Such Algorithm:" + e.getMessage());
            return;
        } catch (NoSuchPaddingException e) {
            System.out.println("No Such Padding:" + e.getMessage());
            return;
        } catch (BadPaddingException e) {
            System.out.println("Bad Padding:" + e.getMessage());
            return;
        } catch (InvalidKeyException e) {
            System.out.println("Invalid Key:" + e.getMessage());
            return;
        } catch (IllegalBlockSizeException e) {
            System.out.println("Illegal Block:" + e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found:" + e.getMessage());
            return;
        } catch (IOException e) {
            System.out.println("I/O Error:" + e.getMessage());
            return;
        }

    }*/
}
