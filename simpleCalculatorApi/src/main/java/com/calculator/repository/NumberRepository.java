package com.calculator.repository;

//NumberRepository extends the CrudRepository interface.
// By extending CrudRepository, NumberRepository inherits several methods for working with Customer persistence,
// including methods for saving, deleting, and finding Customer entities. The CrudRepository interface must be linked
// to the type of domain object it handles and the type of the primary key associated with that domain object.
// As seen above, the CustomerRepository interface extends CrudRepository <Customer, Long>.
// As a result, CustomerRepository will manage Customer domain objects with a Long primary key value.


import com.calculator.entity.Number;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//CrudRepository  provides sophisticated CRUD functionality for the entity class that is being managed.
//JpaRepository interface is a JPA specific repository interface that combines the methods declared by the common
// repository interfaces behind a single interface and provides some JPA related method such as flushing the persistence
// context and delete record in a batch.
//JpaSpecificationExecutor<T>interface is not a “repository interface”. It declares the methods that are used to
// retrieve entities from the database by using Specification<T> objects that use the JPA criteria API.
public interface NumberRepository extends CrudRepository<Number, Long>, JpaRepository<Number, Long>, JpaSpecificationExecutor<Number> {
    List<Number> findByOperation(String operation);
}