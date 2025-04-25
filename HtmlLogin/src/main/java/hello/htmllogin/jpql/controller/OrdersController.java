package hello.htmllogin.jpql.controller;

import hello.htmllogin.jpql.dto.OrdersDto;
import hello.htmllogin.jpql.entity.Orders;
import hello.htmllogin.jpql.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    // #0
    // Controller 에서 Entity 를 JSON 변환, 응답
//    @GetMapping("/listorders")
//    public List<Orders> listOrder(){ // 엔티티를 그대로 반환해서 순환참조 나는듯
//        return ordersService.listOrder();
//    }

    // #1
    // Controller 에서 Entity -> Dto 변경 후, Dto 를 리턴
//    @GetMapping("/listorders")
//    public List<OrdersDto> listOrder(){ // 엔티티를 그대로 반환해서 순환참조 나는듯
//        List<Orders> orderList =  ordersService.listOrder();
//        List<OrdersDto> orderDtoList = new ArrayList<>();
//
//        orderList.forEach(orders -> {
//            OrdersDto ordersDto = OrdersDto.builder()
//                    .id(orders.getId())
//                    .orderQuantity(orders.getOrderQuantity())
//                    .orderDate(orders.getOrderDate())
//                    .build();
//            orderDtoList.add(ordersDto);
//        });
//
//        return orderDtoList;
//    }

    // #2
    // Controller 에서 Entity -> Dto 변경 후, Dto 를 리턴
//    @GetMapping("/listordersservicedto")
//    public List<OrdersDto> listOrdersservicedto(){ // 엔티티를 그대로 반환해서 순환참조 나는듯
//        return ordersService.listOrdersServiceDto();
//    }

    // #3
    @GetMapping("/listordersrepositorydto")
    public List<OrdersDto> listOrdersrepositorydto(){ // 엔티티를 그대로 반환해서 순환참조 나는듯
        return ordersService.listOrdersRepositoryDto();
    }

}
