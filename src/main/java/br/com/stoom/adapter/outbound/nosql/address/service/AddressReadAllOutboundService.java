package br.com.stoom.adapter.outbound.nosql.address.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.stoom.adapter.outbound.nosql.address.mapper.OutboundMapper;
import br.com.stoom.adapter.outbound.nosql.address.repository.AddressModelRepository;
import br.com.stoom.core.dto.AddressCoreDTO;
import br.com.stoom.core.port.outbound.AddressReadAllPortOutbound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AddressReadAllOutboundService implements AddressReadAllPortOutbound {

	private AddressModelRepository addressModelRepository;

	private OutboundMapper mapper;

	@Override
	public Page<AddressCoreDTO> execute(Pageable pageable) {
	
		log.info("Trying read address by pageable options");
		
		var allAddressFinded = addressModelRepository.findAll(pageable);
	
		var mapperedAllAddressFinded = allAddressFinded.map(mapper::from);
		
		log.info("sucess in read address by pageable options");
		
		return mapperedAllAddressFinded;
	}

}
