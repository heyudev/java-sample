package com.heyudev.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 订单
 * @author heyudev
 * @date 2021/06/17
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private Long id;
    private List<String> skus;
    private Double price;
}
