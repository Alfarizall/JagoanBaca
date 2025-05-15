package com.example.crud.Model;

import jakarta.persistence.*;

@Entity
public class Mahasiswa {
    @Id
    private String nrp;
    private String nama;

    // Getter dan Setter
    public String getNrp() { return nrp; }
    public void setNrp(String nrp) { this.nrp = nrp; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
}
