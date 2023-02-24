package br.com.morsesystems.location.document.application.usecase;

import br.com.morsesystems.location.document.domain.Country;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface CountrySaveDocumentLocationUseCase {

    CountrySaveMongoLocationCommand saveMongoLocation(CountrySaveMongoLocationCommand command);

    @Builder
    @RequiredArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    class CountrySaveMongoLocationCommand {
        private final Country country;
    }

}
