package id.prasetiyo.perpustakan.models;

import java.io.Serializable;

/**
 * Created by aoktox on 22/04/16.
 */
public class Books implements Serializable {
    private int id_buku;
    private String judul;
    private String pengarang;
    private String penerbit;
    private String jumlah;

    public Books(int id_buku, String judul, String pengarang, String penerbit, String jumlah) {
        this.id_buku = id_buku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.penerbit = penerbit;
        this.jumlah = jumlah;
    }

    public int getId_buku() {
        return id_buku;
    }

    public void setId_buku(int id_buku) {
        this.id_buku = id_buku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }
}

