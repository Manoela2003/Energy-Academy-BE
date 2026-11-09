package bg.energyacademy.energyacademybe.service.impl;

import bg.energyacademy.energyacademybe.dto.AdminStatsDTO;
import bg.energyacademy.energyacademybe.repository.EventRepository;
import bg.energyacademy.energyacademybe.repository.UserRepository;
import bg.energyacademy.energyacademybe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    public AdminStatsDTO getStats() {
        AdminStatsDTO stats = new AdminStatsDTO();
        stats.setTotalUsers(userRepository.count() - 1);
        stats.setTotalEvents(eventRepository.count());
        return stats;
    }
}
