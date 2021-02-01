package br.com.stoom.core.bo;

import java.util.Optional;

import br.com.stoom.core.dto.AddressCoreDTO;
import br.com.stoom.core.dto.GeolocateDTO;
import br.com.stoom.core.errors.NotFoundRegisterToChangerValuesException;

public class AddressBO {

	private static final AddressBO INSTANCE = new AddressBO();
	
	private AddressBO() {
		
	}
	
	public static AddressBO getInstance() {
		return INSTANCE;
	}
	
	public boolean notContainAnyGeolocate(AddressCoreDTO addressCoreDTO) {
		return addressCoreDTO.getLatitude() == null || addressCoreDTO.getLongitude() == null;
	}
	
	public void addGeolocateInAddress(AddressCoreDTO addressCoreDTO, Optional<GeolocateDTO> optGeolocate) {
		
		optGeolocate.ifPresent( geolocate -> {
		
			if (addressCoreDTO.getLatitude() == null) {
				addressCoreDTO.setLatitude(geolocate.getLatitude());
			}

			if (addressCoreDTO.getLongitude() == null) {
				addressCoreDTO.setLongitude(geolocate.getLongitude());
			}
		});
	}
	
	public void canChangeThisAddressValidation(Optional<AddressCoreDTO> optAddressCoreDTO) throws NotFoundRegisterToChangerValuesException {
		if(optAddressCoreDTO.isEmpty()) {
			throw new NotFoundRegisterToChangerValuesException();
		}
	}
}
