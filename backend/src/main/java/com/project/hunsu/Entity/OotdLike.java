package com.project.hunsu.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OotdLike {

    @Id
    @Column(name = "like_idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;


    @ManyToOne
    @JoinColumn(name = "ootd_idx")
    private Ootd ootd;

    @ManyToOne
    @JoinColumn(name = "nickname")
    private User user;


}
