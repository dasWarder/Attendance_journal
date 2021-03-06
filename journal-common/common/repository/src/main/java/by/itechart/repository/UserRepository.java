package by.itechart.repository;

import by.itechart.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> getUserByUsername(String username);

    List<User> getUsersByRole_Authority(String roleName);

    void deleteUserByUsername(String username);

}
