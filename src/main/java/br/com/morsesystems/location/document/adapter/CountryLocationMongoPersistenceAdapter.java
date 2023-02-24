package br.com.morsesystems.location.document.adapter;

import br.com.morsesystems.location.document.application.port.CountrySavePort;
import br.com.morsesystems.location.document.application.repository.CountryMongoRepository;
import br.com.morsesystems.location.document.application.repository.entity.CountryEntity;
import br.com.morsesystems.location.document.domain.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CountryLocationMongoPersistenceAdapter implements CountrySavePort {

    private final CountryMongoRepository countryMongoRepository;

    @Override
    public Country save(Country country) {

        log.info(String.format("Country with name %s and DDI %s will be saved.",
                country.getCountryName(),
                country.getTelephoneCodArea()));

        CountryEntity value = countryMongoRepository.save(CountryEntity
                .builder()
                .id(country.getId())
                .countryName(country.getCountryName())
                .telephoneCodArea(country.getTelephoneCodArea())
                .build());

        log.info(String.format("Country is saved with name %s and DDI %s, the ID is %s.",
                                value.getCountryName(),
                value.getTelephoneCodArea(), value.getId()));

        return Country.builder()
                .id(value.getId())
                .countryName(value.getCountryName())
                .telephoneCodArea(value.getTelephoneCodArea())
                .build();
    }

}