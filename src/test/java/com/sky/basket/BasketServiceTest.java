package com.broadband.basket;

import org.junit.*;

import org.mockito.*;

@RunWith(MockitoJunitRunner.class)
public class BasketServiceTest {

    @InjectMocks
    private BasketService basketService;

    @Mock
    private SubscriptionService subscriptionService;

    @Mock
    private CustomerService customerService;

    @Before
    public void setUp() {
        basketService = new BasketService(subscriptionService, customerService);
    }

    @Test
    public void testSubscriptionForEntertainment() {
        Mockito.when(subscriptionService.getSubscriptionPrice("ENTERTAINMENT")).thenReturn(BigDecimal.valueOf(9.99));
        BigDecimal actualValue = basketService.calculate(Arrays.asList("ENTERTAINMENT"));
        Assert.assertEquals(BigDecimal.valueOf(9.99), actualValue);
    }

    @Test(expected = SubscriptionNotFoundException.class)
    public void testSubscriptionForMovies() {
        Mockito.when(subscriptionService.getSubscriptionPrice("MOVIES")).thenReturn(null);
        basketService.calculate(Arrays.asList("MOVIES"));
    }

    @Test
    public void testMultipleSubscriptions() {
        Mockito.when(subscriptionService.getSubscriptionPrice("ENTERTAINMENT")).thenReturn(BigDecimal.valueOf(9.99));
        Mockito.when(subscriptionService.getSubscriptionPrice("SPORTS")).thenReturn(BigDecimal.valueOf(19.99));
        BigDecimal actualValue = basketService.calculate(Arrays.asList("ENTERTAINMENT"));
        Assert.assertEquals(BigDecimal.valueOf(29.98), actualValue);
    }

    @Test
    public void testCustomerServiceWithSubscriptions() {
        Mockito.when(customerService.getSubscriptionsorCustomer("1")).thenReturn(Arrays.asList("ENTERTAINMENT"));
        BigDecimal actualValue = basketService.calculate(Arrays.asList("ENTERTAINMENT"));
        Assert.assertEquals(BigDecimal.valueOf(1.99), actualValue);
    }
}