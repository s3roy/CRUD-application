package com.crudApp.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.crudApp.demo.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
		
	
	public List<Account> findByDeletedOnIsNull();
//	Account findByIdAndDeletedOnIsNull(Long id);
	
//	@Query("Select acc from Account acc where acc.deletedOn is null")
//	public List<Account> findByDeletedOnIsNull();
	
	@Query("Select acc from Account acc where acc.id=?1 and acc.deletedOn is null")
	//public Account findByIdAndDeletedOnIsNull(Long id);
	
	public Optional<Account> findByIdAndDeletedOnIsNull(Long id);
}
