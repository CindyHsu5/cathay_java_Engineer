package com.cathay.coindesk.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cathay.coindesk.model.Coin;

@Repository
public interface CoinRepository extends CrudRepository<Coin, String>{

}
