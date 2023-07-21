package com.server.total.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "total")
@Builder
public class Total {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long totalId;
    @Column
    private BigDecimal totalIncome;
    @Column
    private BigDecimal totalOutcome;
    @Column
    private BigDecimal goal;
    @Column
    private Long memberId;


}