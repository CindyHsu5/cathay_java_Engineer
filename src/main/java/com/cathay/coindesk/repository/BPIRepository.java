package com.cathay.coindesk.repository;

import java.util.ArrayList;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cathay.coindesk.model.BPI;
import com.cathay.coindesk.model.BPIPk;

@Repository
public interface BPIRepository extends CrudRepository<BPI, BPIPk>{
	
	public ArrayList<BPI> findByCoin(String coin);
	
	@Modifying
	@Transactional
	public void deleteByCoin(String coin);

}
