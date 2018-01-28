package edu.pw.platon.studies;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RequirementRepository extends CrudRepository<Requirement, Long> {
    List<Requirement> findByCourse(DegreeCourse degreeCourse);
}
