package com.itsol.recruit.dto;

import com.itsol.recruit.entity.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobDTO {
    Long id;

    @NonNull
    String name;

    @NonNull
    Long jobPositionId;

    @NonNull
    Integer numberExperience;

    @NonNull
    Long workingFormId;

    @NonNull
    String addressWork;

    @NonNull
    Long academicLevelId;

    @NonNull
    Long rankId;

    @NonNull
    Integer qtyPerson;

    @NonNull
    String startRecruitmentDate;

    @NonNull
    String dueDate;

    @NonNull
    String skills;

    @NonNull
    String description;

    @NonNull
    String benefits;

    @NonNull
    String jobRequirement;

    @NonNull
    Integer salaryMax;

    @NonNull
    Integer salaryMin;

    @NonNull
    Long contactId;

    Long creatorId;

    String createDate;

    @NonNull
    Long updateUserId;

    @NonNull
    String updateDate;

    @NonNull
    Long statusJobId;

    @NonNull
    Integer views;
}
