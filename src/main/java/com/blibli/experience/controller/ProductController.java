package com.blibli.experience.controller;

import com.blibli.experience.ApiPath;
import com.blibli.experience.command.product.GetProductCategoryEnumCommand;
import com.blibli.experience.command.product.GetProductDetailWithBarcodeAndShopCommand;
import com.blibli.experience.model.request.product.GetProductDetailWithBarcodeAndShopRequest;
import com.blibli.experience.model.response.product.GetProductCategoryEnumResponse;
import com.blibli.experience.model.response.product.GetProductDetailWithBarcodeAndShopResponse;
import com.blibli.oss.command.CommandExecutor;
import com.blibli.oss.common.response.Response;
import com.blibli.oss.common.response.ResponseHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    private CommandExecutor commandExecutor;

    @Autowired
    public ProductController(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @GetMapping(value = ApiPath.PRODUCT_WITH_BARCODE)
    public Mono<Response<GetProductDetailWithBarcodeAndShopResponse>> getDetailProductWithBarcodeAndShop(
            @ModelAttribute GetProductDetailWithBarcodeAndShopRequest request) {
        return commandExecutor.execute(GetProductDetailWithBarcodeAndShopCommand.class, request)
                .log("#getDetailProductWithBarcode - Successfully executing command.")
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(value = ApiPath.PRODUCT_CATEGORY_ENUM)
    public Mono<Response<GetProductCategoryEnumResponse>> getProductCategoryEnum() {
        return commandExecutor.execute(GetProductCategoryEnumCommand.class, "")
                .log("#getProductCategoryEnum - Successfully executing command.")
                .map(ResponseHelper::ok)
                .subscribeOn(Schedulers.elastic());
    }

}
