package br.com.stoom.adapter.outbound.rest.geolocate.response.json;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LocationReponseJson {

	private BigDecimal lat;
	private BigDecimal lng;
	
}
