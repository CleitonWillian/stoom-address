package br.com.stoom.core.port.outbound;

import br.com.stoom.core.dto.AddressCoreDTO;

public interface AddressCreatePortOutbound {

	public String execute(AddressCoreDTO addressCoreDTO);
	
}
