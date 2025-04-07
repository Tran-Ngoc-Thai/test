package test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import test.test.entity.Users;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

    Users findByPhoneOrEmail(String phone, String email);

    List<Users> findByRoleTrue(); // Giả sử role = true là active

    Optional<Users> findByEmail(String email);

    Users findByFullName(String fullName);

   
    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Optional<Users> findByEmailPass(@Param("email") String email);

    Optional<Users> findByResetToken(String resetToken);

    Optional<Users> findByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}