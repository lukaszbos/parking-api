package com.lukasz.api.utils;

import com.lukasz.api.carpark.CarParkDto;
import com.stripe.Stripe;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@NoArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping()
    public ResponseEntity<String> addToken(@RequestParam String stripeToken, @RequestParam String amount) {
        //String response = "xd";
        String response = paymentService.addPayment(stripeToken, amount);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}