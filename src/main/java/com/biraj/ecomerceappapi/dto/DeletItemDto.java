package com.biraj.ecomerceappapi.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DeletItemDto {
  public Long itemId;
  public Long userId;
  public String message;
}
