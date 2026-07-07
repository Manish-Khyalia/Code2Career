package com.manish.code2career.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "jobs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String companyName;

    private String jobRole;

    private String location;

    private Double salary;

    private String status;

    private String applicationLink;

    private String deadline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
