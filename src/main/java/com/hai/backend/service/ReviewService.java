package com.hai.backend.service;

import com.hai.backend.entity.CategoryEntity;
import com.hai.backend.entity.ReviewEntity;
import com.hai.backend.entity.UserEntity;
import com.hai.backend.modal.Review;
import com.hai.backend.modal.UserReview;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReview();

    List<Review> getAllReviewByProductId(int id);

    ReviewEntity getReviewById(int id);

    ReviewEntity saveReview(ReviewEntity review);

    ReviewEntity updateReview(ReviewEntity reviewEntity);

    Boolean check(int id);
}
