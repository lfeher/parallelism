package com.lfeher.parallelism.jpa.repository;

import com.lfeher.parallelism.jpa.model.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "data", path = "data")
public interface DataRepository extends JpaRepository<Data, Long> {

    List<Data> findByName(@Param("name") String name);
}
