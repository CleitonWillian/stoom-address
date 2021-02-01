package br.com.stoom.core.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.stoom.core.bo.AddressBO;
import br.com.stoom.core.dto.AddressCoreDTO;
import br.com.stoom.core.dto.GeolocateDTO;
import br.com.stoom.core.errors.NotFoundRegisterToChangerValuesException;
import br.com.stoom.core.port.inbound.AddressCrudDomainPortInbound;
import br.com.stoom.core.port.outbound.AddressCreatePortOutbound;
import br.com.stoom.core.port.outbound.AddressDeletePortOubound;
import br.com.stoom.core.port.outbound.AddressPatchPortOubound;
import br.com.stoom.core.port.outbound.AddressReadAllPortOutbound;
import br.com.stoom.core.port.outbound.AddressReadByIdPortOutbound;
import br.com.stoom.core.port.outbound.AddressUpdatePortOutbound;
import br.com.stoom.core.port.outbound.GeolocateByAddressPortOubound;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class AddressCrudDomainService implements AddressCrudDomainPortInbound {

	private AddressCreatePortOutbound addressCreatePortOutbound;

	private AddressReadAllPortOutbound addressReadAllPortOutbound;

	private AddressReadByIdPortOutbound addressReadByIdPortOutbound;

	private AddressUpdatePortOutbound addressUpdatePortOutbound;

	private AddressPatchPortOubound addressPatchPortOubound;

	private AddressDeletePortOubound addressDeletePortOubound;

	private GeolocateByAddressPortOubound geolocateByAddressPortOubound;

	private final AddressBO createAddressBO = AddressBO.getInstance();

	@Override
	public String create(AddressCoreDTO addressCoreDTO) {

		log.info("start create process...");

		geolocateResolver(addressCoreDTO);

		var addressId = addressCreatePortOutbound.execute(addressCoreDTO);

		log.info("finish create process");

		return addressId;
	}

	@Override
	public Optional<AddressCoreDTO> read(String addressId) {

		log.info("start read by id process...");

		var optAddressCoreDTO = addressReadByIdPortOutbound.execute(addressId);

		log.info("finish read by id process");

		return optAddressCoreDTO;
	}

	@Override
	public Page<AddressCoreDTO> read(Pageable pageable) {

		log.info("start read all process...");

		var pageAddressCoreDTO = addressReadAllPortOutbound.execute(pageable);

		log.info("finish read all process");

		return pageAddressCoreDTO;
	}

	@Override
	public void update(AddressCoreDTO addressCoreDTO) throws NotFoundRegisterToChangerValuesException{

		log.info("start update process...");

		var canUpdateThisAddress = read(addressCoreDTO.getId());

		createAddressBO.canChangeThisAddressValidation(canUpdateThisAddress);

		geolocateResolver(addressCoreDTO);

		addressUpdatePortOutbound.execute(addressCoreDTO);

		log.info("finish update process");
	}

	@Override
	public void patch(AddressCoreDTO patchAddressCoreDTO) throws NotFoundRegisterToChangerValuesException {

		log.info("start patch process...");

		var canUpdateThisAddress = read(patchAddressCoreDTO.getId());

		createAddressBO.canChangeThisAddressValidation(canUpdateThisAddress);
		
		var addressFindedToPatch = canUpdateThisAddress.orElseThrow(NotFoundRegisterToChangerValuesException::new);

		addressPatchPortOubound.execute(addressFindedToPatch, patchAddressCoreDTO);

		log.info("finish patch process");

	}

	@Override
	public void delete(String addressId) throws NotFoundRegisterToChangerValuesException{

		log.info("start delete process...");
		
		var canUpdateThisAddress = read(addressId);

		createAddressBO.canChangeThisAddressValidation(canUpdateThisAddress);

		addressDeletePortOubound.execute(addressId);

		log.info("finish delete process");

	}

	private void geolocateResolver(AddressCoreDTO addressCoreDTO) {
		if (createAddressBO.notContainAnyGeolocate(addressCoreDTO)) {
			var geolocate = findGeolocate(addressCoreDTO);

			createAddressBO.addGeolocateInAddress(addressCoreDTO, geolocate);
		}
	}

	private Optional<GeolocateDTO> findGeolocate(AddressCoreDTO addressCoreDTO) {
		return geolocateByAddressPortOubound.execute(addressCoreDTO.getStreetName(), addressCoreDTO.getNumber());
	}

}
