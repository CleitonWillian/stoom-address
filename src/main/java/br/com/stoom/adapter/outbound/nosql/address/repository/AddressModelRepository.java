package br.com.stoom.adapter.outbound.nosql.address.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.stoom.adapter.outbound.nosql.address.model.AddressModel;

public interface AddressModelRepository extends MongoRepository<AddressModel, String>{

}
