package uyongseong.emojomo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import uyongseong.emojomo.domain.FeedBack;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class FeedBackRepository {

    private final EntityManager em;

    public void save(String type, String feedback)
    {
        FeedBack feedBack = FeedBack.createFeedBack(feedback, type);

        em.persist(feedBack);
    }
}
