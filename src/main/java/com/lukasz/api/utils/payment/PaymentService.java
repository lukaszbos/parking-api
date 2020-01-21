package com.lukasz.api.utils.payment;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @SneakyThrows
    public String addPayment(String stripeToken, String stripeAmount) {
        // Set your secret key: remember to change this to your live secret key in production
        // See your keys here: https://dashboard.stripe.com/account/apikeys
        Stripe.apiKey = "sk_test_tGwcDlYQEG0HQ4DcBGcSl2rQ00CdPViIfu";

        // Token is created using Stripe Checkout or Elements!
        // Get the payment token ID submitted by the form:
        //System.out.println(request);
        //String token = request.getParameter("stripeToken");
        int amount = Integer.parseInt(stripeAmount) * 100;

        Map<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        params.put("currency", "pln");
        params.put("description", "Example charge");
        params.put("source", stripeToken);
        Charge charge = Charge.create(params);

        return stripeToken;
    }
}