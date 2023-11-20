package com.sku.codesnippetshop.domain.Order.api;

import com.sku.codesnippetshop.domain.Order.dto.OrderCreateDTO;
import com.sku.codesnippetshop.domain.Order.dto.OrderReadDTO;
import com.sku.codesnippetshop.domain.Order.dto.OrderUpdateDTO;
import com.sku.codesnippetshop.domain.Order.service.OrderService;
import com.sku.codesnippetshop.domain.item.dto.ItemCreateDto;
import com.sku.codesnippetshop.domain.item.dto.ItemReadDto;
import com.sku.codesnippetshop.domain.item.dto.ItemUpdateDto;
import com.sku.codesnippetshop.global.error.NotFoundException;
import com.sku.codesnippetshop.global.response.ResponseFormat;
import com.sku.codesnippetshop.global.response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /* 주문 정보 등록 컨트롤러
    param : 등록 주문 정보 info */
    @PostMapping
    public ResponseFormat<Void> regItem(@RequestBody OrderCreateDTO create) {
        try {
            orderService.createOrder(create);
            return ResponseFormat.success(com.sku.codesnippetshop.global.response.ResponseStatus.SUCCESS_CREATE);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /* 주문 정보 조회 컨트롤러
    param : 조회 주문 orderId*/
    @GetMapping("/{orderId}")
    public ResponseFormat<OrderReadDTO> getOrderByOrderId(@PathVariable(name = "orderId") Long orderId) {
        try {
            return ResponseFormat.successWithData(ResponseStatus.SUCCESS_OK, orderService.getOrderByOrderId(orderId));
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }


    /* 주문 정보 수정 컨트롤러
    param : 수정 주문 orderId, 수정 주문 정보 info */
    @PutMapping("/{orderId}")
    public ResponseFormat<Void> updateOrderByOrderId(@PathVariable(name = "orderId") Long orderId,
                                                   @RequestBody OrderUpdateDTO update) {
        try {
            orderService.updateOrder(orderId, update);
            return ResponseFormat.success(com.sku.codesnippetshop.global.response.ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

    /* 주문 삭제 컨트롤러
    param : 삭제 주문 orderId */
    @DeleteMapping("/{orderId}")
    public ResponseFormat<Void> deleteOrderByOrderId(@PathVariable(name = "orderId") Long orderId) {
        try {
            orderService.deleteOrderByOrderId(orderId);
            return ResponseFormat.success(ResponseStatus.SUCCESS_NO_CONTENT);
        } catch (NotFoundException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_NOT_FOUND);
        } catch (RuntimeException e) {
            return ResponseFormat.error(ResponseStatus.FAIL_BAD_REQUEST);
        }
    }

}
