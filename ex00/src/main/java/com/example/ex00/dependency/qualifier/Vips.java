package com.example.ex00.dependency.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component // 등록 !!!
@Qualifier("vips")
@Primary
public class Vips implements Restaurant {
    @Override
    public int getSteak(){return 10000;}
}
