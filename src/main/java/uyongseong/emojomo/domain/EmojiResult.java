package uyongseong.emojomo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter @Setter
public class EmojiResult {

    private Long id;

    private String code;

    private String source;

    private String emoji;

    private String mean;

    private LocalDateTime regiDate;

    private int useCount;

    private String word;

    public EmojiResult createEmojiResult(Emoji e, String word){
        EmojiResult emoji = new EmojiResult();
        emoji.setEmoji(e.getEmoji());
        emoji.setCode(e.getCode());
        emoji.setSource(e.getSource());
        emoji.setMean(e.getMean());
        emoji.setRegiDate(LocalDateTime.now());
        emoji.setUseCount(0);
        emoji.setWord(word);

        return emoji;
    }
}
