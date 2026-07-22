package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.entity.SaveData;
import com.example.backend.repository.SaveDataRepository;
import com.example.backend.service.gamestate.session.GameSession;

import tools.jackson.databind.ObjectMapper;

import java.time.Instant;
import java.util.UUID;

@Service
public class SaveService {
    private final ObjectMapper objectMapper;
    private final GameSessionManager gameSessionManager;
    private final SaveDataRepository saveDataRepository;

    public SaveService(ObjectMapper objectMapper, GameSessionManager gameSessionManager, SaveDataRepository saveDataRepository) {
        this.objectMapper = objectMapper;
        this.gameSessionManager = gameSessionManager;
        this.saveDataRepository = saveDataRepository;
    }

    public String toJson(GameSession gameSession) throws Exception {
        return objectMapper.writeValueAsString(gameSession);
    }

    public GameSession fromJson(String json) throws Exception {
        return objectMapper.readValue(json, GameSession.class);
    }

    public void save(String sessionId, Integer userId) throws Exception {
        GameSession gameSession = gameSessionManager.getRequiredGameSession(sessionId);
        String json = toJson(gameSession);
        SaveData saveData = new SaveData(userId, json, Instant.now());
        saveDataRepository.save(saveData);
    }

    public String load(Integer userId) throws Exception {
        SaveData saveData = saveDataRepository.findByUserId(userId);
        if (saveData == null) {
            return null;
        }
        String json = saveData.getSaveData();
        GameSession gameSession = fromJson(json);
        String newSessionId = UUID.randomUUID().toString();
        gameSession.setSessionId(newSessionId);

        gameSessionManager.putGameSession(gameSession);
        return newSessionId;
    }
}
