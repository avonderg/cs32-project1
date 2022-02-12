package edu.brown.cs.student.main;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BloomHashes {
    /**
     * Generates hashes based on the contents of an array of bytes, converts the result into
     * BigIntegers, and stores them in an array. The hash function is called until the required number
     * of BigIntegers are produced.
     * For each call to the hash function a salt is prepended to the data. The salt is increased by 1
     * for each call.
     *
     * @param data      input data.
     * @param numHashes number of hashes/BigIntegers to produce.
     * @return array of BigInteger hashes
     */
    public static BigInteger[] createHashes(byte[] data, int numHashes) throws NoSuchAlgorithmException {
        BigInteger[] result = new BigInteger[numHashes];

        int k = 0;
        BigInteger salt = BigInteger.valueOf(0);
        while (k < numHashes) {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            sha.update(salt.toByteArray());
            salt = salt.add(BigInteger.valueOf(1));
            byte[] hash = sha.digest(data);
            sha.reset();

            // convert hash byte array to hex string, then to BigInteger
            String hexHash = bytesToHex(hash);
            result[k] = new BigInteger(hexHash, 16);
            k++;
        }
        return result;
    }

    /**
     * Converts a byte array to a hex string.
     * Source: https://stackoverflow.com/a/9855338
     *
     * @param bytes the byte array to convert
     * @return the hex string
     */
    private static String bytesToHex(byte[] bytes) {
        byte[] hexArray = "0123456789ABCDEF".getBytes(StandardCharsets.UTF_8);
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

}
