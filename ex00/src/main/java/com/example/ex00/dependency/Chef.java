package com.example.ex00.dependency;


import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class Chef {
    @NonNull
    private final Restaurant restaurant;
}
