package com.example.ex00.dependency.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
@Qualifier("Laptop")
public class Outback implements Restaurant {
    @Override
    public int getScreenWidth() {
        return 1280;
    }
}
