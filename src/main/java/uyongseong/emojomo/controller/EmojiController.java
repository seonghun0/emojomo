package uyongseong.emojomo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import uyongseong.emojomo.Service.EmojiService;
import uyongseong.emojomo.domain.Emoji;
import uyongseong.emojomo.domain.EmojiResult;
import uyongseong.emojomo.domain.RequestDataDTO;
import uyongseong.emojomo.domain.ResultData;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "emoji")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://quickmoji.site", "http://localhost"})
public class EmojiController {

    private final EmojiService emojiService;

    @GetMapping(value = "getEmoji")
    public List<Emoji> getEmoji(){
        return emojiService.findEmojis();
    }

    @RequestMapping(value = "searchEmoji", method = POST )
    public ResponseEntity<ResultData> searchEmoji(@RequestBody RequestDataDTO requestDataDTO){

        String str = requestDataDTO.getStr();
        ResultData resultData = emojiService.searchWords(str);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return new ResponseEntity<>(resultData, header, HttpStatus.OK);
    }

    @GetMapping(value = "splitWords")
    public List<String> splitWords(@RequestBody RequestDataDTO requestDataDTO){
        return emojiService.splitWords(requestDataDTO.getStr());
    }

    @PostMapping(value = "saveFeedBack")
    public void saveFeedBack(String type, String feedback){

    }

}
