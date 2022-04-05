package com.itsol.recruit.dto;

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
    Date startRecruitmentDate;

    @NonNull
    Date dueDate;

    String skills;

    String description;

    String benefits;

    String jobRequirement;

    @NonNull
    Integer salaryMax;

    @NonNull
    Integer salaryMin;

    @NonNull
    Long contactId;

    Long creatorId;

    Date createDate;

    @NonNull
    Long updateUserId;

    @NonNull
    Date updateDate;

    @NonNull
    Long statusJobId;

    @NonNull
    Integer views;
}
