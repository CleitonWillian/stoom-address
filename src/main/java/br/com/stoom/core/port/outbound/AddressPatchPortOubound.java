package br.com.stoom.core.port.outbound;

import br.com.stoom.core.dto.AddressCoreDTO;

public interface AddressPatchPortOubound {

	public void execute(AddressCoreDTO addressFindedToPatch,AddressCoreDTO patchAddressCoreDTO);
	
}
