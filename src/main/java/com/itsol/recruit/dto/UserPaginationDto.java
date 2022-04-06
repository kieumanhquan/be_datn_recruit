package com.itsol.recruit.dto;

import com.itsol.recruit.entity.Job;
import com.itsol.recruit.entity.User;
import lombok.Data;
import java.util.List;

@Data
public class UserPaginationDto {
    List<User> list;

    Long totalPage;
}
