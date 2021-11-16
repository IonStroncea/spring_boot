package ru.alishev.springcourse.repo;

import org.springframework.data.repository.CrudRepository;

import ru.alishev.springcourse.models.User;

public interface UserRepo extends  CrudRepository<User,Long> {
	User findByUsername(String username);
}
