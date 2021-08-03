package com.zzb.tutorial.mapstructdemo.mapper;

import com.zzb.tutorial.mapstructdemo.data.Car;
import com.zzb.tutorial.mapstructdemo.dto.CarDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    @Mapping(source = "numberOfSeats", target = "seatCount")
    CarDto carToCarDto(Car car);
}
