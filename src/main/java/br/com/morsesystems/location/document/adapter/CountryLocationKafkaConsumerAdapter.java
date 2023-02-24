package br.com.morsesystems.location.document.adapter;

import br.com.morsesystems.location.document.api.CountryLocationEvent;
import br.com.morsesystems.location.document.application.port.CountryLoadMessagePort;
import br.com.morsesystems.location.document.application.usecase.CountrySaveDocumentLocationUseCase;
import br.com.morsesystems.location.document.domain.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
@RequiredArgsConstructor
public class CountryLocationKafkaConsumerAdapter implements CountryLoadMessagePort {

    private final CountrySaveDocumentLocationUseCase countrySaveDocumentLocationUseCase;

    @Bean
    @Override
    public Consumer<CountryLocationEvent> consumer() {
        return countryLocationEvent -> {

            if(countryLocationEvent.getIdempotencyKey() != null) {

                log.info(String.format("Received a CountryLocationEvent with idempotency-key %s and Country with name %s and DDI %s.",
                        countryLocationEvent.getIdempotencyKey(), countryLocationEvent.getCountryName(), countryLocationEvent.getTelephoneCodArea()));

                CountrySaveDocumentLocationUseCase.CountrySaveMongoLocationCommand result =
                        this.countrySaveDocumentLocationUseCase.saveMongoLocation(CountrySaveDocumentLocationUseCase.CountrySaveMongoLocationCommand.builder()
                                .country(Country.builder()
                                        .countryName(countryLocationEvent.getCountryName())
                                        .telephoneCodArea(countryLocationEvent.getTelephoneCodArea())
                                        .build())
                                .build());

                log.info(String.format("CountryLocationEvent with idempotency-key %s was processed and Country with name %s and DDI %s was saved with ID is %s.",
                        countryLocationEvent.getIdempotencyKey(), result.getCountry().getCountryName(), result.getCountry().getTelephoneCodArea(),
                        result.getCountry().getId()));

            } else{
                log.info(String.format("Received a CountryLocationEvent and it will be ignored because it doesn't has idempotency-key. The CountryLocationEvent has id %s, and name %s and DDI %s",
                        countryLocationEvent.getId(), countryLocationEvent.getCountryName(), countryLocationEvent.getTelephoneCodArea()));
            }

        };
    }

}