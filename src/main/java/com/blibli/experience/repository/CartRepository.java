package com.blibli.experience.repository;

import com.blibli.experience.entity.document.Cart;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CartRepository extends ReactiveMongoRepository<Cart, UUID> {

  Mono<Cart> findFirstByUserId(UUID id);

}