package uyongseong.emojomo.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uyongseong.emojomo.domain.Emoji;
import uyongseong.emojomo.domain.EmojiResult;
import uyongseong.emojomo.domain.ResultData;
import uyongseong.emojomo.repository.EmojiRepository;
import uyongseong.emojomo.repository.SearchResultRepository;

import javax.xml.transform.Result;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class EmojiService {

    private final EmojiRepository emojiRepository;
    private final SearchResultRepository searchResultRepository;

    /**
     * 이모지 등록 ??
     */
    @Transactional
    public void save(Emoji emoji){
        emojiRepository.save(emoji);
    }

    public List<Emoji> findEmojis(){
        return emojiRepository.findAll();
    }

    public Emoji findEmoji(Long emojiId){
        return emojiRepository.findOne(emojiId);
    }

    public List<Emoji> findByEmojis(String mean){
        return emojiRepository.findByMean(mean);
    }

    public ResultData searchWords(String str){

        List<String> words = splitWords(str);

        ResultData resultData = findEmojiWithWords(words);

        searchResultRepository.save(str, resultData.getEmojiResult().toString());

        return resultData;
    }

    public List<String> splitWords(String str){
        str = str.trim();

        Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);

        KomoranResult analyzeResultList = komoran.analyze(str);

        List<Token> tokenList = analyzeResultList.getTokenList();
        List<String> words = new ArrayList<>();

        String[] strArray = {"NNG","NNP","NNB","SL","SH","SN"};
        List<String> strList = new ArrayList<>(Arrays.asList(strArray));

        tokenList.forEach(token -> {
            if(strList.contains(token.getPos()) && !words.contains(token.getMorph())) {
                if(token.getPos() == "NNB" && token.getMorph().length() > 1){
                    words.add(token.getMorph());
                }else{
                    words.add(token.getMorph());
                }
            }
        });

        return words;
    }

    public ResultData findEmojiWithWords(List<String> words){
        List<EmojiResult> emojiList = new ArrayList<>();
        List<String> searchWords = new ArrayList<>();

        words.forEach(word ->{
            List<Emoji> emojis = emojiRepository.findByMeans(word);
            if(word.length() == 1 && emojis.size() >= 10){

            }else{
                emojis.forEach(emoji -> {
                    EmojiResult emojiResult = new EmojiResult(); //결과단어 추가
                    EmojiResult er = emojiResult.createEmojiResult(emoji, word);
                    searchWords.add(word);
                    emojiList.add(er);
                    emoji.addUseCount();
                });
            }
        });

        Set<EmojiResult> set = new LinkedHashSet<>(emojiList);
        List<EmojiResult> newList = new ArrayList<>(set);

        Set<String> set2 = new LinkedHashSet<>(searchWords);
        List<String> newWordList = new ArrayList<>(set2);

        ResultData resultData = new ResultData();
        resultData = resultData.createResultData(newList, newWordList);

        return resultData;
    }

    //jar 파일에서는 사용불가
    public List<Emoji> readCSVandSave(String path) throws Exception {
        List<Emoji> data = new ArrayList<>();

        CSVReader reader = new CSVReader(new FileReader(path));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null){
            Emoji emoji = new Emoji().createEmoji(nextLine[0],nextLine[1],nextLine[2],nextLine[3]);
            data.add(emoji);
            emojiRepository.save(emoji);
        }
        return data;
    }

    public List<Emoji> readCSVandSaveWithStream(InputStream is) throws Exception {
        List<Emoji> data = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        String line = "";
        while ( (line = br.readLine()) != null ){
            String[] items = line.split(",");
            String i1 = "", i3 = "";

            if(items[1].contains("|")){
                i1 = Arrays.stream(items[1].split("\\|")).collect(Collectors.joining(", "));
            }else{
                i1 = items[1];
            }
            if(items[3].contains("|")){
                i3 = Arrays.stream(items[3].split("\\|")).collect(Collectors.joining(", "));
            }else{
                i3 = items[3];
            }

            Emoji emoji = new Emoji().createEmoji(items[0],i1,items[2],i3);
            data.add(emoji);
            emojiRepository.save(emoji);
        }
        return data;
    }



}
