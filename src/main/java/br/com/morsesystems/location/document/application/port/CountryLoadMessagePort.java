package br.com.morsesystems.location.document.application.port;

import br.com.morsesystems.location.document.api.CountryLocationEvent;

import java.util.function.Consumer;

public interface CountryLoadMessagePort {

    Consumer<CountryLocationEvent> consumer();

}
