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
public class SurvayList {

    @Id @GeneratedValue
    @Column(name = "SL_id")
    private Long id;

    @Column(name = "SL_text")
    private String text;

    @Column(name = "SL_regi_date")
    private LocalDateTime regiDate;

    @Column(name = "SL_answer")
    private String answer;

    @Column(name = "SL_answer_count")
    private int answerCount;

    @Column(name = "SL_answer_date")
    private LocalDateTime answerDate;

    public static SurvayList createSurvayList(String text){
        SurvayList survayList = new SurvayList();
        //question
        survayList.setText(text);
        survayList.setRegiDate(LocalDateTime.now());

        return survayList;
    }


}
