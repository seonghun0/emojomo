package uyongseong.emojomo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uyongseong.emojomo.Service.EmojiService;
import uyongseong.emojomo.domain.Emoji;
import uyongseong.emojomo.domain.EmojiResult;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "emoji")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EmojiController {

    private final EmojiService emojiService;

    @GetMapping(value = "getEmoji")
    public List<Emoji> getEmoji(){
        return emojiService.findEmojis();
    }

    @RequestMapping(value = "searchEmoji", method = POST )
    public ResponseEntity<List<EmojiResult>> searchEmoji(String str){
        List<EmojiResult> emojiList = emojiService.searchWords(str);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(emojiList,header, HttpStatus.OK);
    }

    @RequestMapping(value = "searchEmoji2", method = POST )
    public List<String> searchEmoji2(String str){
        return emojiService.searchWords2(str);
    }


    @GetMapping(value = "splitWords")
    public List<String> splitWords(String str){
        return emojiService.splitWords2(str);
    }

}
