package com.itsol.recruit.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
    }
}
