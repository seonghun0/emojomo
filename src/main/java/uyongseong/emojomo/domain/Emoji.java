package uyongseong.emojomo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Emoji {

    @Id
    @GeneratedValue
    @Column(name = "E_id")
    private Long id;

    @Column(name = "E_code")
    private String code;

    @Column(name = "E_src", columnDefinition = "TEXT")
    private String source;

    @Column(name = "E_emoji")
    private String emoji;

    @Column(name = "E_mean")
    private String mean;

    @Column(name = "E_regi_date")
    private LocalDateTime regiDate;

    @Column(name = "E_useCount")
    private int useCount;

    public void addUseCount(){
        this.useCount += 1;
    }

    public Emoji createEmoji(String code, String source, String e, String mean){
        Emoji emoji = new Emoji();
        emoji.setEmoji(e);
        emoji.setCode(code);
        emoji.setSource(source);
        emoji.setMean(mean);
        emoji.setRegiDate(LocalDateTime.now());
        emoji.setUseCount(0);

        return emoji;
    }


}
