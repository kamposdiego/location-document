package br.com.morsesystems.location.document.application.port;

import br.com.morsesystems.location.document.domain.Country;

public interface CountrySavePort {

    Country save(Country country);

}
