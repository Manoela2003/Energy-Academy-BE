package bg.energyacademy.energyacademybe.repository;

import bg.energyacademy.energyacademybe.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
