package com.qp.grocery.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateInventoryCountDTO {
    Long id;
    int updateCount;
    boolean incr;
}
