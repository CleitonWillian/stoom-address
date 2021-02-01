package br.com.stoom.core.port.outbound;

import java.util.Optional;

import br.com.stoom.core.dto.GeolocateDTO;

public interface GeolocateByAddressPortOubound {

	Optional<GeolocateDTO> execute(String address,Integer number);
	
}
