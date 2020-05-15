package tubesbc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author skadi
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
public class DB {
    public static Connection con;
    public static Statement stm;
    public  void connect(){
        try {
            String url ="jdbc:mysql://localhost/tubesbc";
            String user="root";
            String pass="";
            Class.forName("com.mysql.jdbc.Driver");
            con =DriverManager.getConnection(url,user,pass);
            stm = con.createStatement();
            System.out.println("koneksi berhasil;");
        } catch (Exception e) {
            System.err.println("koneksi gagal" +e.getMessage());
        }
    }
    public void insertdata(block b){
        try {
            String query = "insert into data (timestamp, nama_produk, kode_produk, harga, hash, prevhash, nonce) VALUES ('"+b.getTimestamp()+"', '"+b.getNama_produk()+"', '"+b.getKode_produk()+"', "+b.getHarga()+", '"+b.getHash()+"', '"+b.getPrevhash()+"', "+b.getNonce()+")";
            Statement s = con.createStatement();
            s.executeUpdate(query);
            System.out.println("insert berhasil");
        } catch (SQLException ex) {
            System.out.println("insert gagal "+ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    public ArrayList<block> ambildata(){
        try {
            String query = "select * from data";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<block> isidb=new ArrayList();
            block i=null;
            
            while (rs.next()) {
                
                String time =rs.getString(1);
                String nama_produk =rs.getString(2);
                String kode_produk = rs.getString(3);
                int harga = Integer.parseInt(rs.getString(4));
                String hash =rs.getString(5);
                String prevhash = rs.getString(6);
                int nonce =Integer.parseInt(rs.getString(7));
                
                i = new block(time,nama_produk,kode_produk,harga,nonce,hash,prevhash);
                isidb.add(i);
            }
            System.out.println("ambil berhasil");
            return isidb;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void updatedata(block b){
        try{
            String query = "update data set timestamp = '"+b.getTimestamp()+"', nama_produk = '"+b.getNama_produk()+"', kode_produk = '"+b.getKode_produk()+"', harga = "+b.getHarga()+", hash = '"+b.getHash()+"', nonce = "+b.getNonce()+" where prevhash = '"+b.getPrevhash()+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("update berhasil");
        }
        catch (SQLException e){
            System.out.println("update gagal");
            e.printStackTrace();
        }
    }
    
    public void deletedata(block b){
            try{
                String query = "delete from data where hash = '"+b.getHash()+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
                System.out.println("delete berhasil");
            }
            catch (SQLException e){
                System.out.println("delete gagal");
                e.printStackTrace();
            }
                
    }
    
    
    public void insertdatar(block b){
        try {
            String query = "insert into rec (timestamp, nama_produk, kode_produk, harga, hash, prevhash, nonce) VALUES ('"+b.getTimestamp()+"', '"+b.getNama_produk()+"', '"+b.getKode_produk()+"', "+b.getHarga()+", '"+b.getHash()+"', '"+b.getPrevhash()+"', "+b.getNonce()+")";
            Statement s = con.createStatement();
            s.executeUpdate(query);
            System.out.println("insert berhasil");
        } catch (SQLException ex) {
            System.out.println("insert gagal"+ex.getMessage());
            ex.printStackTrace();
        }
        
    }
    public ArrayList<block> ambildatar(){
        try {
            String query = "select * from rec";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            ArrayList<block> isidb=new ArrayList();
            block i=null;
            
            while (rs.next()) {
                
                String time =rs.getString(1);
                String nama_produk =rs.getString(2);
                String kode_produk = rs.getString(3);
                int harga = Integer.parseInt(rs.getString(4));
                String hash =rs.getString(5);
                String prevhash = rs.getString(6);
                int nonce =Integer.parseInt(rs.getString(7));
                
                i = new block(time,nama_produk,kode_produk,harga,nonce,hash,prevhash);
                isidb.add(i);
            }
            System.out.println("ambil berhasil");
            return isidb;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void updatedatar(block b){
        try{
            String query = "update rec set timestamp = '"+b.getTimestamp()+"', nama_produk = '"+b.getNama_produk()+"', kode_produk = '"+b.getKode_produk()+"', harga = "+b.getHarga()+", hash = '"+b.getHash()+"', nonce = "+b.getNonce()+" where prevhash = '"+b.getPrevhash()+"'";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
            System.out.println("update berhasil");
        }
        catch (SQLException e){
            System.out.println("update gagal");
            e.printStackTrace();
        }
    }
    
    public void deletedatar(block b){
            try{
                String query = "delete from rec where hash = '"+b.getHash()+"'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
                System.out.println("delete berhasil");
            }
            catch (SQLException e){
                System.out.println("delete gagal");
                e.printStackTrace();
            }
                
    }
    
}
