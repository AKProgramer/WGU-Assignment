package com.d288.idris.services;

import com.d288.idris.services.Purchase;
import com.d288.idris.services.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
    