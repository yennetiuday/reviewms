package com.jobx.reviewms.review.impl;

import java.util.List;
import java.util.Objects;

import com.jobx.reviewms.review.Review;
import com.jobx.reviewms.review.ReviewRepository;
import com.jobx.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean createReview(Long companyId, Review review) {

        if(Objects.nonNull(companyId) && Objects.nonNull(review)) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        } else return false;
    }

    @Override
    public Review getReviewById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = getReviewById(reviewId);
        if(Objects.nonNull(review)) {
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            reviewRepository.save(review);
            return true;
        } else
            return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        if(reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
