package br.com.stoom.core.dto;

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
public class GeolocateDTO {

	private BigDecimal latitude;
	private BigDecimal longitude;
	
	
}
