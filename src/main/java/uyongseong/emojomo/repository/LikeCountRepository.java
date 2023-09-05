package uyongseong.emojomo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import uyongseong.emojomo.domain.LikeCount;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class LikeCountRepository {

    private final EntityManager em;

    public void save(LikeCount likeCount){
        LikeCount lc = new LikeCount().createLikeCount(likeCount.getAccessType(), likeCount.getIpAddress());
        em.persist(lc);
    }

    public Long getCount()
    {
        return em.createQuery("select count(lc) from LikeCount lc", Long.class)
                .getSingleResult();
    }
}
