package br.com.stoom.adapter.outbound.nosql.address.mapper;

import org.mapstruct.Mapper;

import br.com.stoom.adapter.outbound.nosql.address.model.AddressModel;
import br.com.stoom.core.dto.AddressCoreDTO;

@Mapper(componentModel = "spring")
public interface OutboundMapper {

	AddressModel from(AddressCoreDTO coreDTO);
	AddressCoreDTO from(AddressModel adapterDTO);
}
