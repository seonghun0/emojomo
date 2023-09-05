package uyongseong.emojomo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uyongseong.emojomo.domain.LikeCount;
import uyongseong.emojomo.repository.LikeCountRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class LikeCountService {

    private final LikeCountRepository likeCountRepository;

    @Transactional
    public void save(LikeCount likeCount){ likeCountRepository.save(likeCount);}

    public Long getCount()
    {
        return likeCountRepository.getCount();
    }
}
