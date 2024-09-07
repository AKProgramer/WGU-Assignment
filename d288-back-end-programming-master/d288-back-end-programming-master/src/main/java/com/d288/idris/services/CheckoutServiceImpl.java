package com.d288.idris.services;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import com.d288.idris.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.d288.idris.dao.CartItemRepository;
import com.d288.idris.dao.CartRepository;
import com.d288.idris.dao.CustomerRepository;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Autowired
    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository, CartItemRepository cartItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        // Retrieve the customer from the purchase service
        
        Customer customer = purchase.getCustomer();
        // Check if this is an existing customer by ID
        Long customerId = customer.getId();
        Optional<Customer> existingCustomerOpt = customerRepository.findById(customerId);
        if (existingCustomerOpt.isPresent()) {
            customer = existingCustomerOpt.get();
        }

        // Retrieve the cart from the DTO
        Cart cart = purchase.getCart();

        cart.printCart();
        cart.setStatus(StatusType.ordered);
        Set<CartItem> cartItems = purchase.getCartItems();
        if (cartItems != null) {
            for (CartItem cartItem : cartItems) {
                cart.addCartItem(cartItem);
            }
        }
        else{
            System.out.println("Cart items are null");
        }
        // Generate a unique order tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        cart.setOrderTrackingNumber(orderTrackingNumber);
        // Add the cart to the customer's carts
        Set<Cart> carts = customer.getCarts();
        if(carts == null)
        {
            System.out.println("Customer Cart is empty. Cannot place order.");
            return new PurchaseResponse("Error: Customer Cart is empty. Cannot place order.");
        }

        carts.add(cart);
        customer.setCarts(carts);

        // Save the customer and cascade the changes (cart and cart items will be saved)
        customerRepository.save(customer);
        

        // Return a response with the order tracking number
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        // Generate a random UUID number (UUID version-4)
        return UUID.randomUUID().toString();
    }
}
