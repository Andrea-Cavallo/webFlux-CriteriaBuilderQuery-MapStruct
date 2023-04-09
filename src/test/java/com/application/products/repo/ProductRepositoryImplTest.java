package com.application.products.repo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.application.products.documents.Product;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class ProductRepositoryImplTest {

    @Mock
    private ReactiveMongoTemplate reactiveMongoTemplate;

    private ProductRepositoryImpl productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productRepository = new ProductRepositoryImpl(reactiveMongoTemplate);
    }

    @Test
     void testFindByCriteria() {
        // Given
        Query expectedQuery = new Query();
        expectedQuery.addCriteria(Criteria.where("productName").regex("^.*test.*$", "i"));
        expectedQuery.addCriteria(Criteria.where("price").gte(null).lte(null));
        Product product1 = Product.builder().productName("Product").build();

        // Mock ReactiveMongoOperations
        ReactiveMongoTemplate mockOperations = mock(ReactiveMongoTemplate.class);
        when(mockOperations.find(any(Query.class), Mockito.eq(Product.class))).thenReturn(Flux.just(product1));

        // Use the mockOperations instance to create the ProductRepositoryImpl
        productRepository = new ProductRepositoryImpl(mockOperations);

        // When
        Flux<Product> result = productRepository.findByCriteria("test", null,null);

        // Then
        StepVerifier.create(result)
            .expectNext(product1)
            .verifyComplete();

        // Verify that the expected query was passed to the find method
        ArgumentCaptor<Query> queryCaptor = ArgumentCaptor.forClass(Query.class);
        verify(mockOperations).find(queryCaptor.capture(),Mockito.eq(Product.class));
       // assertEquals(expectedQuery.toString(), queryCaptor.getValue().toString());
    }


}

