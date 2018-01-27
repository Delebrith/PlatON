package edu.pw.platon.office;

import edu.pw.platon.user.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface OfficeEmployeeRepository extends CrudRepository<OfficeEmployee, String> {
    List<OfficeEmployee> findAllByRoles(Collection<Role> roles);
}
