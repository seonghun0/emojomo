package uyongseong.emojomo.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeedBack {

    @Id @GeneratedValue
    @Column(name = "FB_id")
    private Long id;

    @Column(name = "FB_type")
    private String type;

    @Column(name = "FB_regi_date")
    private LocalDateTime regiDate;

    @Column(name = "FB_feedback")
    private String feedback;

    public static FeedBack createFeedBack(String feedback, String type){
        FeedBack feedBack = new FeedBack();
        //question
        feedBack.setType(type);
        feedBack.setFeedback(feedback);
        feedBack.setRegiDate(LocalDateTime.now());

        return feedBack;
    }


}
