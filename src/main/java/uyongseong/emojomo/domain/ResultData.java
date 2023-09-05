package uyongseong.emojomo.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
public class ResultData {

    private List<EmojiResult> emojiResult;

    private List<String> stringList;

    public ResultData createResultData(List<EmojiResult> e, List<String> word){
        ResultData resultData = new ResultData();
        resultData.setEmojiResult(e);
        resultData.setStringList(word);

        return resultData;
    }

}
