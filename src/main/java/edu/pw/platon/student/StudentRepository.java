package edu.pw.platon.student;

import edu.pw.platon.user.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface StudentRepository extends CrudRepository<Student, String> {
    Student findTopByOrderByStudentBookNoDesc();
    List<Student> findAllByRoles(Collection<Role> roles);
}
