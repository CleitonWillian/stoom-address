package br.com.stoom.adapter.outbound.rest.geolocate.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.stoom.adapter.outbound.rest.geolocate.client.GoogleClient;
import br.com.stoom.core.dto.GeolocateDTO;
import br.com.stoom.core.port.outbound.GeolocateByAddressPortOubound;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GeolocateByAddressOutboundService implements GeolocateByAddressPortOubound {

	private GoogleClient googleClient;

	private final String key;

	public GeolocateByAddressOutboundService(GoogleClient googleClient, @Value("${geolocate.key}")String key) {
		this.googleClient = googleClient;
		this.key = key;
	}

	@Override
	public Optional<GeolocateDTO> execute(String address, Integer number) {

		log.info("Tryng find geolocate by address...");
		try {
		
		if (StringUtils.isBlank(key)) {
			throw new IllegalArgumentException("Invalid key to request google api");
		}

		var responseFindGeolocate = googleClient
				.findGeolocate(
						buildAddressToRequest(address, number),
						key);

		if (responseFindGeolocate.getStatusCode().is2xxSuccessful()) {
			var bodyReponse = responseFindGeolocate.getBody();
			
			log.info("find geolocate by address success");
			return Optional
					.of(GeolocateDTO.builder()
							.latitude(bodyReponse.getResults().get(0).getGeometry().getLocation().getLat())
							.longitude(bodyReponse.getResults().get(0).getGeometry().getLocation().getLng())
							.build());
		}

		log.warn("find geolocate by address fail , http status is {}", responseFindGeolocate.getStatusCodeValue());
		return Optional.empty();
		}catch (Exception e) {
			log.error("fail in tryng geolocate of google api :{}", e.getMessage());
			return Optional.empty();
		}
	}

	private String buildAddressToRequest(String address, Integer number) {
		return address + " " + number;
	}

}
