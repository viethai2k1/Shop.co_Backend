package com.hai.backend.service.serviceimpl;

import com.hai.backend.entity.CategoryEntity;
import com.hai.backend.entity.ReviewEntity;
import com.hai.backend.entity.UserEntity;
import com.hai.backend.modal.Review;
import com.hai.backend.modal.UserReview;
import com.hai.backend.repository.ReviewRepository;
import com.hai.backend.repository.UserRepository;
import com.hai.backend.service.Format;
import com.hai.backend.service.ReviewService;
import com.hai.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getAllReview() {
        List<ReviewEntity> reviewEntities = reviewRepository.findAllByIsDeleteFalse();
        List<Review> reviews = new ArrayList<>();

        for (ReviewEntity entity: reviewEntities) {
            Review review = Format.ReviewEntityToReview(entity);
            reviews.add(review);
        }

        return reviews;
    }

    @Override
    public List<Review> getAllReviewByProductId(int id) {
        List<ReviewEntity> reviewEntities = reviewRepository.findAllByProductIdAndIsDeleteFalseAndStatusTrue(id);

        List<Review> reviews = new ArrayList<>();

        for (ReviewEntity entity: reviewEntities) {
            Review review = Format.ReviewEntityToReview(entity);
            reviews.add(review);
        }

        return reviews;
    }

    @Override
    public ReviewEntity getReviewById(int id) {
        return reviewRepository.findByIdAndIsDeleteFalse(id);
    }

    @Override
    public ReviewEntity saveReview(ReviewEntity review) {
        return reviewRepository.save(review);
    }

    @Override
    public ReviewEntity updateReview(ReviewEntity reviewEntity) {
        return reviewRepository.save(reviewEntity);
    }

    @Override
    public Boolean check(int id) {
        return reviewRepository.existsById(id);
    }

}
