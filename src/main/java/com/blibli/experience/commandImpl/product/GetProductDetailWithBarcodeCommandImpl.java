package com.blibli.experience.commandImpl.product;

import com.blibli.experience.command.product.GetProductDetailWithBarcodeCommand;
import com.blibli.experience.entity.document.Product;
import com.blibli.experience.model.response.product.GetProductDetailWithBarcodeResponse;
import com.blibli.experience.repository.ProductRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class GetProductDetailWithBarcodeCommandImpl implements GetProductDetailWithBarcodeCommand {

  private ProductRepository productRepository;

  @Autowired
  public GetProductDetailWithBarcodeCommandImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public Mono<GetProductDetailWithBarcodeResponse> execute(String barcode) {
    return productRepository.findFirstByProductBarcode(barcode)
        .switchIfEmpty(Mono.error(new NotFoundException("Product not found!")))
        .map(this::toResponse);
  }

  private GetProductDetailWithBarcodeResponse toResponse(Product product) {
    GetProductDetailWithBarcodeResponse response = new GetProductDetailWithBarcodeResponse();
    BeanUtils.copyProperties(product, response);
    return response;
  }

}
