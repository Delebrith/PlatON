package edu.pw.platon.admin;

import edu.pw.platon.user.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface AdministratorRepository extends CrudRepository<Administrator, String> {
    List<Administrator> findAllByRoles(Collection<Role> roles);

}
