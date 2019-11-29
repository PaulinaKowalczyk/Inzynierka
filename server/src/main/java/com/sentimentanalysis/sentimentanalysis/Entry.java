package com.sentimentanalysis.sentimentanalysis;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@Table(name="ENTRY")
public class Entry {
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private @NonNull String text;
    private @NonNull Timestamp timestamp;

    @OneToMany(mappedBy="entry")
    private Set<Result> resultSet;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

}
