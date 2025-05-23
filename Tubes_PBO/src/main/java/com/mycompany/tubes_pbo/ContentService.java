/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tubes_pbo;

/**
 *
 * @author user
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import java.util.ArrayList;
class ContentService {
    private List<Content> contents;

    public ContentService() {
        contents = new ArrayList<>();
    }

    public void addContent(Content content) {
        contents.add(content);
    }

    public List<Content> searchContent(String keyword) {
        return contents.stream()
                .filter(c -> c.title.contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Content> filterByGenre(String genre) {
        return contents.stream()
                .filter(c -> c.genres.contains(genre))
                .collect(Collectors.toList());
    }
    
    public List<Content> getTopRated() {
        float maxAvgRating = contents.stream()
                .map(ContentService::calculateAverageRating)
                .max(Float::compare)
                .orElse(0f);

        return contents.stream()
                .filter(c -> calculateAverageRating(c) == maxAvgRating)
                .collect(Collectors.toList());
    }

    public List<Content> getMostReviewed() {
        int maxReviews = contents.stream()
                .mapToInt(c -> c.reviews.size())
                .max()
                .orElse(0);

        return contents.stream()
                .filter(c -> c.reviews.size() == maxReviews)
                .collect(Collectors.toList());
    }

    // Helper method untuk menghitung rata-rata rating dari seluruh review
    private static float calculateAverageRating(Content content) {
        if (content.reviews.isEmpty()) return 0f;

        float total = 0f;
        for (Review review : content.reviews) {
            total += review.getRating().getAvgRate();
        }
        return total / content.reviews.size();
    }

}
