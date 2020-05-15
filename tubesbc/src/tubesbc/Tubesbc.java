/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubesbc;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author skadi
 */
public class Tubesbc { 
    public static ArrayList<block> listblock = new ArrayList();
    public static DB koneksi = new DB();
    
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
   
    // membuat block baru ketika isi database kosong
    public static block buatblock () throws NoSuchAlgorithmException{
        block b = new block();
        Random r = new Random();
        Scanner s = new Scanner(System.in);
        
        LocalDate time = java.time.LocalDate.now();
        String waktu = time.toString();
        System.out.println("masukkan nama produk");
        String nama_produk = s.nextLine();
        System.out.println("masukkan kode produk");
        String kode_produk = s.nextLine();
        System.out.println("masukkan harga");
        int harga = s.nextInt();
        String prev_hash = "0";
        int nonce = 0;
        
        String temp = toHexString(getSHA(waktu+nama_produk+kode_produk+Integer.toString(harga)+Integer.toString(nonce)));
        String tmp = temp.substring(0,4);
//        mining nonce sampai 4 hash pertama = "5402"
        if (!"5402".equalsIgnoreCase(tmp)){
            while (!"5402".equals(tmp)){
                nonce = r.nextInt(2147483647);
                temp = toHexString(getSHA(waktu+nama_produk+kode_produk+Integer.toString(harga)+Integer.toString(nonce)+prev_hash));
                tmp = temp.substring(0,4);
            }
        }
        b.setNama_produk(nama_produk);
        b.setHarga(harga);
        b.setKode_produk(kode_produk);
        b.setTimestamp(waktu);
        b.setNonce(nonce);
        b.setHash(temp);
        b.setPrevhash(prev_hash);
        
        return b;
    }
    // untuk menambah block baru
    public static block tambahblock(ArrayList listblock) throws NoSuchAlgorithmException{
        block b = new block();
        Random r = new Random();
        Scanner s = new Scanner(System.in);
        int last = listblock.size()-1;
        block tblock = (block) listblock.get(last);
        
        LocalDate time = java.time.LocalDate.now();
        String waktu = time.toString();
        System.out.println("masukkan nama produk");
        String nama_produk = s.nextLine();
        System.out.println("masukkan kode produk");
        String kode_produk = s.nextLine();
        System.out.println("masukkan harga");
        int harga = s.nextInt();
        String prev_hash = tblock.getHash();
        int nonce = 0;
        
        String temp = toHexString(getSHA(waktu+nama_produk+kode_produk+Integer.toString(harga)+Integer.toString(nonce)));
        String tmp = temp.substring(0,4);
//        mining nonce sampai 4 hash pertama = "5402"
        if (!"5402".equalsIgnoreCase(tmp)){
            while (!"5402".equals(tmp)){
                nonce = r.nextInt(2147483647);
                temp = toHexString(getSHA(waktu+nama_produk+kode_produk+Integer.toString(harga)+Integer.toString(nonce)+prev_hash));
                tmp = temp.substring(0,4);
            }
        }
        b.setNama_produk(nama_produk);
        b.setHarga(harga);
        b.setKode_produk(kode_produk);
        b.setTimestamp(waktu);
        b.setNonce(nonce);
        b.setHash(temp);
        b.setPrevhash(prev_hash);
        
        return b;
    }
    
    public static void viewdata(ArrayList listblock){
        for (int i=0; i<listblock.size(); i++){
            block tblock = (block) listblock.get(i);
            System.out.println("data ke "+i);
            System.out.println("waktu :"+tblock.getTimestamp());
            System.out.println("nama produk = "+tblock.getNama_produk());
            System.out.println("kode produk = "+tblock.getKode_produk());
            System.out.println("harga : "+tblock.getHarga());
            System.out.println("hash : "+tblock.getHash());
            System.out.println("previous hash = "+tblock.getPrevhash());
            
        }
        Scanner s = new Scanner(System.in);
        String k = s.nextLine();
    }
    
    public static block searchdata(ArrayList listblock){
        Scanner s = new Scanner(System.in);
        System.out.println("masukkan nama produk yang akan dicari");
        String cari = s.nextLine();
        block tblock = new block();
        int i = 0;
        while (!cari.equalsIgnoreCase(tblock.getNama_produk())){
            tblock = (block) listblock.get(i);
            i++;
        }
        if (cari.equalsIgnoreCase(tblock.getNama_produk())){
            System.out.println("produk ditemukan");
            return tblock;
        }
        else{
            System.out.println("produk tidak ditemukan");
            return null;
        }
        
    }
    
    public static block ubahdata (block b) throws NoSuchAlgorithmException{
        Scanner s = new Scanner(System.in);
        int pil = 0;
            System.out.println("pilih data yang ingin diubah");
            System.out.println("1. nama produk");
            System.out.println("2. kode produk");
            System.out.println("3. harga");
            System.out.println("4. exit");
            pil = s.nextInt();
            if (pil == 1){
                System.out.println("masukkan nama produk yang baru");
                String namabaru = s.nextLine();
                b.setNama_produk(namabaru);
            }
            if (pil == 2) {
                System.out.println("masukkan kode produk yang baru");
                String kodebaru = s.nextLine();
                b.setKode_produk(kodebaru);
            }
            if (pil == 3){
                System.out.println("masukkan harga produk yang baru");
                int hargabaru = s.nextInt();  
                b.setHarga(hargabaru);
            }
        Random r = new Random();
        String temp = toHexString(getSHA(b.getTimestamp()+b.getNama_produk()+b.getKode_produk()+Integer.toString(b.getHarga())+Integer.toString(b.getNonce())));
        String tmp = temp.substring(0,4);
//        mining nonce sampai 4 hash pertama = "5402"
        if (!"5402".equalsIgnoreCase(tmp)){
            while (!"5402".equals(tmp)){
                int nonce = r.nextInt(2147483647);
                temp = toHexString(getSHA(b.getTimestamp()+b.getNama_produk()+b.getKode_produk()+Integer.toString(b.getHarga())+Integer.toString(nonce)));
                tmp = temp.substring(0,4);
                if ("5402".equals(tmp)){
                    b.setNonce(nonce);
                    b.setHash(temp);
                }
            }
        }
        return b;
    }
    
    public static int cariindex (ArrayList listblock, block b){
        int i = 0;
        int k = listblock.size()-1;
        String carinama = b.getNama_produk();
        block tb = (block) listblock.get(i);
        while ((!carinama.equalsIgnoreCase(tb.getNama_produk() )) && (i<=k)){            
            tb = (block) listblock.get(i);
            i++;            
        }
        return i;
    }
    
    
    public static void main(String[] args) throws NoSuchAlgorithmException {
        // TODO code application logic here
        koneksi.connect();
        listblock = koneksi.ambildatar();
        Scanner s = new Scanner(System.in);
        int pil = 0;
        String k;
        System.out.println("selamat datang di program test blockchain");
        if (listblock.isEmpty()){
            System.out.println("database kosong isi dulu");
            block b = buatblock();
            koneksi.insertdata(b);
            koneksi.insertdatar(b);
            listblock.add(b);
            k = s.nextLine();
        }
        
        
        
        
        while(pil != 8){
            
            System.out.println("tentukan pilihanmu");
            System.out.println("1. tambah data");
            System.out.println("2. ubah data (resmi)");
            System.out.println("3. ubah data non resmi");
            System.out.println("4. delete data");
            System.out.println("5. lihat data pada database");
            System.out.println("6. lihat data pada data recovery");
            System.out.println("7. validasi dan recovery data");
            System.out.println("8. exit");
            System.out.println("masukkan pilihanmu");
            pil = s.nextInt();
            
            if (pil == 1){
                block b = tambahblock(listblock);
                listblock.add(b);
                koneksi.insertdata(b);
                koneksi.insertdatar(b);
                k = s.nextLine();
            }
            // ubah data resmi adalah ubah data yang direstui ubah di array list dan database
            if (pil == 2){
                block b = searchdata(listblock);
                block ubah = ubahdata(b);
                int indeks = cariindex(listblock,b);
                listblock.set(indeks, ubah);
                koneksi.updatedata(ubah);
                koneksi.updatedatar(ubah);
                k = s.nextLine();
            }
            // ubah data non resmi adalah ubah data yang tidak direstui atau ubah langsung di database / datapool (akan direstore pada validasi atau setelah exit)
            if (pil == 3){
                block tb = searchdata(listblock);
                block ubah = ubahdata(tb);
                koneksi.updatedata(ubah);
                k = s.nextLine();
            }
            //menghapus data pada data recovery sekaligus row pada database
            if (pil == 4){
                block tb = searchdata(listblock);
                int indeks = cariindex(listblock,tb);
                listblock.remove(indeks);
                koneksi.deletedata(tb);
                koneksi.deletedatar(tb);
                
                k = s.nextLine();
            }
            if (pil == 5){
                ArrayList<block> templist = koneksi.ambildata();
                viewdata(templist);
                k = s.nextLine();
            }
            if (pil == 6){
                ArrayList<block> templist = koneksi.ambildatar();
                viewdata(templist);
                k = s.nextLine();
            }
            if (pil == 7){
                ArrayList<block> tdb = koneksi.ambildata();
                ArrayList<block> tdr = koneksi.ambildatar();
                int i = 0;
                block tblock1 = tdb.get(i); //dari database
                block tblock2 = tdr.get(i); // dari data recovery
                
                while (i <= (tdr.size()-1)){
                    tblock1 = tdb.get(i); //dari database
                    tblock2 = tdr.get(i); // dari data recovery
                    if (!tblock1.getHash().equalsIgnoreCase(tblock2.getHash())){
                        System.out.println("data tidak valid");
                        System.out.println("ada yang berubah di data ke "+ i);
                        tdb.set(i, tblock2);
                        koneksi.updatedata(tblock2);
                        i = 0;
                    }
                    else{
                        i++;
                    }
                }
                k = s.nextLine();
                if (tblock1.getHash().equalsIgnoreCase(tblock2.getHash())){
                    System.out.println("data valid");
                    k = s.nextLine();
                }
                k = s.nextLine();
            }
           
        }
        
        ArrayList<block> tdb = koneksi.ambildata();
        ArrayList<block> tdr = koneksi.ambildatar();
        int i = 0;
        block tblock1 = tdb.get(i); //dari database
        block tblock2 = tdr.get(i); // dari data recovery
        
        while (i <= (tdr.size()-1)){
            tblock1 = tdb.get(i); //dari database
            tblock2 = tdr.get(i); // dari data recovery
            if (!tblock1.getHash().equalsIgnoreCase(tblock2.getHash())){
                System.out.println("data tidak valid");
                System.out.println("ada yang berubah di data ke "+ i);
                tdb.set(i, tblock2);
                koneksi.updatedata(tblock2);
                i = 0;
            }
            else{
                i++;
            }
        }
        k = s.nextLine();
        if (tblock1.getHash().equalsIgnoreCase(tblock2.getHash())){
            System.out.println("data valid");
            k = s.nextLine();
        }
        k = s.nextLine();
    }                 

    private static void tambahdata(ArrayList<block> listblock) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
