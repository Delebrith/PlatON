package edu.pw.platon.studies;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RealisationRepository extends CrudRepository<Realisation, Long> {
    List<Realisation> findBySubjectAndSemesterCode(Subject subject, String semesterCode);
}
