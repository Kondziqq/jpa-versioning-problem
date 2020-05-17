package com.example.jpaversiontraining.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public abstract class BaseDto {

    private long version;

    private Date creationDate;

    private Date lastModificationDate;
}
