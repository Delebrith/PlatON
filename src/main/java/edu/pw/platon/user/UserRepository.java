package edu.pw.platon.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UserRepository extends CrudRepository<User, String>{
    Integer countAllByRoles(Collection<Role> roles);
}
