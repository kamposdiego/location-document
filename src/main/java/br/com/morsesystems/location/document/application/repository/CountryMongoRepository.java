package br.com.morsesystems.location.document.application.repository;

import br.com.morsesystems.location.document.application.repository.entity.CountryEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountryMongoRepository extends MongoRepository<CountryEntity, Long> {

}
