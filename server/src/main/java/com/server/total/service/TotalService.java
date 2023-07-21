package com.server.total.service;

import com.server.advice.BusinessLogicException;
import com.server.advice.ExceptionCode;
import com.server.total.entity.Total;
import com.server.total.repository.TotalRepository;
import com.server.wishlist.entity.Wishlist;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TotalService {

    private final TotalRepository totalRepository;

    public TotalService(TotalRepository totalRepository) {
        this.totalRepository = totalRepository;
    }

    public Total createTotal(Total total, Long memberId) {
        total.setMemberId(memberId);
        Total savedTotal = totalRepository.save(total);
        return savedTotal;

    }

    public Total updateTotal(Total total, Long memberId) {
        Total findTotal = findVerifiedTotal(total.getTotalId());
        if(!findTotal.getMemberId().equals(memberId)){
            throw new BusinessLogicException(ExceptionCode.WISHLIST_MEMBER_NOT_MATCH);
        }
        Optional.ofNullable(total.getTotalIncome())
                .ifPresent(totalIncome -> findTotal.setTotalIncome(totalIncome));
        Optional.ofNullable(total.getTotalOutcome())
                .ifPresent(totalOutcome -> findTotal.setTotalOutcome(totalOutcome));
        Optional.ofNullable(total.getGoal())
                .ifPresent(goal -> findTotal.setGoal(goal));
        return totalRepository.save(findTotal);
    }

    @Transactional(readOnly = true)
    public Total findTotal(Long totalId, Long memberId) {
        Total findTotal = totalRepository.findById(totalId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.TOTAL_NOT_FOUND));

        if (!findTotal.getMemberId().equals(memberId)) {
            throw new BusinessLogicException(ExceptionCode.TOTAL_MEMBER_NOT_MATCH);
        }

        return findTotal;
    }

    private Total findVerifiedTotal(Long totalId) {
        Optional<Total> optionalTotal = totalRepository.findById(totalId);
        if(optionalTotal.isEmpty()){
            throw new BusinessLogicException(ExceptionCode.TOTAL_NOT_FOUND);
        }
        return optionalTotal.get();
    }


}
