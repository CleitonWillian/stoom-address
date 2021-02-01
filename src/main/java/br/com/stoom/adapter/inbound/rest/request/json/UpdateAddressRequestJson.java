package br.com.stoom.adapter.inbound.rest.request.json;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAddressRequestJson {

	@NotNull
	private String streetName;
	@NotNull
	private Integer number;
	private String complement;
	@NotNull
	private String neighbourhood;
	@NotNull
	private String city;
	@NotNull
	private String state;
	@NotNull
	private String country;
	@NotNull
	private String zipcode;
	private BigDecimal latitude;
	private BigDecimal longitude;

}
