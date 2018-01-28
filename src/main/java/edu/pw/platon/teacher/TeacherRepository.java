package edu.pw.platon.teacher;

import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher, String> {
    Teacher findByUsername(String username);
}
