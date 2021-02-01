package br.com.stoom.adapter.outbound.nosql.address.service;

import org.springframework.stereotype.Service;

import br.com.stoom.adapter.outbound.nosql.address.mapper.OutboundMapper;
import br.com.stoom.adapter.outbound.nosql.address.repository.AddressModelRepository;
import br.com.stoom.core.dto.AddressCoreDTO;
import br.com.stoom.core.port.outbound.AddressUpdatePortOutbound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AddressUpdateOutboundService implements AddressUpdatePortOutbound{

	private AddressModelRepository addressModelRepository;

	private OutboundMapper mapper;
	
	@Override
	public void execute(AddressCoreDTO addressCoreDTO) {
	
		log.info("Trying update address with id {}", addressCoreDTO.getId());
		
		var addressModelToSave = mapper.from(addressCoreDTO);
		
		addressModelRepository.save(addressModelToSave);
		
		log.info("new address updated with sucess");
		
	}

}
