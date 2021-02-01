package br.com.stoom.adapter.outbound.nosql.address.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.stoom.adapter.outbound.nosql.address.mapper.OutboundMapper;
import br.com.stoom.adapter.outbound.nosql.address.repository.AddressModelRepository;
import br.com.stoom.core.dto.AddressCoreDTO;
import br.com.stoom.core.port.outbound.AddressReadByIdPortOutbound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AddressReadByIdOutboundService implements AddressReadByIdPortOutbound {

	private AddressModelRepository addressModelRepository;

	private OutboundMapper mapper;

	@Override
	public Optional<AddressCoreDTO> execute(String addressId) {
	log.info("Trying read address with id {} ",addressId);
		
		var optAddressFinded = addressModelRepository.findById(addressId);
	
		var mapperedAddressFinded = optAddressFinded.map(mapper::from);
		
		log.info("sucess in read address with id {}", addressId);
		
		return mapperedAddressFinded;
	}

}
