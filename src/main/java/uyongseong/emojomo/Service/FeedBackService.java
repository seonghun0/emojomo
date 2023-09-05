package uyongseong.emojomo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uyongseong.emojomo.repository.FeedBackRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class FeedBackService {

    private final FeedBackRepository feedBackRepository;

    //피드백 등록
    @Transactional
    public void save(String feedback, String type){ feedBackRepository.save(type, feedback);}
}
