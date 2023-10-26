package com.hai.backend.service;

import com.hai.backend.entity.ReviewEntity;
import com.hai.backend.entity.UserEntity;
import com.hai.backend.modal.Review;
import com.hai.backend.modal.UserReview;

public class Format {
    public static Review ReviewEntityToReview(ReviewEntity entity){
        Review review = new Review();
        review.setId(entity.getId());
        review.setReview(entity.getReview());
        review.setStatus(entity.isStatus());
        review.setRate(entity.getRate());
        review.setDelete(entity.isDelete());
        review.setCreateDate(entity.getCreateDate());
        review.setProduct(entity.getProduct());


        UserReview userReview = Format.UserEntityToUserReview(entity.getUser());

        review.setUser(userReview);

        return review;
    }

    public static UserReview UserEntityToUserReview(UserEntity userEntity){
        UserReview userReview = new UserReview();
        userReview.setId(userEntity.getId());
        userReview.setFullname(userEntity.getFullname());
        userReview.setStatus(userEntity.isStatus());
        userReview.setCreateDate(userEntity.getCreateDate());
        userReview.setImage(userEntity.getImage());

        return userReview;
    }
}
