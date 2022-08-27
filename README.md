## Interview task: Basket Service

Our team is implementing a Basket Service to calculate the cost of subscriptions that the 
customer wishes to buy.

Your task is to implement the BasketService interface.

Subscription prices are retrieved via the SubscriptionService interface. You are not expected
to implement this, consider it a third party.

Junit must be used to unit test BasketService against the provided Acceptance Criteria. Third party services
should be mocked.

We have the following features that we want to implement. Please complete each feature in it's own commit.

When you are finished, make sure you provide a git-bundle, so we can see your local history (see docs here).


### Part 1 Implement Basket Service:
Multiple Subscription exist:

- Entertainment £9.99 , Sports £19.99 , Kids £6.99
- Acceptance Criteria: Scenario: Successful basket calculation of a single subscription
- Given the customer wants to purchase an Entertainment subscription When the basket is calculated
- Then a scuccessful response us rturned with £9.99 as the charge

input: ENT SPORTS KIDS
outpt: 9.99 19.99 6.99

Scenbario:
Unknown subscription should return SubscrNOTFoundEXecption 
Given the customer wants to purchase a MOVIES subscription And the SubscriptionService does not return a price(returns null)
When the basket is calculated Then a SubscriptionNotFound exception is thrown


Scenario:
Successful basket calculation of multiple subscriptions
Given the customer wants to purchase Entertainment and Sports subscriptions.
When the basket is calculated 
Then a successful respobse is returned with £29.98 as the charge



### Part 2 - Enhance Basket Service to use Customer Service

we would now like you to enhance the "# Interviews" 
