package br.com.stoom.core.port.outbound;


import java.util.Optional;

import br.com.stoom.core.dto.AddressCoreDTO;

public interface AddressReadByIdPortOutbound {

	Optional<AddressCoreDTO> execute(String addressId);
	
}
