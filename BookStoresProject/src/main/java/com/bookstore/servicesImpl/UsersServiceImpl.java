package com.bookstore.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.bookstore.entity.Users;
import com.bookstore.jwt.JwtUtil;
import com.bookstore.jwt.MyUserDetailsService;
import com.bookstore.repository.UsersRepository;
import com.bookstore.services.UsersService;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;
    
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    
    // Retrieve a list of all users
    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Retrieve a user by their ID
    @Override
    public Users getUserById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    // Create a new user
    @Override
    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    // Update an existing user
    @Override
    public Users updateUser(Long userId, Users user) {
        if (usersRepository.existsById(userId)) {
            user.setUserID(userId);
            return usersRepository.save(user);
        }
        return null;
    }

    // Delete a user by their ID (currently empty)
    @Override
    public void deleteUser(Long userId) {
        // Implementation for deleting a user can be added here
    }

    // Method to handle user login
    @Override
    public ResponseEntity<String> logIn(Map<String, String> map) {
        try {
            Users user = usersRepository.getUserByUserName(map.get("username"));
            if (Objects.isNull(user)) {
                String messageBuild = "{" + "\n message: user not found" + "\n}";
                return new ResponseEntity<String>(messageBuild, HttpStatus.BAD_REQUEST);
            } else {
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(map.get("username"), map.get("password")));
                if (authentication.isAuthenticated()) {
                    if (myUserDetailsService.getUserDetails().getUsername() != null) {
                        return new ResponseEntity<String>(jwtUtil.generateToken(myUserDetailsService.getUserDetails().getUsername(), myUserDetailsService.getUserDetails().getRole()), HttpStatus.OK);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return new ResponseEntity<String>("SOMETHING WENT WRONG", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Method to handle user registration
    @Override
    public ResponseEntity<String> signUp(Map<String, String> map) {
        try {
            Users user = usersRepository.getUserByUserName(map.get("username"));
            if (Objects.isNull(user)) {
                usersRepository.save(configUser(map));
                return new ResponseEntity<String>("User Added Successfully!!!", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("Username Not Available!!!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Something Went Wrong!!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Helper method to configure user information
    private Users configUser(Map<String, String> map) {
        try {
            Users user = new Users();
            user.setName(map.get("name"));
            user.setUsername(map.get("username"));
            user.setPasscode(map.get("password"));
            user.setEmail(map.get("email"));
            user.setPhoneNo(Long.parseLong(map.get("phoneNo")));
            user.setAddress(map.get("address"));
            user.setRole(map.get("role"));
            user.setStatus("Active");
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}




























//package com.bookStoreProject.servicesImpl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Service;
//
//import com.bookStoreProject.entity.Users;
//import com.bookStoreProject.jwt.JwtUtil;
//import com.bookStoreProject.jwt.MyUserDetailsService;
//import com.bookStoreProject.repository.UsersRepository;
//import com.bookStoreProject.services.UsersService;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//@Service
//public class UsersServiceImpl implements UsersService {
//    private final UsersRepository usersRepository;
//    
//    @Autowired
//    private MyUserDetailsService myUserDetailsService;
//    
//    @Autowired
//    private JwtUtil jwtUtil;
//    
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    public UsersServiceImpl(UsersRepository usersRepository) {
//        this.usersRepository = usersRepository;
//    }
//    
//    
//
//    @Override
//    public List<Users> getAllUsers() {
//        return usersRepository.findAll();
//    }
//
//    @Override
//    public Users getUserById(Long userId) {
//        return usersRepository.findById(userId).orElse(null);
//    }
//
//    @Override
//    public Users createUser(Users user) {
//        return usersRepository.save(user);
//    }
//
//    @Override
//    public Users updateUser(Long userId, Users user) {
//        if (usersRepository.existsById(userId)) {
//            user.setUserID(userId);
//            return usersRepository.save(user);
//        }
//        return null;
//    }
//
//	@Override
//	public void deleteUser(Long userId) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	@Override
//	public ResponseEntity<String> logIn(Map<String, String> map) {
//		
//		try {
//			Users user = usersRepository.getUserByUserName(map.get("username"));
//			if (Objects.isNull(user)) {
//				String messageBuild = "{"+ "\n message: user not found"	+ "\n}"; 
//				return new ResponseEntity<String>(messageBuild,HttpStatus.BAD_REQUEST); 
//			} else {
//				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(map.get("username"), map.get("password")));
//				if (authentication.isAuthenticated()) {
//					if (myUserDetailsService.getUserDetails().getUsername() != null) {
//						return new ResponseEntity<String>(jwtUtil.generateToken(myUserDetailsService.getUserDetails().getUsername(),myUserDetailsService.getUserDetails().getRole()),HttpStatus.OK);
//					}
//				}
//			}
//		}
//		 catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return new ResponseEntity<String>("SOMETHING WENT WRONG",HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//
//
//
//	@Override
//	public ResponseEntity<String> signUp(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//		Users user  =  usersRepository.getUserByUserName(map.get("username"));
//		
//		if(Objects.isNull(user)) {
//			usersRepository.save(configUser(map));
//			return new ResponseEntity<String>("User Added Successfully!!!",HttpStatus.OK);
//		}else {
//			return new ResponseEntity<String>("Username Not Available!!!",HttpStatus.BAD_REQUEST);
//		}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>("Something Went Wrong!!!",HttpStatus.INTERNAL_SERVER_ERROR);
//		
//	}
//
//
//
//	private Users configUser(Map<String, String> map) {
//		// TODO Auto-generated method stub
//		try {
//			
//			Users user = new Users();
//			user.setName(map.get("name"));
//			user.setUsername(map.get("username"));
//			user.setPasscode(map.get("password"));
//			user.setEmail(map.get("email"));
//			user.setPhoneNo(Long.parseLong(map.get("phoneNo")));
//			user.setAddress(map.get("address"));
//			user.setRole(map.get("role"));
//			user.setStatus("Active");
//			System.out.println(user);
//			return user;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//   
//}
//
//
////key_id
////rzp_live_q6yVbq1url18PH,
////
////
////
////key_secret
////eY0bzJPfeWB4wKvRSUbVtFmr
