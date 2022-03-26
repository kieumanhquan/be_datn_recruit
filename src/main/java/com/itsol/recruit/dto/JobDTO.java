package com.itsol.recruit.dto;

import com.itsol.recruit.entity.WorkingForm;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JobDTO {
    Long id;

    String name;

    Long jobPositionId;

    Integer numberExperience;

    Long workingFormId;

    String addressWork;

    Long academicLevelId;

    Long rankId;

    Integer qtyPerson;

    Date startRecruitmentDate;

    Date dueDate;

    String skills;
}
