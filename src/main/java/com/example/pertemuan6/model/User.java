package com.example.pertemuan6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity // Menandakan ini sebagai tabel database
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Nama;
    private String NIM;
    private String JenisKelamin;
    private String fotoPath;    // Nama file foto
    private String deskripsi;   // Deskripsi foto
}
