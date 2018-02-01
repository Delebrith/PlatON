package edu.pw.platon.student;

import edu.pw.platon.studies.Realisation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(Student student);
    Enrollment findByStudentAndRealisation(Student student, Realisation realisation);
}
