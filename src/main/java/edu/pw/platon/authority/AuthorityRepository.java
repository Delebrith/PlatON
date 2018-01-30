package edu.pw.platon.authority;

import edu.pw.platon.user.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority, String> {
    List<Authority> findAllByRoles(Collection<Role> roles);
}
