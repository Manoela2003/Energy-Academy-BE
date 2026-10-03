package bg.energyacademy.energyacademybe.web;

import bg.energyacademy.energyacademybe.entity.SiteContent;
import bg.energyacademy.energyacademybe.repository.SiteContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
@CrossOrigin(origins = {"http://localhost:5173", "https://energy-academy-flax.vercel.app/"})
public class SiteContentController {

    @Autowired
    private SiteContentRepository repository;

    @GetMapping
    public Map<String, String> getAllContent() {
        List<SiteContent> list = repository.findAll();
        Map<String, String> map = new HashMap<>();
        for (SiteContent item : list) {
            map.put(item.getContentKey(), item.getContentValue());
        }
        return map;
    }

    @PutMapping("/{key}")
    public ResponseEntity<SiteContent> updateContent(@PathVariable String key, @RequestBody Map<String, String> body) {
        String newValue = body.get("contentValue");

        Optional<SiteContent> existing = repository.findByContentKey(key);
        SiteContent content;

        if (existing.isPresent()) {
            content = existing.get();
            content.setContentValue(newValue);
        } else {
            content = new SiteContent(key, newValue);
        }

        repository.save(content);
        return ResponseEntity.ok(content);
    }
}