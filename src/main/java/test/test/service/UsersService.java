package test.test.service;

import java.util.List;

import test.test.entity.Users;

public interface UsersService {
    List<Users> getAllUsers();
    Users getById(Integer id);
    Users createUser(Users user);
    Users updateUser(Integer id, Users user);
    void deleteUser(Integer id);
}
