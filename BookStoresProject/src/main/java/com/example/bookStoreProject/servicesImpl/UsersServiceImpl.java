package com.example.bookStoreProject.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookStoreProject.entity.Users;
import com.example.bookStoreProject.repository.UsersRepository;
import com.example.bookStoreProject.services.UsersService;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUserById(Long userId) {
        return usersRepository.findById(userId).orElse(null);
    }

    @Override
    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public Users updateUser(Long userId, Users user) {
        if (usersRepository.existsById(userId)) {
            user.setUserID(userId);
            return usersRepository.save(user);
        }
        return null;
    }

	@Override
	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}

   
}

