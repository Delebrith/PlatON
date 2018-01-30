package edu.pw.platon.studies;

import org.springframework.data.repository.CrudRepository;

public interface ClassTypeRepository extends CrudRepository<ClassType, Long> {
    ClassType findByName(String name);
}
