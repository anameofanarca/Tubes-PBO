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

public class RegisteredUser extends User {

    private String username;
    private String email;
    private String password;
    private List<content> favorites;
    private List<content> watchlist;
    private List<content> recommendations;
    private List<content> contentHistory;
// Constructor

    public RegisteredUser(int id, String role, String username, String email, String password) {
        super(id, role);

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Email tidak valid.");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password harus minimal 6 karakter.");
        }

        this.username = username;
        this.email = email;
        this.password = password;
        this.favorites = new ArrayList<>();
        this.watchlist = new ArrayList<>();
        this.recommendations = new ArrayList<>();
        this.contentHistory = new ArrayList<>();
    }
//    
//    setter-getter atribut non list
//    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//    setter-getter atribut list
//    

    public List<content> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<content> favorites) {
        this.favorites = favorites;
    }

    public List<content> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(List<content> watchlist) {
        this.watchlist = watchlist;
    }

    public List<content> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<content> recommendations) {
        this.recommendations = recommendations;
    }

    public List<content> getcontentHistory() {
        return contentHistory;
    }

    public void setcontentHistory(List<content> contentHistory) {
        this.contentHistory = contentHistory;
    }

//    Tambah-Hapus method list
    public void addToFavorites(content c) {
        if ("Guest".equals(getRole())) {
            throw new IllegalArgumentException("Anda perlu login untuk mendapatkan fitur ini!");
        }

        if (!favorites.contains(c)) {
            favorites.add(c);
            System.out.println(c.getTitle() + " ditambahkan ke watchlist.");
        } else {
            System.out.println(c.getTitle() + " sudah ada di watchlist.");
        }
    }

    public void addToWatchList(content c) {
        if ("Guest".equals(getRole())) {
            throw new IllegalArgumentException("Anda perlu login untuk mendapatkan fitur ini!");
        }

        if (!watchlist.contains(c)) {
            watchlist.add(c);
            System.out.println(c.getTitle() + " ditambahkan ke watchlist.");
        } else {
            System.out.println(c.getTitle() + " sudah ada di watchlist.");
        }
    }

    // Asumsikan kamu punya akses ke daftar semua konten
    private static List<content> allcontents = new ArrayList<>(); // Isi bisa dari database

    public void addToRecommendations(content c) {
        if (c.getGenres().isEmpty()) {
            System.out.println("Tidak ada genre untuk mencari rekomendasi.");
            return;
        }

        List<content> sameGenrecontents = new ArrayList<>();

        for (content other : allcontents) {
            if (other.equals(c)) {
                continue; // Lewati konten yang sama
            }
            // Jika ada minimal satu genre yang sama
            for (String genre : c.getGenres()) {
                if (other.getGenres().contains(genre)) {
                    sameGenrecontents.add(other);
                    break;
                }
            }
        }

        // Ambil maksimal 2 konten, hindari duplikasi di recommendations
        int count = 0;
        for (content rec : sameGenrecontents) {
            if (!recommendations.contains(rec)) {
                recommendations.add(rec);
                System.out.println("Rekomendasi: " + rec.getTitle());
                count++;
                if (count >= 2) {
                    break;
                }
            }
        }

        if (count == 0) {
            System.out.println("Tidak ada rekomendasi tersedia.");
        }
    }

    public void addTocontentHistory(content c) {
        if (c == null) {
            System.out.println("Konten tidak valid.");
            return;
        }

        // Tambahkan ke riwayat jika belum ada
        if (!contentHistory.contains(c)) {
            contentHistory.add(c);
            System.out.println(c.getTitle() + " ditambahkan ke riwayat.");

            // Setelah tambah ke riwayat, cari rekomendasi
            addToRecommendations(c);
        } else {
            System.out.println(c.getTitle() + " sudah ada di riwayat.");
        }
    }

    public void removeFromWatchlist(content c) {
        if (watchlist.remove(c)) {
            System.out.println(c.getTitle() + " dihapus dari watchlist.");
        } else {
            System.out.println(c.getTitle() + " tidak ditemukan di watchlist.");
        }
    }

    public void removeFromFavorites(content c) {
        if (favorites.remove(c)) {
            System.out.println(c.getTitle() + " dihapus dari watchlist.");
        } else {
            System.out.println(c.getTitle() + " tidak ditemukan di watchlist.");
        }
    }

    public void tampilkanRiwayat() {
        System.out.println("Riwayat Konten:");
        for (content c : contentHistory) {
            System.out.println("- " + c.getTitle());
        }
    }

    public void logout() {
        this.setRole("Guest");
    }

    public void displayStatus() {
        System.out.println("Informasi Akun:");
        System.out.println("Nama: " + getUsername());
        System.out.println("Email: " + getEmail());
        System.out.println("User ID: " + getIdUser());
        System.out.println("Status: " + getRole());
    }
}
