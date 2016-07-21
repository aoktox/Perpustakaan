package id.prasetiyo.perpustakan.models;

import java.io.Serializable;

/**
 * Created by aoktox on 22/04/16.
 */
public class Pengguna implements Serializable {
    private int id_anggota;
    private String email;
    private String nama;
    private String no_telp;
    private String alamat;

    public Pengguna() {
    }

    public Pengguna(int id_anggota,String email, String nama, String no_telp, String alamat) {
        this.id_anggota = id_anggota;
        this.email = email;
        this.nama = nama;
        this.no_telp = no_telp;
        this.alamat = alamat;
    }

    public int getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(int id_anggota) {
        this.id_anggota = id_anggota;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}