package com.sentimentanalysis.sentimentanalysis;

import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name="RESULTS")
public class Result {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="entry_id", nullable=false)
    private Entry entry;

    private @NonNull String methon;
    private @NonNull float positive;
    private @NonNull float negative;
    private @NonNull float neutral;
    private @NonNull float overall;

}
