package br.com.stoom.adapter.outbound.nosql.address.bo;

import static java.util.Optional.ofNullable;

import br.com.stoom.core.dto.AddressCoreDTO;

public class AddressModelBO {

	private static final AddressModelBO INSTANCE = new AddressModelBO();
	
	private AddressModelBO() {
		
	}
	
	public static AddressModelBO getInstance() {
		return INSTANCE;
	}

	public void patch(AddressCoreDTO addressFindedToPatch, AddressCoreDTO patchAddressCoreDTO) {
		
		ofNullable(patchAddressCoreDTO.getCity()).ifPresent(addressFindedToPatch::setCity);
		ofNullable(patchAddressCoreDTO.getComplement()).ifPresent(addressFindedToPatch::setComplement);
		ofNullable(patchAddressCoreDTO.getCountry()).ifPresent(addressFindedToPatch::setCountry);
		ofNullable(patchAddressCoreDTO.getLatitude()).ifPresent(addressFindedToPatch::setLatitude);
		ofNullable(patchAddressCoreDTO.getLongitude()).ifPresent(addressFindedToPatch::setLongitude);
		ofNullable(patchAddressCoreDTO.getNeighbourhood()).ifPresent(addressFindedToPatch::setNeighbourhood);
		ofNullable(patchAddressCoreDTO.getNumber()).ifPresent(addressFindedToPatch::setNumber);
		ofNullable(patchAddressCoreDTO.getState()).ifPresent(addressFindedToPatch::setState);
		ofNullable(patchAddressCoreDTO.getStreetName()).ifPresent(addressFindedToPatch::setStreetName);
		ofNullable(patchAddressCoreDTO.getZipcode()).ifPresent(addressFindedToPatch::setZipcode);
		
	}
	
}
