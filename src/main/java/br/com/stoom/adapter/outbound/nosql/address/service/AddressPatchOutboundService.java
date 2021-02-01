package br.com.stoom.adapter.outbound.nosql.address.service;

import org.springframework.stereotype.Service;

import br.com.stoom.adapter.outbound.nosql.address.bo.AddressModelBO;
import br.com.stoom.adapter.outbound.nosql.address.mapper.OutboundMapper;
import br.com.stoom.adapter.outbound.nosql.address.repository.AddressModelRepository;
import br.com.stoom.core.dto.AddressCoreDTO;
import br.com.stoom.core.port.outbound.AddressPatchPortOubound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AddressPatchOutboundService implements AddressPatchPortOubound {

	private AddressModelRepository addressModelRepository;
	
	private OutboundMapper mapper;

	private final AddressModelBO addressModelBO = AddressModelBO.getInstance();

	@Override
	public void execute(AddressCoreDTO addressFindedToPatch, AddressCoreDTO patchAddressCoreDTO) {

		log.info("Trying update patch address with id {}", patchAddressCoreDTO.getId());

		addressModelBO.patch(addressFindedToPatch, patchAddressCoreDTO);

		addressModelRepository.save(mapper.from(addressFindedToPatch));

		log.info("address updated patch with sucess ");

	}

}
