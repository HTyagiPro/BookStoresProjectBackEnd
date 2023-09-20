package com.bookStoreProject.servicesImpl;

import com.bookStoreProject.entity.Book;
import com.bookStoreProject.entity.CartItem;
import com.bookStoreProject.entity.Customer;
import com.bookStoreProject.entity.Users;
import com.bookStoreProject.jwt.MyUserDetailsService;
import com.bookStoreProject.repository.BookRepository;
import com.bookStoreProject.repository.CartItemRepository;
import com.bookStoreProject.repository.CustomerRepository;
import com.bookStoreProject.services.CartItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    @Autowired
    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
        this.cartItemRepository = cartItemRepository;
    }

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookRepository bookRepository;

    // Get a list of all cart items
    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    // Get a cart item by its ID
    @Override
    public Optional<CartItem> getCartItemById(Long id) {
        return cartItemRepository.findById(id);
    }

    // Save a cart item
    @Override
    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    // Delete a cart item by its ID
    @Override
    public void deleteCartItem(Long id) {
        cartItemRepository.deleteById(id);
    }

    // View the user's cart
    @Override
    public ResponseEntity<List<CartItem>> viewUserCart() {
        try {
            Users user = myUserDetailsService.getUserDetails();
            Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
            if (customer != null)
                return new ResponseEntity<List<CartItem>>(cartItemRepository.getCartByCustomerID(customer.getCustomerID()), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }
        List<CartItem> cart = null;
        return new ResponseEntity<List<CartItem>>(cart, HttpStatus.OK);
    }

    // Add an item to the cart
    @Override
    public ResponseEntity<String> addToCart(Map<String, String> map) {
        try {
            Users user = myUserDetailsService.getUserDetails();
            Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
            Book book = bookRepository.findById(Long.parseLong(map.get("bookID"))).get();

            CartItem cartItem = cartItemRepository.getCartByBookIdAndCustomerID(book.getBookID(), customer.getCustomerID());

            if (Objects.isNull(cartItem)) {
                cartItem = new CartItem();
                cartItem.setBook(bookRepository.findById(Long.parseLong(map.get("bookID"))).get());
                cartItem.setCustomer(customer);
                cartItem.setQuantity(1);
                cartItem.setRecordCreatedOn(new Timestamp(new Date().getTime()));
                cartItemRepository.save(cartItem);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemRepository.save(cartItem);
            }
            return new ResponseEntity<String>("Item Added To Cart Successfully!!!", HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<String>("Something Went Wrong, Please Try Again After Sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Delete an item from the cart
    @Override
    public ResponseEntity<String> deleteFromCart(Map<String, String> map) {
        try {
            Users user = myUserDetailsService.getUserDetails();
            Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
            CartItem cartItem = cartItemRepository.getCartByBookIdAndCustomerID(Long.parseLong(map.get("bookID")), customer.getCustomerID());

            if (Objects.isNull(cartItem)) {
                return new ResponseEntity<String>("Item not Present!!", HttpStatus.NOT_FOUND);
            } else {
                if (cartItem.getQuantity() - 1 == 0) {
                    cartItemRepository.deleteById(cartItem.getId());
                    return new ResponseEntity<String>("Item removed from cart!!!", HttpStatus.OK);
                } else {
                    cartItem.setQuantity(cartItem.getQuantity() - 1);
                    cartItemRepository.save(cartItem);
                    return new ResponseEntity<String>("Item quantity updated!!!", HttpStatus.OK);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}













//package com.bookStoreProject.servicesImpl;
//
//
//import com.bookStoreProject.entity.Book;
//import com.bookStoreProject.entity.CartItem;
//import com.bookStoreProject.entity.Customer;
//import com.bookStoreProject.entity.Users;
//import com.bookStoreProject.jwt.MyUserDetailsService;
//import com.bookStoreProject.repository.BookRepository;
//import com.bookStoreProject.repository.CartItemRepository;
//import com.bookStoreProject.repository.CustomerRepository;
//import com.bookStoreProject.services.CartItemService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class CartItemServiceImpl implements CartItemService {
//
//    
//
//	private final CartItemRepository cartItemRepository;
//
//    @Autowired
//    public CartItemServiceImpl(CartItemRepository cartItemRepository) {
//        this.cartItemRepository = cartItemRepository;
//    }
//    
//    @Autowired
//    MyUserDetailsService myUserDetailsService;
//    
//    @Autowired
//    CustomerRepository customerRepository; 
//    
//    @Autowired
//    BookRepository bookRepository;
//    
//
//    @Override
//    public List<CartItem> getAllCartItems() {
//        return cartItemRepository.findAll();
//    }
//
//    @Override
//    public Optional<CartItem> getCartItemById(Long id) {
//        return cartItemRepository.findById(id);
//    }
//
//    @Override
//    public CartItem saveCartItem(CartItem cartItem) {
//        return cartItemRepository.save(cartItem);
//    }
//
//    @Override
//    public void deleteCartItem(Long id) {
//        cartItemRepository.deleteById(id);
//    }
//    
//    @Override
//	public ResponseEntity<List<CartItem>> viewUserCart() {
//		try {
//			Users user = myUserDetailsService.getUserDetails();
//			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
//			if(customer != null)
//				return new ResponseEntity<List<CartItem>> (cartItemRepository.getCartByCustomerID(customer.getCustomerID()), HttpStatus.OK);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		List<CartItem> cart = null;
//		return new ResponseEntity<List<CartItem>> (cart, HttpStatus.OK);
//	}
//
//	@Override
//	public ResponseEntity<String> addToCart(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//			Users user = myUserDetailsService.getUserDetails();
//			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
//			Book book = bookRepository.findById(Long.parseLong(map.get("bookID"))).get();
//			
//			
//			CartItem cartItem = cartItemRepository.getCartByBookIdAndCustomerID(book.getBookID(), customer.getCustomerID());
//			
//			if(Objects.isNull(cartItem)) {
//				cartItem = new CartItem();
//				cartItem.setBook(bookRepository.findById(Long.parseLong(map.get("bookID"))).get());
//				cartItem.setCustomer(customer);
//				cartItem.setQuantity(1);
//				cartItem.setRecordCreatedOn(new Timestamp(new Date().getTime()));
//				cartItemRepository.save(cartItem);
//			}else {
//				
//				cartItem.setQuantity(cartItem.getQuantity() + 1);
//				cartItemRepository.save(cartItem);
//			}
//			return new ResponseEntity<String>("Item Added To Cart Successfully!!!", HttpStatus.OK);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		
//		return new ResponseEntity<String>("Something Went Wrong, Please Try Again After Sometime!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//	@Override
//	public ResponseEntity<String> deleteFromCart(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//			
//			Users user = myUserDetailsService.getUserDetails();
//			Customer customer = customerRepository.getCustomerByEmail(user.getEmail());
//			CartItem cartItem = cartItemRepository.getCartByBookIdAndCustomerID(Long.parseLong(map.get("bookID")), customer.getCustomerID());
//			
//			if (Objects.isNull(cartItem)) {
//				return new ResponseEntity<String>("Item not Present!!", HttpStatus.NOT_FOUND);
//			}else {
//				if (cartItem.getQuantity() - 1 == 0) {
//					cartItemRepository.deleteById(cartItem.getId());
//					return new ResponseEntity<String>("Item removed from cart!!!", HttpStatus.OK);
//				}else {
//					cartItem.setQuantity(cartItem.getQuantity() - 1);
//					cartItemRepository.save(cartItem);
//					return new ResponseEntity<String>("Item quantity updated!!!", HttpStatus.OK);
//				}
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}
//
//
//
//
//
//
//
