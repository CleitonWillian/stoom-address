package br.com.stoom.core.bo;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import br.com.stoom.core.dto.AddressCoreDTO;
import br.com.stoom.core.dto.GeolocateDTO;

class AddressBOTest {

	private final AddressBO addressBO = AddressBO.getInstance();
	
	@Test
	void canAddLngAndLatWhenGeolocateExistButLatAmdLngIsNullInAddressCoreDTO() {
		
		AddressCoreDTO addressCoreDTO = new AddressCoreDTO();
		BigDecimal latitude =  BigDecimal.valueOf(30.5);
		BigDecimal longitude =  BigDecimal.valueOf(20.3);
		Optional<GeolocateDTO> optGeolocate = Optional.of(GeolocateDTO.builder()
				.latitude(latitude)
				.longitude(longitude)
				.build());
				
		addressBO.addGeolocateInAddress(addressCoreDTO , optGeolocate);
		assertThat(addressCoreDTO.getLatitude()).isEqualByComparingTo(latitude);
		assertThat(addressCoreDTO.getLongitude()).isEqualByComparingTo(longitude);
	}
	
	
	@Test
	void notAddLngAndLatWhenGeolocateNotExistButLatAmdLngIsNullInAddressCoreDTO() {
		
		AddressCoreDTO addressCoreDTO = new AddressCoreDTO();
		Optional<GeolocateDTO> optGeolocate = Optional.empty();
				
		addressBO.addGeolocateInAddress(addressCoreDTO , optGeolocate);
		assertThat(addressCoreDTO.getLatitude()).isNull();
		assertThat(addressCoreDTO.getLongitude()).isNull();
	}
	
	@Test
	void notAddLngAndLatWhenGeolocateExistButLatAmdLngIsNotNullInAddressCoreDTO() {
		
		AddressCoreDTO addressCoreDTO = new AddressCoreDTO();
		
		BigDecimal latitude =  BigDecimal.valueOf(30.5);
		BigDecimal longitude =  BigDecimal.valueOf(20.33);
		addressCoreDTO.setLatitude( latitude.add(BigDecimal.TEN));
		addressCoreDTO.setLongitude( longitude.add(BigDecimal.TEN));
		
		Optional<GeolocateDTO> optGeolocate = Optional.of(GeolocateDTO.builder()
				.latitude(latitude)
				.longitude(longitude)
				.build());
				
		addressBO.addGeolocateInAddress(addressCoreDTO , optGeolocate);
		assertThat(addressCoreDTO.getLatitude()).isEqualByComparingTo(latitude.add(BigDecimal.TEN));
		assertThat(addressCoreDTO.getLongitude()).isEqualByComparingTo(longitude.add(BigDecimal.TEN));
	}
}
