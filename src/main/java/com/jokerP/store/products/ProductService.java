package com.jokerP.store.products;

import com.jokerP.store.commons.NotFoundException;
import com.jokerP.store.carts.ProductDto;
import com.jokerP.store.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private ProductMapper productMapper;

    public Iterable<ProductDto> getAllProducts(Byte categoryId) {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toDto)
                .filter(product -> categoryId == null || product.getCategoryId().equals(categoryId))
                .toList();
    }

    public ProductDto getProductById(Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NotFoundException(Constants.Message.PRODUCT_NOT_FOUND);
        }

        return productMapper.toDto(product);
    }

    public ProductDto createProduct(ProductDto productDto) {
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            throw new NotFoundException(Constants.Message.CATEGORY_NOT_FOUND);
        }

        var product = productMapper.toEntity(productDto);
        productRepository.save(product);
        productDto.setId(product.getId());

        return productDto;
    }

    public ProductDto updateProduct(Long id, ProductDto productDto) {
        var category = categoryRepository.findById(productDto.getCategoryId()).orElse(null);
        if (category == null) {
            throw new NotFoundException(Constants.Message.CATEGORY_NOT_FOUND);
        }

        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NotFoundException(Constants.Message.PRODUCT_NOT_FOUND);
        }

        productMapper.update(productDto, product);
        productRepository.save(product);
        productDto.setId(product.getId());

        return productDto;
    }

    public void deleteProduct(Long id) {
        var product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new NotFoundException(Constants.Message.PRODUCT_NOT_FOUND);
        }

        productRepository.delete(product);
    }
}
