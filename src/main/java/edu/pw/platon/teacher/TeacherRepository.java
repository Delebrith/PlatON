package edu.pw.platon.teacher;

import edu.pw.platon.user.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface TeacherRepository extends CrudRepository<Teacher, String> {
    Teacher findByUsername(String username);
    List<Teacher> findAllByRoles(Collection<Role> roles);
}
