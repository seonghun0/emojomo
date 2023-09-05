package uyongseong.emojomo.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class LikeCount {

    @Id
    @GeneratedValue
    @Column(name = "LC_idx")
    private Long id;

    @Column(name = "LC_access_type")
    private String accessType;

    @Column(name = "LC_ip_address")
    private String ipAddress;

    @Column(name = "LC_regi_date")
    private LocalDateTime regiDate;

    public LikeCount createLikeCount(String accessType, String ipAddress)
    {
        LikeCount likeCount = new LikeCount();

        likeCount.setAccessType(accessType);
        likeCount.setIpAddress(ipAddress);
        likeCount.setRegiDate(LocalDateTime.now());

        return likeCount;
    }
}
