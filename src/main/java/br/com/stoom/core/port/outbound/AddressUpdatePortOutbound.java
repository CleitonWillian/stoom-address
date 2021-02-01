package br.com.stoom.core.port.outbound;

import br.com.stoom.core.dto.AddressCoreDTO;

public interface AddressUpdatePortOutbound {

	public void execute(AddressCoreDTO addressCoreDTO);
	
}
