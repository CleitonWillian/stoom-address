package br.com.stoom.adapter.outbound.rest.geolocate.request.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeolocateRequestParam {

	private String address;
	private String key;
	
}
