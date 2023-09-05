package uyongseong.emojomo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uyongseong.emojomo.Service.EmojiService;
import uyongseong.emojomo.Service.FeedBackService;
import uyongseong.emojomo.domain.FeedBackDTO;

@RestController
@RequestMapping(value = "feedback")
@RequiredArgsConstructor
@CrossOrigin(origins = {"https://quickmoji.site", "http://localhost"})
public class FeedBackController {

    private final FeedBackService feedBackService;

    @PostMapping(value = "save")
    public void save(@RequestBody FeedBackDTO feedBackDTO){
        feedBackService.save(feedBackDTO.getFeedback(), feedBackDTO.getType());
    }
}
