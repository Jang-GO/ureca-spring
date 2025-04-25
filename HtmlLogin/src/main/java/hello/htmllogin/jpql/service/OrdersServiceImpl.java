package hello.htmllogin.jpql.service;

import hello.htmllogin.jpql.dto.OrdersDto;
import hello.htmllogin.jpql.entity.Orders;
import hello.htmllogin.jpql.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersServiceImpl implements OrdersService{

    private final OrdersRepository ordersRepository;

    @Override
    public List<Orders> listOrder() {
        return ordersRepository.listOrder();
    }

    @Override
    public List<OrdersDto> listOrdersServiceDto() {
        List<Orders> orderList =  ordersRepository.listOrdersServiceDto();
        List<OrdersDto> orderDtoList = new ArrayList<>();

        orderList.forEach(orders -> {
            OrdersDto ordersDto = OrdersDto.builder()
                    .id(orders.getId())
                    .orderQuantity(orders.getOrderQuantity())
                    .orderDate(orders.getOrderDate())
                    .build();
            orderDtoList.add(ordersDto);
        });

        return orderDtoList;
    }

    @Override
    public List<OrdersDto> listOrdersRepositoryDto() {
        return ordersRepository.listOrdersRepositoryDto();
    }
}
