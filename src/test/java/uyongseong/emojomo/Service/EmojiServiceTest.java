package uyongseong.emojomo.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import uyongseong.emojomo.domain.Emoji;
import uyongseong.emojomo.repository.EmojiRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EmojiServiceTest {

    @Autowired EmojiService emojiService;
    @Autowired EmojiRepository emojiRepository;

    @Test
    public void 이모지() throws Exception {
        //given
        Emoji emoji = new Emoji();
        emoji.setEmoji("2134");
        //when
        emojiService.save(emoji);
        //then
        assertEquals(emoji, emojiRepository.findOne(emoji.getId()));
    }

    @Test
    public void 검색() throws Exception {
        //given
        String str = "검색 할려하는 얼굴";
        //List<Emoji> emojiList = emojiService.searchWords(str);
        //when
//        emojiList.forEach(emoji -> {
//            System.out.println("emoji = " + emoji.getEmoji());
//        });
        //then
    }

}