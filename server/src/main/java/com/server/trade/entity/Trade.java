package com.server.trade.entity;

import com.server.member.entity.Member;
import com.server.total.entity.Total;
import com.server.utils.CustomBeanUtils;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "trade")
@Builder
public class Trade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tradeId;
    private String type; //수입 or 지출
    private String tradeName; //내역
    @NotNull
    private BigDecimal amount; //금액정확도를 위해 BigDecimal 타입을 사용했습니다.
    private String note; //비고
    private LocalDate date; //날짜 LocalDate.of(2023, 7, 1);
    @Enumerated(EnumType.STRING)
    private Category category;
    private Long memberId;


    public void setDate(LocalDate date) {
        this.date = date;
    }

//    @ManyToOne
//    @JoinColumn(name = "MEMBER_ID")
//    private Member member;

//    public long getMemberId(){
//        return member.getMemberId();
//    }










    


}
