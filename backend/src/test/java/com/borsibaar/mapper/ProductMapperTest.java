package com.borsibaar.mapper;

import com.borsibaar.dto.ProductRequestDto;
import com.borsibaar.dto.ProductResponseDto;
import com.borsibaar.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {

    private ProductMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(ProductMapper.class);
    }

    @Test
    void when_calling_to_response_with_all_fields_should_map_correctly() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setCategoryId(7L);
        product.setName("Diamond");
        product.setDescription("Shiny diamond");
        product.setBasePrice(BigDecimal.valueOf(5.50));
        product.setMinPrice(BigDecimal.valueOf(3.00));
        product.setMaxPrice(BigDecimal.valueOf(10.00));

        // Act
        ProductResponseDto dto = mapper.toResponse(product);

        // Assert
        assertEquals(1L, dto.id());
        assertEquals("Diamond", dto.name());
        assertEquals("Shiny diamond", dto.description());
        assertEquals(BigDecimal.valueOf(5.50), dto.currentPrice());
        assertEquals(BigDecimal.valueOf(3.00), dto.minPrice());
        assertEquals(BigDecimal.valueOf(10.00), dto.maxPrice());
        assertEquals(7L, dto.categoryId());
        assertNull(dto.categoryName());
    }

    @Test
    void when_calling_to_response_should_map_base_price_to_current_price() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Emerald");
        product.setBasePrice(BigDecimal.valueOf(12.99));
        product.setCategoryId(5L);

        // Act
        ProductResponseDto dto = mapper.toResponse(product);

        // Assert
        assertEquals(1L, dto.id());
        assertEquals("Emerald", dto.name());
        assertEquals(BigDecimal.valueOf(12.99), dto.currentPrice());
        assertEquals(5L, dto.categoryId());
    }

    @Test
    void when_calling_to_response_with_null_description_should_map_as_null() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Redstone");
        product.setBasePrice(BigDecimal.valueOf(15.00));
        product.setCategoryId(3L);
        product.setDescription(null);

        // Act
        ProductResponseDto dto = mapper.toResponse(product);

        // Assert
        assertEquals(1L, dto.id());
        assertEquals("Redstone", dto.name());
        assertEquals(BigDecimal.valueOf(15.00), dto.currentPrice());
        assertEquals(3L, dto.categoryId());
        assertNull(dto.description());
    }

    @Test
    void when_calling_to_response_with_null_prices_should_map_as_null() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Dirt");
        product.setBasePrice(BigDecimal.valueOf(5.00));
        product.setCategoryId(1L);
        product.setMinPrice(null);
        product.setMaxPrice(null);

        // Act
        ProductResponseDto dto = mapper.toResponse(product);

        // Assert
        assertEquals(1L, dto.id());
        assertEquals("Dirt", dto.name());
        assertEquals(BigDecimal.valueOf(5.00), dto.currentPrice());
        assertEquals(1L, dto.categoryId());
        assertNull(dto.minPrice());
        assertNull(dto.maxPrice());
    }

    @Test
    void when_calling_to_entity_with_all_fields_should_map_correctly() {
        // Arrange
        ProductRequestDto request = new ProductRequestDto(
                "Netherite",
                "Ancient netherite",
                BigDecimal.valueOf(25.00),
                BigDecimal.valueOf(20.00),
                BigDecimal.valueOf(30.00),
                8L
        );

        // Act
        Product entity = mapper.toEntity(request);

        // Assert
        assertEquals("Netherite", entity.getName());
        assertEquals("Ancient netherite", entity.getDescription());
        assertEquals(BigDecimal.valueOf(25.00), entity.getBasePrice());
        assertEquals(BigDecimal.valueOf(20.00), entity.getMinPrice());
        assertEquals(BigDecimal.valueOf(30.00), entity.getMaxPrice());
        assertEquals(8L, entity.getCategoryId());
        assertTrue(entity.isActive());
    }

    @Test
    void when_calling_to_entity_should_map_current_price_to_base_price() {
        // Arrange
        ProductRequestDto request = new ProductRequestDto(
                "Obsidian",
                "Dark obsidian",
                BigDecimal.valueOf(18.50),
                BigDecimal.valueOf(15.00),
                BigDecimal.valueOf(22.00),
                6L
        );

        // Act
        Product entity = mapper.toEntity(request);

        // Assert
        assertEquals("Obsidian", entity.getName());
        assertEquals("Dark obsidian", entity.getDescription());
        assertEquals(BigDecimal.valueOf(18.50), entity.getBasePrice());
        assertEquals(BigDecimal.valueOf(15.00), entity.getMinPrice());
        assertEquals(BigDecimal.valueOf(22.00), entity.getMaxPrice());
        assertEquals(6L, entity.getCategoryId());
    }

    @Test
    void when_calling_to_entity_should_set_ignored_fields_as_null() {
        // Arrange
        ProductRequestDto request = new ProductRequestDto(
                "Quartz",
                "Nether quartz",
                BigDecimal.valueOf(20.00),
                BigDecimal.valueOf(18.00),
                BigDecimal.valueOf(25.00),
                4L
        );

        // Act
        Product entity = mapper.toEntity(request);

        // Assert
        assertNull(entity.getId());
        assertNull(entity.getOrganizationId());
        assertNull(entity.getCreatedAt());
        assertNull(entity.getUpdatedAt());
    }

    @Test
    void when_calling_to_entity_should_default_active_flag_to_true() {
        // Arrange
        ProductRequestDto request = new ProductRequestDto(
                "Glowstone",
                null,
                BigDecimal.valueOf(22.00),
                BigDecimal.valueOf(20.00),
                BigDecimal.valueOf(28.00),
                9L
        );

        // Act
        Product entity = mapper.toEntity(request);

        // Assert
        assertEquals("Glowstone", entity.getName());
        assertNull(entity.getDescription());
        assertEquals(BigDecimal.valueOf(22.00), entity.getBasePrice());
        assertEquals(BigDecimal.valueOf(20.00), entity.getMinPrice());
        assertEquals(BigDecimal.valueOf(28.00), entity.getMaxPrice());
        assertEquals(9L, entity.getCategoryId());
        assertTrue(entity.isActive());
    }

    @Test
    void when_calling_to_entity_with_null_description_should_map_as_null() {
        // Arrange
        ProductRequestDto request = new ProductRequestDto(
                "Lapis",
                null,
                BigDecimal.valueOf(30.00),
                BigDecimal.valueOf(25.00),
                BigDecimal.valueOf(35.00),
                2L
        );

        // Act
        Product entity = mapper.toEntity(request);

        // Assert
        assertEquals("Lapis", entity.getName());
        assertNull(entity.getDescription());
        assertEquals(BigDecimal.valueOf(30.00), entity.getBasePrice());
        assertEquals(BigDecimal.valueOf(25.00), entity.getMinPrice());
        assertEquals(BigDecimal.valueOf(35.00), entity.getMaxPrice());
        assertEquals(2L, entity.getCategoryId());
    }

    @Test
    void when_calling_update_entity_should_update_all_modifiable_fields() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setName("Old Block");
        existingProduct.setDescription("Old block description");
        existingProduct.setBasePrice(BigDecimal.valueOf(10.00));
        existingProduct.setMinPrice(BigDecimal.valueOf(8.00));
        existingProduct.setMaxPrice(BigDecimal.valueOf(15.00));
        existingProduct.setCategoryId(1L);

        ProductRequestDto updateRequest = new ProductRequestDto(
                "Updated Block",
                "Updated block description",
                BigDecimal.valueOf(12.00),
                BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(18.00),
                2L
        );

        // Act
        mapper.updateEntity(existingProduct, updateRequest);

        // Assert
        assertEquals("Updated Block", existingProduct.getName());
        assertEquals("Updated block description", existingProduct.getDescription());
        assertEquals(BigDecimal.valueOf(12.00), existingProduct.getBasePrice());
        assertEquals(BigDecimal.valueOf(10.00), existingProduct.getMinPrice());
        assertEquals(BigDecimal.valueOf(18.00), existingProduct.getMaxPrice());
        assertEquals(2L, existingProduct.getCategoryId());
    }

    @Test
    void when_calling_update_entity_should_preserve_protected_fields() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setId(100L);
        existingProduct.setOrganizationId(5L);
        existingProduct.setName("Diamond");
        existingProduct.setActive(true);
        OffsetDateTime createdAt = OffsetDateTime.now().minusDays(10);
        OffsetDateTime updatedAt = OffsetDateTime.now().minusDays(5);
        existingProduct.setCreatedAt(createdAt);
        existingProduct.setUpdatedAt(updatedAt);

        ProductRequestDto updateRequest = new ProductRequestDto(
                "Updated Diamond",
                "Block description",
                BigDecimal.valueOf(6.00),
                BigDecimal.valueOf(4.00),
                BigDecimal.valueOf(8.00),
                3L
        );

        // Act
        mapper.updateEntity(existingProduct, updateRequest);

        // Assert
        assertEquals(100L, existingProduct.getId());
        assertEquals(5L, existingProduct.getOrganizationId());
        assertTrue(existingProduct.isActive());
        assertEquals(createdAt, existingProduct.getCreatedAt());
        assertEquals(updatedAt, existingProduct.getUpdatedAt());
    }

    @Test
    void when_calling_update_entity_with_null_description_should_preserve_existing_value() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setName("Block");
        existingProduct.setDescription("Existing block description");
    

        ProductRequestDto updateRequest = new ProductRequestDto(
                "Updated Block",
                null,
                BigDecimal.valueOf(11.00),
                BigDecimal.valueOf(9.00),
                BigDecimal.valueOf(13.00),
                1L
        );

        // Act
        mapper.updateEntity(existingProduct, updateRequest);

        // Assert
        assertEquals("Existing block description", existingProduct.getDescription());
        assertEquals("Updated Block", existingProduct.getName());
    }

    @Test
    void when_calling_update_entity_with_null_price_should_preserve_existing_value() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setName("Block");
        existingProduct.setBasePrice(BigDecimal.valueOf(10.00));
        existingProduct.setMinPrice(BigDecimal.valueOf(8.00));
        existingProduct.setMaxPrice(BigDecimal.valueOf(12.00));
        existingProduct.setCategoryId(1L);

        ProductRequestDto updateRequest = new ProductRequestDto(
                "Block",
                "Block description",
                BigDecimal.valueOf(10.00),
                null,
                null,
                1L
        );

        // Act
        mapper.updateEntity(existingProduct, updateRequest);

        // Assert
        assertEquals(BigDecimal.valueOf(8.00), existingProduct.getMinPrice());
        assertEquals(BigDecimal.valueOf(12.00), existingProduct.getMaxPrice());
    }

    @Test
    void when_calling_update_entity_should_map_current_price_to_base_price() {
        // Arrange
        Product existingProduct = new Product();
        existingProduct.setName("Block");
        existingProduct.setBasePrice(BigDecimal.valueOf(10.00));
        existingProduct.setCategoryId(1L);

        ProductRequestDto updateRequest = new ProductRequestDto(
                "Block",
                "Block description",
                BigDecimal.valueOf(15.00),
                BigDecimal.valueOf(12.00),
                BigDecimal.valueOf(18.00),
                1L
        );

        // Act
        mapper.updateEntity(existingProduct, updateRequest);

        // Assert
        assertEquals("Block", existingProduct.getName());
        assertEquals("Block description", existingProduct.getDescription());
        assertEquals(BigDecimal.valueOf(15.00), existingProduct.getBasePrice());
        assertEquals(BigDecimal.valueOf(12.00), existingProduct.getMinPrice());
        assertEquals(BigDecimal.valueOf(18.00), existingProduct.getMaxPrice());
        assertEquals(1L, existingProduct.getCategoryId());
    }

    @Test
    void when_calling_to_entity_with_max_length_strings_should_handle_correctly() {
        // Arrange
        String longName = "a".repeat(120);
        String longDescription = "b".repeat(1000);

        ProductRequestDto request = new ProductRequestDto(
                longName,
                longDescription,
                BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(8.00),
                BigDecimal.valueOf(12.00),
                1L
        );

        // Act
        Product entity = mapper.toEntity(request);

        // Assert
        assertEquals(longName, entity.getName());
        assertEquals(longDescription, entity.getDescription());
    }

    @Test
    void when_calling_to_response_should_preserve_big_decimal_precision() {
        // Arrange
        Product product = new Product();
        product.setId(1L);
        product.setName("Dirt");
        product.setBasePrice(new BigDecimal("19.9999"));
        product.setMinPrice(new BigDecimal("15.5555"));
        product.setMaxPrice(new BigDecimal("25.1234"));
        product.setCategoryId(1L);

        // Act
        ProductResponseDto dto = mapper.toResponse(product);

        // Assert
        assertEquals(new BigDecimal("19.9999"), dto.currentPrice());
        assertEquals(new BigDecimal("15.5555"), dto.minPrice());
        assertEquals(new BigDecimal("25.1234"), dto.maxPrice());
    }
}
