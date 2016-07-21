package id.prasetiyo.perpustakan.models;

import java.io.Serializable;

/**
 * Created by aoktox on 22/04/16.
 */
public class Peminjaman implements Serializable {
    private int id_pinjam;
    private int id_anggota;
    private int id_buku;
    private String tgl_pinjam;
    private String tgl_kembali;
    private int status;

    public Peminjaman() {
    }

    public Peminjaman(int id_pinjam, int id_anggota, int id_buku, String tgl_pinjam, String tgl_kembali, int status) {

        this.id_pinjam = id_pinjam;
        this.id_anggota = id_anggota;
        this.id_buku = id_buku;
        this.tgl_pinjam = tgl_pinjam;
        this.tgl_kembali = tgl_kembali;

        this.status = status;
    }

    public int getId_pinjam() {
        return id_pinjam;
    }

    public void setId_pinjam(int id_pinjam) {
        this.id_pinjam = id_pinjam;
    }

    public int getId_anggota() {
        return id_anggota;
    }

    public void setId_anggota(int id_anggota) {
        this.id_anggota = id_anggota;
    }

    public int getId_buku() {
        return id_buku;
    }

    public void setId_buku(int id_buku) {
        this.id_buku = id_buku;
    }

    public String getTgl_pinjam() {
        return tgl_pinjam;
    }

    public void setTgl_pinjam(String tgl_pinjam) {
        this.tgl_pinjam = tgl_pinjam;
    }

    public String getTgl_kembali() {
        return tgl_kembali;
    }

    public void setTgl_kembali(String tgl_kembali) {
        this.tgl_kembali = tgl_kembali;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
