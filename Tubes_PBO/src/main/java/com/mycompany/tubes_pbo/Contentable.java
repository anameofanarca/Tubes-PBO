/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes_pbo;

/**
 *
 * @author user
 */
import java.util.*;
public interface Contentable {           
    float getAverageRating();     // mengembalikan rata-rata rating dari review
    int getReviewCount();         // jumlah review
    List<String> getGenres();     // genre dari konten ini
    void info();
}
