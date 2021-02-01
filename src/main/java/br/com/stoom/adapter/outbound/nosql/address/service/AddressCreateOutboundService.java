package br.com.stoom.adapter.outbound.nosql.address.service;

import org.springframework.stereotype.Service;

import br.com.stoom.adapter.outbound.nosql.address.mapper.OutboundMapper;
import br.com.stoom.adapter.outbound.nosql.address.repository.AddressModelRepository;
import br.com.stoom.core.dto.AddressCoreDTO;
import br.com.stoom.core.port.outbound.AddressCreatePortOutbound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AddressCreateOutboundService implements AddressCreatePortOutbound{

	private AddressModelRepository addressModelRepository;
	
	private OutboundMapper mapper;

	@Override
	public String execute(AddressCoreDTO addressCoreDTO) {
		
		log.info("Trying save new address");
		
		var addressModelToSave = mapper.from(addressCoreDTO);
		
		var savedAddressModel = addressModelRepository.save(addressModelToSave);
		
		log.info("new address saved with sucess");
		
		return savedAddressModel.getId();
	}
	
	
}
