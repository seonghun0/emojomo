package uyongseong.emojomo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uyongseong.emojomo.Service.EmojiService;
import uyongseong.emojomo.domain.Emoji;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "csv")
@CrossOrigin(origins = {"https://quickmoji.site", "http://localhost"})
public class CsvReaderController {
    
    @Autowired
    EmojiService emojiService;

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping(value = "save")
    public void saveEmoji(HttpServletRequest request) throws Exception {
        var path = "/csv/emoji_list_final.csv";

        ClassPathResource is = new ClassPathResource(path);
        InputStream inputStream = is.getInputStream();

        List<Emoji> data = emojiService.readCSVandSaveWithStream(inputStream);

        if(data.isEmpty()){
            System.out.println("실패");
        }else{
            System.out.println("완료");
        }
    }
}
