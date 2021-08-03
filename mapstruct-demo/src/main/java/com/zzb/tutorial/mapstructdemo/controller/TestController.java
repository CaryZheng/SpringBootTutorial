package com.zzb.tutorial.mapstructdemo.controller;

import com.zzb.tutorial.mapstructdemo.data.Car;
import com.zzb.tutorial.mapstructdemo.data.CarType;
import com.zzb.tutorial.mapstructdemo.data.Order;
import com.zzb.tutorial.mapstructdemo.dto.CarDto;
import com.zzb.tutorial.mapstructdemo.mapper.CarMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    private Logger log = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/mapper")
    public CarDto test() {
        Car car = new Car();
        car.setMake("Morris");
        car.setNumberOfSeats(5);
        car.setType(CarType.CAR_2);

        Order order = new Order();
        order.setOrderId(100);
        order.setDescription("Test Order1");

        car.setOrder(order);

        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

        return carDto;
    }

}
