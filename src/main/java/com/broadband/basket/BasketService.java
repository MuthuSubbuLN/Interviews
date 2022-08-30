package com.sky.basket;

import java.math.BigDecimal;
import java.util.List;

public class BasketService {

    private SubscriptionService subscriptionService;

    private CustomerService customerService;

    BasketService(SubscriptioService subscriptioService, CustomerService customerService) {
        this.subscriptionService = subscriptioService;
        this.customerService = customerService;
    }
    public BigDecimal calculate(List<String> subscriptions, String customerId) throws BasketConditionNotMetException, SubscriptionNotFoundException {
        BigDecimal price = BigDecimal.ZERO;
        price = calculatePrice(subscriptions, price, customerId);

        return price;
    }


    private BigDecimal calculatePrice(List<String> subscriptions, BigDecimal price, String customerId) {
        for (String eachSubscription : subscriptions) {
            BigDecimal eachPrice = subscriptionService.getSubscriptionPrice(eachSubscription);

            if (eachPrice == null) {
                throw new SubscriptionNotFoundException();
            }
            if (eachSubscription.equalsIgnoreCase("BOOST")) {
                List<String> customerSubscriptions = customerService.getSubscriptionsForCustomer(customerId);
                checkCustomerHasEntertainmentSubscription(customerSubscriptions);
            }
            price = price.add(eachPrice);
        }
        return price;
    }

    private boolean checkCustomerHasEntertainmentSubscription(List<String> customerSubscriptions) {
        return customerSubscriptions.forEach(e -> e.equlasIgnoreCase("ENTERTAINMENT"));
    }
}
