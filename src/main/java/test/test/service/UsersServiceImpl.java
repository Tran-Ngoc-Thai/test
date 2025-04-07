package test.test.service;

import java.sql.Date;

import java.util.List;


import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import test.test.entity.Users;
import test.test.repository.UsersRepo;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UsersRepo usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getById(Integer id) {
        return usersRepository.findById(id).orElseThrow();
    }
    // public Optional<Users> getUserById(Integer id) {
    //     return usersRepository.findById(id);
    // }

    public Users createUser(Users user) {
        return usersRepository.save(user);
    }

    public Users updateUser(Integer id, Users updatedUser) {
        return usersRepository.findById(id).map(user -> {
            user.setFullName(updatedUser.getFullName());
            user.setPassword(updatedUser.getPassword());
    
            Date dateOfBirth = updatedUser.getDateOfBirth(); // Giả sử đây là kiểu java.util.Date
    
            if (dateOfBirth != null) {
                // Chuyển đổi từ java.util.Date sang java.sql.Date
                java.sql.Date sqlDate = new java.sql.Date(dateOfBirth.getTime());
                user.setDateOfBirth(sqlDate); // Gọi setter tương ứng
            }
    
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());
            user.setGender(updatedUser.getGender());
            user.setPosition(updatedUser.getPosition());
            user.setRole(updatedUser.getRole());
            return usersRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Integer id) {
        usersRepository.deleteById(id);
    }
}
