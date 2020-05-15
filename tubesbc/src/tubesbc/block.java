package tubesbc;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author skadi
 */
public class block {
    private String timestamp;
    private String nama_produk;
    private String kode_produk;
    private int harga;
    private String hash;
    private String prevhash;
    private int nonce;
    
    public block(String timestamp, String nama_produk, String kode_produk, int harga, int nonce, String hash, String prevhash){
        setNama_produk(nama_produk);
        setKode_produk(kode_produk);
        setHarga(harga);
        setHash(hash);
        setTimestamp(timestamp);
        setPrevhash(prevhash);
        setNonce(nonce);
        
    }
    public block(){
        
    }
    /**
     * @return the nama_produk
     */
    public String getNama_produk() {
        return nama_produk;
    }

    /**
     * @param nama_produk the nama_produk to set
     */
    public void setNama_produk(String nama_produk) {
        this.nama_produk = nama_produk;
    }

    /**
     * @return the kode_produk
     */
    public String getKode_produk() {
        return kode_produk;
    }

    /**
     * @param kode_produk the kode_produk to set
     */
    public void setKode_produk(String kode_produk) {
        this.kode_produk = kode_produk;
    }

    /**
     * @return the harga
     */
    public int getHarga() {
        return harga;
    }

    /**
     * @param harga the harga to set
     */
    public void setHarga(int harga) {
        this.harga = harga;
    }

    /**
     * @return the hash
     */
    public String getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * @return the prevhash
     */
    public String getPrevhash() {
        return prevhash;
    }

    /**
     * @param prevhash the prevhash to set
     */
    public void setPrevhash(String prevhash) {
        this.prevhash = prevhash;
    }

    /**
     * @return the nonce
     */
    public int getNonce() {
        return nonce;
    }

    /**
     * @param nonce the nonce to set
     */
    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) 
    { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
    }
   
}
