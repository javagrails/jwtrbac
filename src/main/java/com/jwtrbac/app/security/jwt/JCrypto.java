package com.jwtrbac.app.security.jwt;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class JCrypto {
    private static final byte[] key            = "MyDifficultPassw".getBytes();
    private static final String transformation = "AES"; //"AES/ECB/PKCS5Padding";

    public static void encrypt(Serializable object, OutputStream ostream) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        try {
            // Length is 16 byte
            SecretKeySpec sks = new SecretKeySpec(key, transformation);

            // Create cipher
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            SealedObject sealedObject = new SealedObject(object, cipher);

            // Wrap the output stream
            CipherOutputStream cos          = new CipherOutputStream(ostream, cipher);
            ObjectOutputStream outputStream = new ObjectOutputStream(cos);
            outputStream.writeObject(sealedObject);
            outputStream.close();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public static Object decrypt(InputStream istream) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKeySpec sks    = new SecretKeySpec(key, transformation);
        Cipher        cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, sks);

        CipherInputStream cipherInputStream = new CipherInputStream(istream, cipher);
        ObjectInputStream inputStream       = new ObjectInputStream(cipherInputStream);
        SealedObject      sealedObject;
        try {
            sealedObject = (SealedObject) inputStream.readObject();
            return sealedObject.getObject(cipher);
        } catch (ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    static class Person implements Serializable {
        private static final long   serialVersionUID = 0;
        private final        String name;
        private final        int    age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Person person = (Person) o;

            if (age != person.age) {
                return false;
            }
            if (!name.equals(person.name)) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age;
            return result;
        }
    }


    public static void main(String[] args) {
        try {
            //JCrypto.testEncryptDecryptString();
            //JCrypto.testEncryptDecryptPerson();
            JCrypto.testEncryptDecryptPersonList();
            System.out.println("____");
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testEncryptDecryptString() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        String orig = "hello";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        encrypt(orig, baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        //assertEquals(orig, decrypt(bais));

        System.out.println(orig.equals(decrypt(bais)));
        System.out.println("____");
    }

    public static void testEncryptDecryptPerson() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        Person orig = new Person("Jack", 21);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        encrypt(orig, baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        //assertEquals(orig, decrypt(bais));

        System.out.println(orig.equals(decrypt(bais)));
        System.out.println("____");
    }

    public static void testEncryptDecryptPersonList() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        List<Person> people = new ArrayList<>();
        Person p1 = new Person("Jack", 21);
        Person p2 = new Person("Zill", 22);
        people.add(p1);
        people.add(p2);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        encrypt((Serializable) people, baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        //assertEquals(orig, decrypt(bais));

        System.out.println(people.equals(decrypt(bais)));
        System.out.println("____");
    }
}
