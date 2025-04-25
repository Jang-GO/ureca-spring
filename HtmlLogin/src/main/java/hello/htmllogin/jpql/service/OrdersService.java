package hello.htmllogin.jpql.service;

import hello.htmllogin.jpql.dto.OrdersDto;
import hello.htmllogin.jpql.entity.Orders;

import java.util.List;

public interface OrdersService {
    List<Orders> listOrder();
    List<OrdersDto> listOrdersServiceDto();
    List<OrdersDto> listOrdersRepositoryDto();
}
