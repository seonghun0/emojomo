package uyongseong.emojomo.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import uyongseong.emojomo.domain.Emoji;
import uyongseong.emojomo.domain.SearchResult;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchResultRepository {

    private final EntityManager em;

    public void save(String searchText, String result){
        SearchResult searchResult = SearchResult.createSearchResults(searchText, result);

        em.persist(searchResult);
    }
}
