package edu.pw.platon.student;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FinancialObligationRepository extends CrudRepository<FinancialObligation, Long> {
    List<FinancialObligation> findByStudent(Student student);
}
