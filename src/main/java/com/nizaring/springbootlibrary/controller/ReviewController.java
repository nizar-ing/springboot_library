package com.nizaring.springbootlibrary.controller;

import com.nizaring.springbootlibrary.requestmodels.ReviewRequest;
import com.nizaring.springbootlibrary.service.ReviewService;
import com.nizaring.springbootlibrary.utils.ExtractJWT;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("https://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/secure/user/book")
    public Boolean reviewBookByUser(@RequestHeader(name = "Authorization") String token, @RequestParam(name = "bookId") Long bookId) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if (userEmail == null) throw new Exception("User email is missing");
        return reviewService.userReviewListed(userEmail, bookId);
    }

    @PostMapping("/secure")
    public void postReview(@RequestHeader(name = "Authorization") String token, @RequestBody ReviewRequest reviewRequest) throws Exception {
        String userEmail = ExtractJWT.payloadJWTExtraction(token, "\"sub\"");
        if (userEmail == null) throw new Exception("User Email is missing");
        reviewService.postReview(userEmail, reviewRequest);
    }

}
