package uyongseong.emojomo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SearchResult {

    @Id @GeneratedValue
    @Column(name = "SR_id")
    private Long id;

    @Column(name = "SR_search_text", columnDefinition = "LONGTEXT")
    private String searchText;

    @Column(name = "SR_emoji_result", columnDefinition = "LONGTEXT")
    private String emojiResult;

    @Column(name = "SR_search_time")
    private LocalDateTime searchTime;

    public static SearchResult createSearchResults(String searchText, String result){
        SearchResult searchResult = new SearchResult();

        searchResult.setEmojiResult(result);
        searchResult.setSearchText(searchText);
        searchResult.setSearchTime(LocalDateTime.now());

        return searchResult;
    }

}
