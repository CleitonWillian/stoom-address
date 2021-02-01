package br.com.stoom.adapter.outbound.nosql.address.service;

import org.springframework.stereotype.Service;

import br.com.stoom.adapter.outbound.nosql.address.repository.AddressModelRepository;
import br.com.stoom.core.port.outbound.AddressDeletePortOubound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AddressDeleteOutboundService implements AddressDeletePortOubound{

	private AddressModelRepository addressModelRepository;
	
	@Override
	public void execute(String addressId) {
		
		log.info("Trying delete address with id {}", addressId);
		
		addressModelRepository.deleteById(addressId);
		
		log.info("deleted address with id {}", addressId);
		
	}

}
