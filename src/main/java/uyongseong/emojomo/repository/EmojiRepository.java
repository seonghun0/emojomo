package uyongseong.emojomo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import uyongseong.emojomo.domain.Emoji;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EmojiRepository {

    private final EntityManager em;

    public void save(Emoji emoji) {
        em.persist(emoji);
    }

    public Emoji findOne(Long id) {
        return em.find(Emoji.class, id);
    }

    public List<Emoji> findAll() {
        return em.createQuery("select e from Emoji e", Emoji.class).getResultList();
    }

    public List<Emoji> findByMean(String mean){
        return em.createQuery("select e from Emoji e where e_mean = :e_mean", Emoji.class)
                .setParameter("E_mean", mean).getResultList();
    }

    public List<Emoji> findByMeans(String mean){
        return em.createQuery("select e from Emoji e where e_mean Like :e_mean", Emoji.class)
                .setParameter("e_mean", "%"+mean+"%")
                .setMaxResults(10)
                .getResultList();
    }

}
