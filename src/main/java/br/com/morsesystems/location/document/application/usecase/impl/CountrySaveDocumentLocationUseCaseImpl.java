package br.com.morsesystems.location.document.application.usecase.impl;

import br.com.morsesystems.location.document.application.port.CountrySavePort;
import br.com.morsesystems.location.document.application.usecase.CountrySaveDocumentLocationUseCase;
import br.com.morsesystems.location.document.domain.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Component
@Transactional
@Slf4j
public class CountrySaveDocumentLocationUseCaseImpl implements CountrySaveDocumentLocationUseCase {

    private final CountrySavePort countrySavePort;

    @Override
    public CountrySaveMongoLocationCommand saveMongoLocation(CountrySaveMongoLocationCommand command) {
        log.info("The use case CountrySaveDocumentLocationUseCaseImpl was started.");

        Country country = countrySavePort.save(command.getCountry());

        log.info("The use case CountrySaveDocumentLocationUseCaseImpl was finished.");

        return CountrySaveMongoLocationCommand
                .builder()
                .country(country)
                .build();
    }

}