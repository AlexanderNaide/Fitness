package org.satal.backservice.entities.enums;

import java.util.Objects;

public enum OrderStatusEnums {

    CREATED("created"),
    FORMED("formed"),
    DELIVERED("delivered"),
    READY("ready to receive"),
    RECEIVED("received"),
    CLOSED("closed");

    private final String status;

    OrderStatusEnums(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatusEnums getByStatus(String status){
        for (OrderStatusEnums value : values()) {
            if (Objects.equals(value.status, status)){
                return value;
            }
        }
        throw new IllegalArgumentException();
    }

}
