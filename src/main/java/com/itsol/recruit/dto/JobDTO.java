package com.itsol.recruit.dto;

import com.itsol.recruit.core.CustomValidate.DueDateConstraint;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobDTO {

    Long id;

    @NonNull
    @Size(max = 150, message = "Over 150 characters")
    String name;

    @NonNull
    Long jobPositionId;

    @NonNull
    Integer numberExperience;

    @NonNull
    Long workingFormId;

    @NonNull
    @Size(max = 300, message = "Over 300 characters")
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
    @DueDateConstraint
    Date dueDate;

    String skills;

    @Size(max = 2000, message = "Over 2000 characters")
    String description;

    @Size(max = 2000, message = "Over 2000 characters")
    String benefits;

    @Size(max = 2000, message = "Over 2000 characters")
    String jobRequirement;

    @NonNull
    Integer salaryMax;

    @NonNull
    Integer salaryMin;

    @NonNull
    Long contactId;

    @NonNull
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
