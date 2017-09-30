package ua.rd.repository;

import ua.rd.domain.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
        Collection<User> getAllUsers();

        Optional<User> getUserById(Long id);

        Optional<User> getUserByName(String name);

        void save(User user);

        void update(User user);

        void delete(User user);

        User createUser(String name);
}
