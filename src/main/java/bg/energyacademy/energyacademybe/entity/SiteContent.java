package bg.energyacademy.energyacademybe.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "site_content")
public class SiteContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String contentKey;

    @Column(columnDefinition = "TEXT")
    private String contentValue;

    public SiteContent(String contentKey, String contentValue) {
        this.contentKey = contentKey;
        this.contentValue = contentValue;
    }
}
